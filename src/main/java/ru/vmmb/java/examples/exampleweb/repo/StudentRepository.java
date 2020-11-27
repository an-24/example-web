package ru.vmmb.java.examples.exampleweb.repo;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.vmmb.java.examples.exampleweb.model.Student;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student,Integer> {
    @Query("from Student")
    public List<Student> findStudents(Pageable page);

    @Query("from Student s " +
           "where lower(s.name) like lower(:mask) or " +
                  "lower(s.surname) like lower(:mask) or " +
                  "lower(s.mname) like lower(:mask) or " +
                  "lower(s.zkNumber) like lower(:mask) ")
    public List<Student> findStudents(@Param("mask") String mask, Pageable page);
}
