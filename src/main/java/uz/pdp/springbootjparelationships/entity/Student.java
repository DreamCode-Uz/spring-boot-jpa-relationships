package uz.pdp.springbootjparelationships.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String firstname;

    @Column(nullable = false)
    private String lastname;

    @OneToOne
    private Address address;

    @ManyToOne
    private Group group;

    @ManyToMany
    private List<Subject> subjects;

    public Student(String firstname, String lastname, Address address, Group group, List<Subject> subjects) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.address = address;
        this.group = group;
        this.subjects = subjects;
    }
}
