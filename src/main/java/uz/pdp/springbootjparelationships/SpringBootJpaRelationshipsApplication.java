package uz.pdp.springbootjparelationships;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import uz.pdp.springbootjparelationships.entity.Subject;
import uz.pdp.springbootjparelationships.payload.FacultyDTO;
import uz.pdp.springbootjparelationships.payload.GroupDTO;
import uz.pdp.springbootjparelationships.payload.UniversityDTO;
import uz.pdp.springbootjparelationships.service.FacultyService;
import uz.pdp.springbootjparelationships.service.GroupService;
import uz.pdp.springbootjparelationships.service.SubjectService;
import uz.pdp.springbootjparelationships.service.UniversityService;

import static uz.pdp.springbootjparelationships.client.StartedData.*;

@SpringBootApplication
public class SpringBootJpaRelationshipsApplication implements CommandLineRunner {

    private final SubjectService subjectService;
    private final UniversityService universityService;
    private final FacultyService facultyService;
    private final GroupService groupService;

    @Autowired
    public SpringBootJpaRelationshipsApplication(SubjectService subjectService, UniversityService universityService, FacultyService facultyService, GroupService groupService) {
        this.subjectService = subjectService;
        this.universityService = universityService;
        this.facultyService = facultyService;
        this.groupService = groupService;
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringBootJpaRelationshipsApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        for (String name : subjects) {
            subjectService.saveSubject(new Subject(name));
        }

        for (UniversityDTO university : universities()) {
            universityService.addUniversity(university);
        }

        for (FacultyDTO faculty : faculties()) {
            facultyService.addFaculty(faculty);
        }

        for (GroupDTO group : groups()) {
            groupService.save(group);
        }
    }
}
