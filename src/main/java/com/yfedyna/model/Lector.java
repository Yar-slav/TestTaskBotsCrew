package com.yfedyna.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

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

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "degree")
    @Enumerated(EnumType.STRING)
    private LectorsDegree degree;

    @Column(name = "salary")
    private BigDecimal salary;

    @OneToOne(mappedBy = "headOfDepartment")
    private Department head;

    @ToString.Exclude
    @ManyToMany(mappedBy = "lectors")
    private List<Department> departments = new ArrayList<>();
}
