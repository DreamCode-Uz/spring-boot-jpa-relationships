package uz.pdp.springbootjparelationships.payload;

import lombok.Data;

@Data
public class UniversityDTO {
    private String name;
    private String city;
    private String district;
    private String street;
}
