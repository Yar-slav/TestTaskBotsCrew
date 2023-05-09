package com.yfedyna.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @ToString.Exclude
    @OneToOne
    @JoinColumn(
            name = "head_of_department_id",
            foreignKey = @ForeignKey(name = "fk_departments_lectors"))
    private Lector headOfDepartment;

    @ToString.Exclude
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "departments_lectors",
            joinColumns = @JoinColumn(name = "department_id", foreignKey = @ForeignKey(name = "fk_departments_lectors_departments")),
            inverseJoinColumns = @JoinColumn(name = "lector_id", foreignKey = @ForeignKey(name = "fk_departments_lectors_lectors"))
    )
    private List<Lector> lectors = new ArrayList<>();
}
