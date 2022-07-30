package uz.pdp.springbootjparelationships.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.pdp.springbootjparelationships.entity.Faculty;
import uz.pdp.springbootjparelationships.entity.Group;
import uz.pdp.springbootjparelationships.payload.GroupDTO;
import uz.pdp.springbootjparelationships.repository.FacultyRepository;
import uz.pdp.springbootjparelationships.repository.GroupRepository;

import java.util.Optional;

import static org.springframework.http.HttpStatus.*;

@Service
public class GroupService {

    private final GroupRepository groupRepository;
    private final FacultyRepository facultyRepository;

    @Autowired
    public GroupService(GroupRepository groupRepository, FacultyRepository facultyRepository) {
        this.groupRepository = groupRepository;
        this.facultyRepository = facultyRepository;
    }

    //    FOR MINISTRY
    public ResponseEntity<?> getAllGroupForMinistry() {
        return new ResponseEntity<>(groupRepository.findAll(), OK);
    }

    public ResponseEntity<?> getAllGroupForUniversity(Integer id) {
        return new ResponseEntity<>(groupRepository.findAllByFaculty_UniversityId(id), OK);
    }

    public ResponseEntity<?> save(GroupDTO dto) {
        Group group = new Group();
        group.setName(dto.getName());
        Optional<Faculty> optionalFaculty = facultyRepository.findById(dto.getFacultyId());
        if (!optionalFaculty.isPresent()) {
            return new ResponseEntity<>("Faculty not found", NOT_FOUND);
        }
        group.setFaculty(optionalFaculty.get());
        return new ResponseEntity<>(groupRepository.save(group), CREATED);
    }

    public ResponseEntity<?> edit(Integer id, GroupDTO dto) {
        Optional<Group> optionalGroup = groupRepository.findById(id);
        if (optionalGroup.isPresent()) {
            Group group = optionalGroup.get();
            group.setName(dto.getName());
            Optional<Faculty> optionalFaculty = facultyRepository.findById(dto.getFacultyId());
            if (optionalFaculty.isPresent()) {
                group.setFaculty(optionalFaculty.get());
                return new ResponseEntity<>(groupRepository.save(group), OK);
            }
        }
        return new ResponseEntity<>(NOT_FOUND);
    }

    public ResponseEntity<?> delete(Integer id) {
        Optional<Group> optionalGroup = groupRepository.findById(id);
        if (optionalGroup.isPresent()) {
            groupRepository.delete(optionalGroup.get());
            return new ResponseEntity<>("Group successfully deleted", OK);
        }
        return new ResponseEntity<>(NOT_FOUND);
    }
}
