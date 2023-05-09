package com.yfedyna.repository;

import com.yfedyna.model.Department;
import com.yfedyna.model.Lector;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface DepartmentRepo extends CrudRepository<Department, Long> {

    boolean existsByName(String departmentName);

    @Query("SELECT CONCAT(d.headOfDepartment.firstName, ' ', d.headOfDepartment.lastName) " +
            "FROM Department d WHERE d.name = :departmentName")
    String findHeadOfDepartment(String departmentName);

    @Query("SELECT l FROM Department d join d.lectors l WHERE d.name = :departmentName")
    List<Lector> getLectors(String departmentName);

    @Query("SELECT AVG(l.salary) FROM Department d JOIN d.lectors l WHERE d.name = :departmentName")
    BigDecimal findSalaryByDepartmentName(@Param("departmentName") String departmentName);

    @Query("SELECT count(l.id) FROM Department d JOIN d.lectors as l group by :departmentName")
    Long countOfEmployeesByDepartmentName(@Param("departmentName") String departmentName);

}
