package uz.pdp.springbootjparelationships.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.springbootjparelationships.entity.Group;

@Repository
public interface GroupRepository extends JpaRepository<Group, Integer> {
}
