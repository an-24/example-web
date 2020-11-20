package ru.vmmb.java.examples.exampleweb.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.vmmb.java.examples.exampleweb.model.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student,Integer> {

}
