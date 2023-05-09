package com.yfedyna.repository;

import com.yfedyna.model.Lector;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LectorRepo extends CrudRepository<Lector, Long> {

    @Query("SELECT CONCAT(l.firstName, ' ', l.lastName) " +
            "FROM Lector l WHERE CONCAT(l.firstName, ' ', l.lastName) LIKE %:template%")
    List<String> findByFirstNameOrLastName(String template);
}
