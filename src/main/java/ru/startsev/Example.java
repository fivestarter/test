package ru.startsev;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.startsev.model.Dept;
import ru.startsev.model.DeptRepository;
import ru.startsev.model.Emp;
import ru.startsev.model.EmpRepository;

import java.util.ArrayList;
import java.util.List;

@RestController
public class Example {

    private DeptRepository deptRepository;
    private EmpRepository empRepository;


    @Autowired
    public Example(DeptRepository deptRepository, EmpRepository empRepository) {
        this.deptRepository = deptRepository;
        this.empRepository = empRepository;
    }

    @RequestMapping("/")
    String home() {
        Dept dept = new Dept();
        dept.setName("number 1");
        List<Emp> emps = new ArrayList<>();
        Emp emp = new Emp();
        emp.setDept(dept);
        emp.setName("Kashtanka");
        emps.add(emp);
        dept.setEmps(emps);
        deptRepository.save(dept);
//        empRepository.save(emp);
        return "secret";
    }

    @RequestMapping("/otvet/{id}")
    String otvet(@PathVariable("id") Long id) {
        return deptRepository.findById(id).get().getEmps().get(0).getName();
    }

    @RequestMapping("/addnew/{id}")
    String addNew(@PathVariable("id") Long id) {
        Dept dept = deptRepository.findById(id).get();
        Emp emp = new Emp();
        emp.setDept(dept);
        emp.setName("Pushok");
        dept.getEmps().add(emp);
        deptRepository.save(dept);
        return "Ok";
    }

    @RequestMapping("/orphan/{id}")
    String orphan(@PathVariable("id") Long id) {
        Dept dept = deptRepository.findById(id).get();
        dept.setName("Baron");
        deptRepository.save(dept);
        return "Ok";
    }

    @RequestMapping("/delete/{id}")
    String delete(@PathVariable("id") Long id) {
        Dept dept = deptRepository.findById(id).get();
        dept.setEmps(null);
        deptRepository.save(dept);
        return "Ok";
    }
}
