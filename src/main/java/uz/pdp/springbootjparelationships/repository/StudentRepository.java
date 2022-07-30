package uz.pdp.springbootjparelationships.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.springbootjparelationships.entity.Student;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {

    Page<Student> findAllByGroup_Faculty_UniversityId(Integer group_faculty_university_id, Pageable pageable);

    Page<Student> findAllByGroup_FacultyIdAndGroup_Faculty_UniversityId(Integer group_faculty_id, Integer group_faculty_university_id, Pageable pageable);

    List<Student> findAllByGroup_Faculty_UniversityIdAndGroupId(Integer group_faculty_university_id, Integer group_id);
}
