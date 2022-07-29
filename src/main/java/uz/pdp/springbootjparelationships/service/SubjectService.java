package uz.pdp.springbootjparelationships.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.pdp.springbootjparelationships.entity.Subject;
import uz.pdp.springbootjparelationships.repository.SubjectRepository;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.*;

@Service
public class SubjectService {

    private final SubjectRepository repository;

    @Autowired
    public SubjectService(SubjectRepository repository) {
        this.repository = repository;
    }

    //    READ ALL
    public ResponseEntity<List<Subject>> getAll() {
        return new ResponseEntity<>(repository.findAll(), OK);
    }

    //    READ ONE
    public ResponseEntity<Object> getOne(Integer id) {
        Optional<Subject> optionalSubject = repository.findById(id);
        if (optionalSubject.isPresent()) {
            return new ResponseEntity<>(optionalSubject.get(), OK);
        }
        return new ResponseEntity<>("Subject not found", NOT_FOUND);
    }

    //    CREAT
    public ResponseEntity<Object> saveSubject(Subject subject) {
        if (!repository.existsByName(subject.getName())) {
            return new ResponseEntity<>(repository.save(subject), CREATED);
        }
        return new ResponseEntity<>("Subject name already exist", ALREADY_REPORTED);
    }

    //    UPDATE
    public ResponseEntity<Object> editSubject(Integer id, Subject subject) {
        Optional<Subject> optionalSubject = repository.findById(id);
        if (optionalSubject.isPresent()) {
            if (repository.existsByName(subject.getName())) {
                return new ResponseEntity<>("Subject name already exist.", ALREADY_REPORTED);
            }
            return new ResponseEntity<>(repository.save(new Subject(id, subject.getName())), OK);
        }
        return new ResponseEntity<>("Subject not found.", NOT_FOUND);
    }

    //    DELETE
    public ResponseEntity<String> deleteSubject(Integer id) {
        Optional<Subject> subjectOptional = repository.findById(id);
        if (subjectOptional.isPresent()) {
            try {
                repository.delete(subjectOptional.get());
                return new ResponseEntity<>("Subject successfully deleted.", OK);
            } catch (Exception e) {
                return new ResponseEntity<>("BAD REQUEST", BAD_REQUEST);
            }
        }
        return new ResponseEntity<>("Subject not found.", NOT_FOUND);
    }
}
