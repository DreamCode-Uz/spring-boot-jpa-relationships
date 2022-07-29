package uz.pdp.springbootjparelationships.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.springbootjparelationships.entity.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {
}
