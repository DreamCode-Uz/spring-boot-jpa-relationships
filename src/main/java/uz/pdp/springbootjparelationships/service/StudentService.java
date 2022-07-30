package uz.pdp.springbootjparelationships.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.pdp.springbootjparelationships.entity.*;
import uz.pdp.springbootjparelationships.payload.StudentDTO;
import uz.pdp.springbootjparelationships.repository.AddressRepository;
import uz.pdp.springbootjparelationships.repository.GroupRepository;
import uz.pdp.springbootjparelationships.repository.StudentRepository;
import uz.pdp.springbootjparelationships.repository.SubjectRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.*;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final AddressRepository addressRepository;
    private final GroupRepository groupRepository;
    private final SubjectRepository subjectRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository, AddressRepository addressRepository, GroupRepository groupRepository, SubjectRepository subjectRepository) {
        this.studentRepository = studentRepository;
        this.addressRepository = addressRepository;
        this.groupRepository = groupRepository;
        this.subjectRepository = subjectRepository;
    }

    //    READ FOR MINISTRY
    public ResponseEntity<Page<Student>> getAllStudentsForMinistry(Integer page) {
        return new ResponseEntity<>(studentRepository.findAll(PageRequest.of(page - 1, 10)), OK);
    }

    //    READ FOR UNIVERSITY
    public ResponseEntity<Page<Student>> getAllStudentsForUniversity(Integer universityId, Integer page) {
        return new ResponseEntity<>(studentRepository.findAllByGroup_Faculty_UniversityId(universityId, PageRequest.of(page - 1, 10)), OK);
    }

    //    READ FOR FACULTY
    public ResponseEntity<Page<Student>> getAllStudentForFaculty(Integer universityId, Integer facultyId, Integer page) {
        return new ResponseEntity<>(studentRepository.findAllByGroup_FacultyIdAndGroup_Faculty_UniversityId(facultyId, universityId, PageRequest.of(page - 1, 10)), OK);
    }

    //    READ FOR GROUP
    public ResponseEntity<List<Student>> getAllStudentForGroupOwner(Integer universityId, Integer groupId) {
        return new ResponseEntity<>(studentRepository.findAllByGroup_Faculty_UniversityIdAndGroupId(universityId, groupId), OK);
    }

    //    CREAT
    public ResponseEntity<?> saveStudent(StudentDTO dto) {
        try {
            Optional<Group> optionalGroup = groupRepository.findById(dto.getGroupId());
            if (!optionalGroup.isPresent()) {
                return new ResponseEntity<>("Student Group is not found.", NOT_FOUND);
            }
            List<Subject> subjects = subjectScan(dto.getSubjectId());
            if (subjects.size() == 0) {
                return new ResponseEntity<>(BAD_REQUEST);
            }
            Address address = new Address(dto.getCity(), dto.getDistrict(), dto.getStreet());
            address = addressRepository.save(address);
            Student save = studentRepository.save(new Student(dto.getFirstname(), dto.getLastname(), address, optionalGroup.get(), subjects));
            return new ResponseEntity<>(save, CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(BAD_REQUEST);
        }
    }

    //    UPDATE
    public ResponseEntity<?> editStudent(Integer id, StudentDTO dto) {
        Optional<Student> os = studentRepository.findById(id);
        if (os.isPresent()) {
            try {
                Optional<Group> og = groupRepository.findById(dto.getGroupId());
                if (!og.isPresent()) {
                    return new ResponseEntity<>("Group is not found", NOT_FOUND);
                }
                List<Subject> subjects = subjectScan(dto.getSubjectId());
                if (subjects.size() == 0) {
                    return new ResponseEntity<>(BAD_REQUEST);
                }
                Address address = os.get().getAddress();
                address.setStreet(dto.getStreet());
                address.setCity(dto.getCity());
                address.setDistrict(dto.getDistrict());

                address = addressRepository.save(address);
                return new ResponseEntity<>(studentRepository.save(new Student(os.get().getFirstname(), os.get().getLastname(), address, og.get(), subjects)), OK);
            } catch (Exception e) {
                return new ResponseEntity<>(BAD_REQUEST);
            }
        }
        return new ResponseEntity<>("Student is not found", NOT_FOUND);
    }


    //    DELETE
    public ResponseEntity<?> deleteStudent(Integer id) {
        Optional<Student> optionalStudent = studentRepository.findById(id);
        if (optionalStudent.isPresent()) {
            try {
                studentRepository.delete(optionalStudent.get());
                return new ResponseEntity<>("Student successfully deleted", OK);
            } catch (Exception e) {
                return new ResponseEntity<>(BAD_REQUEST);
            }
        }
        return new ResponseEntity<>(NOT_FOUND);
    }

    /************  SUBJECT ACTION  ************/
    public List<Subject> subjectScan(List<Integer> subjectsId) {
        List<Subject> subjects = new ArrayList<>();
        for (Integer subjectId : subjectsId) {
            Optional<Subject> optionalSubject = subjectRepository.findById(subjectId);
            if (!optionalSubject.isPresent()) {
                return new ArrayList<>();
            }
            subjects.add(optionalSubject.get());
        }
        return subjects;
    }
}
