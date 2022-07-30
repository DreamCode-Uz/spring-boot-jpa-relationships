package uz.pdp.springbootjparelationships.payload;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class GroupDTO {
    private String name;
    private Integer facultyId;
}
