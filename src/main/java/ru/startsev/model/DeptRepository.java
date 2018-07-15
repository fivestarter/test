package ru.startsev.model;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

public interface DeptRepository extends CrudRepository<Dept, Long>, JpaSpecificationExecutor<Dept> {
}
