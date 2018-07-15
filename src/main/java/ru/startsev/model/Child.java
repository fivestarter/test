package ru.startsev.model;

import javax.persistence.*;

@Entity
public class Child {
    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String surname;

    @ManyToOne
    @JoinColumn(name = "emp_id")
    private Emp emp;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Emp getEmp() {
        return emp;
    }

    public void setEmp(Emp emp) {
        this.emp = emp;
    }
}
