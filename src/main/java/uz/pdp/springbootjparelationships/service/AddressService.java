package uz.pdp.springbootjparelationships.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.pdp.springbootjparelationships.entity.Address;
import uz.pdp.springbootjparelationships.repository.AddressRepository;

import java.util.List;

@Service
public class AddressService {

    private final AddressRepository repository;

    @Autowired
    public AddressService(AddressRepository repository) {
        this.repository = repository;
    }

    //    READ
//    public ResponseEntity<List<Address>> getAddress() {
//
//    }
}
