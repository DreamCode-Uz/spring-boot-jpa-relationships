package uz.pdp.springbootjparelationships.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.springbootjparelationships.entity.University;
import uz.pdp.springbootjparelationships.payload.UniversityDTO;
import uz.pdp.springbootjparelationships.service.UniversityService;

import java.util.List;

@RestController
@RequestMapping("/api/university")
public class UniversityController {

    private final UniversityService service;

    @Autowired
    public UniversityController(UniversityService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<University>> universityList() {
        return service.getAll();
    }

    @PostMapping
    public ResponseEntity<Object> save(@RequestBody UniversityDTO universityDTO) {
        return service.addUniversity(universityDTO);
    }

    @PutMapping("/id={id}")
    public ResponseEntity<Object> update(@PathVariable Integer id, @RequestBody UniversityDTO universityDTO) {
        return service.editUniversity(id, universityDTO);
    }

    @DeleteMapping("/id={id}")
    public ResponseEntity<String> delete(@PathVariable Integer id) {
        return service.deleteUniversity(id);
    }
}
