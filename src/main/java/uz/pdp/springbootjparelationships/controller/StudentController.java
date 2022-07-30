package uz.pdp.springbootjparelationships.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.springbootjparelationships.entity.Student;
import uz.pdp.springbootjparelationships.payload.StudentDTO;
import uz.pdp.springbootjparelationships.service.StudentService;

import java.util.List;

@RestController
@RequestMapping("/api/student")
public class StudentController {
    private final StudentService service;

    @Autowired
    public StudentController(StudentService service) {
        this.service = service;
    }

    //    FOR MINISTRY
    @GetMapping
    public ResponseEntity<Page<Student>> getAllStudentForMinistry(@RequestParam(value = "page", defaultValue = "1") Integer page) {
        return service.getAllStudentsForMinistry(page);
    }

    //    FOR UNIVERSITY
    @GetMapping("/university={universityId}")
    public ResponseEntity<Page<Student>> getAllStudentForMinistry(@PathVariable Integer universityId, @RequestParam(value = "page", defaultValue = "1") Integer page) {
        return service.getAllStudentsForUniversity(universityId, page);
    }

    //    FOR FACULTY
    @GetMapping("/university={universityId}/faculty={facultyId}")
    public ResponseEntity<Page<Student>> getAllStudentForFaculty(@PathVariable Integer universityId, @PathVariable Integer facultyId, @RequestParam(value = "page", defaultValue = "1") Integer page) {
        return service.getAllStudentForFaculty(universityId, facultyId, page);
    }

    //    FOR GROUP OWNER
    @GetMapping("/university={universityId}/group={groupId}")
    public ResponseEntity<List<Student>> getAllStudentForGroup(@PathVariable Integer universityId, @PathVariable Integer groupId) {
        return service.getAllStudentForGroupOwner(universityId, groupId);
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody StudentDTO dto) {
        return service.saveStudent(dto);
    }

    @PutMapping("/id={studentId}")
    public ResponseEntity<?> update(@PathVariable("studentId") Integer id, @RequestBody StudentDTO studentDTO) {
        return service.editStudent(id, studentDTO);
    }

    @DeleteMapping("/id={studentId}")
    public ResponseEntity<?> delete(@PathVariable Integer studentId) {
        return service.deleteStudent(studentId);
    }
}
