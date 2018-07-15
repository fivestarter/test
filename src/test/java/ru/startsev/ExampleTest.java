package ru.startsev;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.startsev.criteria.DeptSpecification;
import ru.startsev.model.Child;
import ru.startsev.model.Dept;
import ru.startsev.model.DeptRepository;
import ru.startsev.model.Emp;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
public class ExampleTest {

    private DeptRepository deptRepository;

    public void init() {
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
    }


    public void test() {
        deptRepository.findAll(DeptSpecification.getByName("Petrov"))
                .forEach(dept -> System.out.println(dept.getId()));
    }

}