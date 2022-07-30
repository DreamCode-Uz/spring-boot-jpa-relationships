package uz.pdp.springbootjparelationships.payload;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UniversityDTO {
    private String name;
    private String city;
    private String district;
    private String street;
}
