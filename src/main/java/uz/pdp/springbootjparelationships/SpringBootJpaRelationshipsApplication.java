package uz.pdp.springbootjparelationships;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import uz.pdp.springbootjparelationships.entity.Subject;
import uz.pdp.springbootjparelationships.entity.University;
import uz.pdp.springbootjparelationships.payload.UniversityDTO;
import uz.pdp.springbootjparelationships.service.SubjectService;
import uz.pdp.springbootjparelationships.service.UniversityService;

import static uz.pdp.springbootjparelationships.client.StartedData.subjects;
import static uz.pdp.springbootjparelationships.client.StartedData.universities;

@SpringBootApplication
public class SpringBootJpaRelationshipsApplication implements CommandLineRunner {

    private final SubjectService subjectService;
    private final UniversityService universityService;

    @Autowired
    public SpringBootJpaRelationshipsApplication(SubjectService subjectService, UniversityService universityService) {
        this.subjectService = subjectService;
        this.universityService = universityService;
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
    }
}
