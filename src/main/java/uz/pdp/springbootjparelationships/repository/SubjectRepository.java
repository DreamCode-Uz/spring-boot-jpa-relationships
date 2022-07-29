package uz.pdp.springbootjparelationships.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.springbootjparelationships.entity.Subject;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Integer> {
}
