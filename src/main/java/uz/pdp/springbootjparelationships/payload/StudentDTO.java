package uz.pdp.springbootjparelationships.payload;

import lombok.Data;

import java.util.List;

@Data
public class StudentDTO {
    private String firstname;
    private String lastname;
    private String city;
    private String district;
    private String street;
    private Integer groupId;
    private List<Integer> subjectId;
}
