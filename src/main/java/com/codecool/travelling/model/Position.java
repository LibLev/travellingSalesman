package com.codecool.travelling.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.*;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Position {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne(cascade = CascadeType.MERGE)
    private Company company;

    @NotEmpty
    private String nameOfPosition, city;

    @NotNull
    private float salary;

    //private List<String> requirements = new ArrayList<>();

    //private Map<Salesman, STATUS> applicants = new HashMap<>();
}
