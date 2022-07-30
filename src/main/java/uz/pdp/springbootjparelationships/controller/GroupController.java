package uz.pdp.springbootjparelationships.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.springbootjparelationships.payload.GroupDTO;
import uz.pdp.springbootjparelationships.service.GroupService;

@RestController
@RequestMapping("/api/group")
public class GroupController {

    private final GroupService service;

    @Autowired
    public GroupController(GroupService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<?> getAllGroupForMinistry() {
        return service.getAllGroupForMinistry();
    }

    @GetMapping("/id={groupId}")
    public ResponseEntity<?> getAllGroupForUniversity(@PathVariable Integer groupId) {
        return service.getAllGroupForUniversity(groupId);
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody GroupDTO groupDTO) {
        return service.save(groupDTO);
    }

    @PutMapping("/id={groupId}")
    public ResponseEntity<?> update(@PathVariable Integer groupId, @RequestBody GroupDTO groupDTO) {
        return service.edit(groupId, groupDTO);
    }

    @DeleteMapping("/id={groupId}")
    public ResponseEntity<?> delete(@PathVariable Integer groupId) {
        return service.delete(groupId);
    }
}
