package ru.vmmb.java.examples.exampleweb.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "STUDENT")
public class Student {
    @Id
    @SequenceGenerator(name="newRec", sequenceName="STUDENT_SEQ",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "newRec")
    @Column(name = "STUDENT_ID", unique = true,nullable = false)
    private Integer id;

    @Column(name = "STUDENT_NAME", length = 30,nullable = false)
    private String name;

    @Column(name = "STUDENT_SURNAME", length = 100,nullable = false)
    private String surname;

    @Column(name = "STUDENT_MNAME", length = 60,nullable = true)
    private String mname;

    @Column(name = "STUDENT_ZKNUMBER", length = 20,nullable = false)
    private String zkNumber;

    @Column(name = "STUDENT_BDATE", nullable = true)
    @Temporal(TemporalType.TIMESTAMP)
    private Date bdate;

    public Student() {
        //nop
    }

    public Student(String name, String surname, String mname, String zkNumber, Date bdate) {
        this.name = name;
        this.surname = surname;
        this.mname = mname;
        this.zkNumber = zkNumber;
        this.bdate = bdate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getMname() {
        return mname;
    }

    public void setMname(String mname) {
        this.mname = mname;
    }

    public String getZkNumber() {
        return zkNumber;
    }

    public void setZkNumber(String zkNumber) {
        this.zkNumber = zkNumber;
    }

    public Date getBdate() {
        return bdate;
    }

    public void setBdate(Date bdate) {
        this.bdate = bdate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", mname='" + mname + '\'' +
                ", zkNumber='" + zkNumber + '\'' +
                ", bdate=" + bdate +
                '}';
    }
}
