package uz.pdp.springbootjparelationships.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.pdp.springbootjparelationships.entity.Faculty;
import uz.pdp.springbootjparelationships.entity.University;
import uz.pdp.springbootjparelationships.payload.FacultyDTO;
import uz.pdp.springbootjparelationships.repository.FacultyRepository;
import uz.pdp.springbootjparelationships.repository.UniversityRepository;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.*;

@Service
public class FacultyService {

    private final FacultyRepository facultyRepository;
    private final UniversityRepository universityRepository;

    @Autowired
    public FacultyService(FacultyRepository facultyRepository, UniversityRepository universityRepository) {
        this.facultyRepository = facultyRepository;
        this.universityRepository = universityRepository;
    }

    //    MINISTRY
    public ResponseEntity<List<Faculty>> getAllForMinistry() {
        return new ResponseEntity<>(facultyRepository.findAll(), OK);
    }

    //    UNIVERSITY
    public ResponseEntity<Object> getAllForUniversity(Integer universityId) {
        Optional<Faculty> optionalFaculty = facultyRepository.findAllByUniversity_Id(universityId);
        if (optionalFaculty.isPresent()) {
            System.out.println(optionalFaculty);
        }
        return new ResponseEntity<>(NOT_FOUND);
    }

    public ResponseEntity<?> addFaculty(FacultyDTO dto) {
        Optional<University> optional = universityRepository.findById(dto.getUniversityId());
        boolean facultyByName = facultyRepository.existsByName(dto.getName());
        if (!facultyByName && optional.isPresent()) {
            facultyRepository.save(new Faculty(dto.getName(), optional.get()));
            return new ResponseEntity<>("Faculty successfully added", CREATED);
        }
        return new ResponseEntity<>(BAD_REQUEST);
    }

    public ResponseEntity<?> editFaculty(Integer id, FacultyDTO dto) {
        Optional<Faculty> optionalFaculty = facultyRepository.findById(id);
        if (optionalFaculty.isPresent()) {
            Faculty faculty = optionalFaculty.get();
            faculty.setName(dto.getName());
            Optional<University> ou = universityRepository.findById(dto.getUniversityId());
            ou.ifPresent(faculty::setUniversity);
            facultyRepository.save(faculty);
            return new ResponseEntity<>("Faculty successfully edited", OK);
        }
        return new ResponseEntity<>("Faculty not found", NOT_FOUND);
    }

    public ResponseEntity<?> deleteFaculty(Integer id) {
        Optional<Faculty> optionalFaculty = facultyRepository.findById(id);
        if (optionalFaculty.isPresent()) {
            facultyRepository.delete(optionalFaculty.get());
            return new ResponseEntity<>("Faculty successfully deleted.", OK);
        }
        return new ResponseEntity<>("Faculty not found.", NOT_FOUND);
    }
}
