package uz.pdp.springbootjparelationships.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.springbootjparelationships.entity.Faculty;

import java.util.Optional;

@Repository
public interface FacultyRepository extends JpaRepository<Faculty, Integer> {

    boolean existsByName(String name);
    Optional<Faculty> findAllByUniversity_Id(Integer university_id);
}
