package uz.pdp.springbootjparelationships.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.springbootjparelationships.entity.University;

@Repository
public interface UniversityRepository extends JpaRepository<University, Integer> {
}
