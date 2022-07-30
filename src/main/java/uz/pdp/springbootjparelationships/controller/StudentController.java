package uz.pdp.springbootjparelationships.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.springbootjparelationships.entity.Student;
import uz.pdp.springbootjparelationships.payload.StudentDTO;
import uz.pdp.springbootjparelationships.service.StudentService;

@RestController
@RequestMapping("/api/student")
public class StudentController {
    private final StudentService service;

    @Autowired
    public StudentController(StudentService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<Page<Student>> getAllStudentForMinistry(@RequestParam(value = "page", defaultValue = "1") Integer page) {
        return service.getAllStudentsForMinistry(page);
    }

    @GetMapping("/university/id={universityId}")
    public ResponseEntity<Page<Student>> getAllStudentForMinistry(@PathVariable Integer universityId, @RequestParam(value = "page", defaultValue = "1") Integer page) {
        return service.getAllStudentsForUniversity(universityId, page);
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody StudentDTO dto) {
        return service.saveStudent(dto);
    }

    @DeleteMapping("/id={universityId}")
    public ResponseEntity<?> delete(@PathVariable Integer universityId) {
        return service.deleteStudent(universityId);
    }
}
