package com.yfedyna.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Lector {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;

    @Column(name = "degree")
    @Enumerated(EnumType.STRING)
    private LectorsDegree degree;

    private BigDecimal salary;

    @OneToOne(mappedBy = "headOfDepartment")
    private Department head;

    @ManyToMany(mappedBy = "lectors")
    private List<Department> departments = new ArrayList<>();
}
