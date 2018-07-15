package ru.startsev.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Emp {
    @Id
    @GeneratedValue
    private Long id;
    private String name;

    @ManyToOne
    @JoinColumn(name = "dept_id")
    private Dept dept;

    @OneToMany(mappedBy = "emp", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Child> children;

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

    public Dept getDept() {
        return dept;
    }

    public void setDept(Dept dept) {
        this.dept = dept;
    }

    public List<Child> getChildren() {
        return children;
    }

    public void setChildren(List<Child> children) {
        this.children = children;
    }
}
