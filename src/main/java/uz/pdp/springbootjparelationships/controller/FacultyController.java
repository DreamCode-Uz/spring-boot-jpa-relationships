package uz.pdp.springbootjparelationships.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.springbootjparelationships.payload.FacultyDTO;
import uz.pdp.springbootjparelationships.service.FacultyService;

@RestController
@RequestMapping("/api/faculty")
public class FacultyController {

    private final FacultyService service;

    @Autowired
    public FacultyController(FacultyService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<?> allFacultyForMinistry() {
        return service.getAllForMinistry();
    }

    @GetMapping("/university={facultyId}")
    public ResponseEntity<?> allFacultyForUniversity(@PathVariable Integer facultyId) {
        return service.getAllForUniversity(facultyId);
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody FacultyDTO facultyDTO) {
        return service.addFaculty(facultyDTO);
    }

    @PutMapping("/id={facultyId}")
    public ResponseEntity<?> update(@PathVariable Integer facultyId, @RequestBody FacultyDTO facultyDTO) {
        return service.editFaculty(facultyId, facultyDTO);
    }

    @DeleteMapping("/id={facultyId}")
    public ResponseEntity<?> delete(@PathVariable Integer facultyId) {
        return service.deleteFaculty(facultyId);
    }
}
