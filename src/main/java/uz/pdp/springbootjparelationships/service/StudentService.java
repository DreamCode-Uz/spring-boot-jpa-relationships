package uz.pdp.springbootjparelationships.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.pdp.springbootjparelationships.entity.Address;
import uz.pdp.springbootjparelationships.entity.Group;
import uz.pdp.springbootjparelationships.entity.Student;
import uz.pdp.springbootjparelationships.entity.Subject;
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

    //    CREAT
    public ResponseEntity<?> saveStudent(StudentDTO dto) {
        try {
            Optional<Group> optionalGroup = groupRepository.findById(dto.getGroupId());
            if (!optionalGroup.isPresent()) {
                return new ResponseEntity<>("Student Group is not found.", NOT_FOUND);
            }
            List<Subject> subjects = subjectScan(dto.getSubjectId());
            if (subjects == null) {
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
                return null;
            }
            subjects.add(optionalSubject.get());
        }
        return subjects;
    }
}
