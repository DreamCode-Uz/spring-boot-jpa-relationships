package uz.pdp.springbootjparelationships.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.springbootjparelationships.entity.Subject;
import uz.pdp.springbootjparelationships.service.SubjectService;

import java.util.List;

@RestController
@RequestMapping("/api/subject")
public class SubjectController {

    private final SubjectService service;

    @Autowired
    public SubjectController(SubjectService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Subject>> subjects() {
        return service.getAll();
    }

    @GetMapping("/id={subjectId}")
    public ResponseEntity<Object> subject(@PathVariable("subjectId") Integer id) {
        return service.getOne(id);
    }

    @PostMapping
    public ResponseEntity<Object> addSubject(@RequestBody Subject subject) {
        return service.saveSubject(subject);
    }

    @PutMapping("/id={subjectId}")
    public ResponseEntity<Object> update(@PathVariable("subjectId") Integer id, @RequestBody Subject subject) {
        return service.editSubject(id, subject);
    }

    @DeleteMapping("/id={subjectId}")
    public ResponseEntity<String> delete(@PathVariable("subjectId") Integer id) {
        return service.deleteSubject(id);
    }
}
