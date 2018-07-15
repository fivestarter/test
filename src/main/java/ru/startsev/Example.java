package ru.startsev;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.startsev.criteria.DeptSpecification;
import ru.startsev.model.*;

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

    @RequestMapping("/init")
    String init() {
        Dept dept = new Dept();
        dept.setName("Dept 1");

        List<Emp> emps = new ArrayList<>();
        dept.setEmps(emps);

        Emp emp = new Emp();
        emp.setDept(dept);
        emp.setName("Petrov");
        emps.add(emp);
        List<Child> children = new ArrayList<>();
        emp.setChildren(children);

        Child sonChild = new Child();
        sonChild.setEmp(emp);
        sonChild.setName("Misha");
        sonChild.setSurname("Ryajenka");
        children.add(sonChild);

        Child daughter = new Child();
        daughter.setEmp(emp);
        daughter.setName("Masha");
        daughter.setSurname("Kefir");
        children.add(daughter);

        Emp empSidorov = new Emp();
        empSidorov.setDept(dept);
        empSidorov.setName("Sidorov");
        emps.add(empSidorov);

        List<Child> sidorovChildren = new ArrayList<>();
        emp.setChildren(sidorovChildren);

        Child sidorovSon = new Child();
        sidorovSon.setEmp(empSidorov);
        sidorovSon.setName("Misha");
        sidorovSon.setSurname("Ryajenka");
        sidorovChildren.add(sidorovSon);

        Child sodorovDaughter = new Child();
        sodorovDaughter.setEmp(empSidorov);
        sodorovDaughter.setName("Masha");
        sodorovDaughter.setSurname("Kefir");
        sidorovChildren.add(sodorovDaughter);


        deptRepository.save(dept);
        return "ok";
    }

    @RequestMapping("/search")
    String search() {
        return deptRepository.findAll(DeptSpecification.getByChildSurname("Kefir"))
                .stream()
                .map(dept -> dept.getId().toString())
                .reduce("", ((s, s2) -> s + s2));

    }
}
