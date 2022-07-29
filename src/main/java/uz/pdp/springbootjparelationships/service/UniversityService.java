package uz.pdp.springbootjparelationships.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.pdp.springbootjparelationships.entity.Address;
import uz.pdp.springbootjparelationships.entity.University;
import uz.pdp.springbootjparelationships.payload.UniversityDTO;
import uz.pdp.springbootjparelationships.repository.AddressRepository;
import uz.pdp.springbootjparelationships.repository.UniversityRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UniversityService {
    private final UniversityRepository universityRepository;
    private final AddressRepository addressRepository;

    @Autowired
    public UniversityService(UniversityRepository universityRepository, AddressRepository addressRepository) {
        this.universityRepository = universityRepository;
        this.addressRepository = addressRepository;
    }

    public ResponseEntity<List<University>> getAll() {
        return new ResponseEntity<>(universityRepository.findAll(), HttpStatus.OK);
    }

    public ResponseEntity<Object> addUniversity(UniversityDTO dto) {
        Address address = new Address(dto.getCity(), dto.getDistrict(), dto.getStreet());
        try {
            address = addressRepository.save(address);
            University save = universityRepository.save(new University(dto.getName(), address));
            return new ResponseEntity<>(save, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("BAD REQUEST", HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<Object> editUniversity(Integer id, UniversityDTO dto) {
        Optional<University> ou = universityRepository.findById(id);
        if (ou.isPresent()) {
            University university = ou.get();
            university.setName(dto.getName());
            try {
                Address address = university.getAddress();
                address.setCity(dto.getCity());
                address.setDistrict(dto.getDistrict());
                address.setStreet(dto.getStreet());
                address = addressRepository.save(address);
                university.setAddress(address);
                return new ResponseEntity<>(universityRepository.save(university), HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity<>("BAD REQUEST", HttpStatus.OK);
            }
        }
        return new ResponseEntity<>("University not found",HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<String> deleteUniversity(Integer id) {
        Optional<University> optional = universityRepository.findById(id);
        if (optional.isPresent()) {
            universityRepository.delete(optional.get());
            return new ResponseEntity<>("University successfully deleted.", HttpStatus.OK);
        }
        return new ResponseEntity<>("University not found.",HttpStatus.NOT_FOUND);
    }
}
