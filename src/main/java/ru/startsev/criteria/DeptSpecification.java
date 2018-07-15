package ru.startsev.criteria;

import org.springframework.data.jpa.domain.Specification;
import ru.startsev.model.Child;
import ru.startsev.model.Dept;
import ru.startsev.model.Emp;

import javax.persistence.criteria.*;

public class DeptSpecification {

    public static Specification<Dept> getByChildName(final String name) {
        return (r, cq, cb) -> cb.equal(r.get("emp").get("child").get("name"), name);
    }

    public static Specification<Dept> getByChildSurname(final String surname) {
        return (r, cq, cb) -> {
            Join<Dept, Emp> emps = r.join("emps");
            Join<Emp, Child> childJoin = emps.join("children");
            return cb.equal(childJoin.get("surname"), surname);
        };
    }

    public static Specification<Dept> getByEmpName(final String name) {
        return (Specification<Dept>) (root, criteriaQuery, criteriaBuilder) -> {
            Join<Dept, Emp> emps = root.join("emps");
            return criteriaBuilder.equal(emps.get("name"), name);
        };
    }

    public static Specification<Dept> getByName(final String name) {
        return (r, cq, cb) -> cb.equal(r.get("name"), name);
    }

}
