package ru.vmmb.java.examples.exampleweb.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.vmmb.java.examples.exampleweb.model.Student;
import ru.vmmb.java.examples.exampleweb.repo.StudentRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;
import java.util.Date;
import java.util.List;

@Controller
public class StudentController {
    private static final int PAGE_SIZE = 10;
    private static Logger logger = LoggerFactory.getLogger(StudentController.class);
/*
    private Student[] demoStudents = new Student[] {
            new Student("Иван","Иванов","Иванович","0003245",new Date()),
            new Student("Перт","Петров","Петрович","0001111",new Date()),
            new Student("Сидор","Сидоров","","0002454",new Date())
    };
*/
    @Autowired
    private StudentRepository studentRepository;

    @PersistenceContext
    private EntityManager em;

    @GetMapping("/students")
    public String students(Model model,
                           @RequestParam(value = "page", required = false) Integer page,
                           @RequestParam(value = "mask",required = false) String mask) {
        if(page==null) page=0;
        List<Student> students;
        if(mask==null) {
            students = studentRepository.findStudents(PageRequest.of(page, PAGE_SIZE,Sort.by("surname", "name", "mname")));
        } else {
            students = studentRepository.findStudents("%"+mask+"%", PageRequest.of(page, PAGE_SIZE,Sort.by("surname", "name", "mname")));
        }
        model.addAttribute("students",students);
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
    public String postStudent(@Valid @ModelAttribute Student student, BindingResult result, Model model) {
        logger.info("Post student...");
        logger.info(student.toString());

        if(result.hasErrors()) {
            logger.error(result.getAllErrors().toString());
            return "students/add";
        }

        studentRepository.saveAndFlush(student);
        return "redirect:/students";
    }
}
