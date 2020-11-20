package ru.vmmb.java.examples.exampleweb.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.vmmb.java.examples.exampleweb.model.Student;
import ru.vmmb.java.examples.exampleweb.repo.StudentRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import java.util.Date;

@Controller
public class StudentController {
    private static Logger logger = LoggerFactory.getLogger(StudentController.class);

    private Student[] demoStudents = new Student[] {
            new Student("Иван","Иванов","Иванович","0003245",new Date()),
            new Student("Перт","Петров","Петрович","0001111",new Date()),
            new Student("Сидор","Сидоров","","0002454",new Date())
    };

    @Autowired
    private StudentRepository studentRepository;

    @PersistenceContext
    private EntityManager em;

    @GetMapping("/students")
    public String students(Model model) {
        model.addAttribute("students",studentRepository.findAll(Sort.by("surname","name","mname")));
        return "students/index";
    }

    @GetMapping("/add")
    public String add(Model model) {
        Student s = new Student();
        s.setZkNumber("ZK-");
        model.addAttribute("student",s);
        return "students/add";
    }

    @GetMapping("/edit/{id}")
    @Transactional(propagation = Propagation.REQUIRED)
    public String edit(Model model, @PathVariable("id") Integer id) {
        Student s = studentRepository.getOne(id);
        model.addAttribute("student",s);
        return "students/add";
    }

    @GetMapping("/delete/{id}")
    @Transactional(propagation = Propagation.REQUIRED)
    public String delete(Model model, @PathVariable("id") Integer id) {
        studentRepository.deleteById(id);
        return "redirect:/students";
    }

    @PostMapping("/poststudent")
    @Transactional(propagation = Propagation.REQUIRED)
    public String postStudent(@ModelAttribute Student student, Model model) {
        logger.info(student.toString());

        studentRepository.saveAndFlush(student);

/*
        if(student.getId()==null) {
            em.persist(student);
        } else {
            em.merge(student);
        }
 */

/*
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try {
            if(student.getId()==null) {
                em.persist(student);
            } else {
                em.merge(student);
            }
            tx.commit();
        } catch (Exception ex) {
            tx.rollback();
            ex.printStackTrace();
        }

 */
        return "redirect:/students";
    }
}
