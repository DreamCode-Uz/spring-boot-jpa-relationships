package uz.pdp.springbootjparelationships.payload;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class FacultyDTO {
    private String name;
    private Integer universityId;
}
