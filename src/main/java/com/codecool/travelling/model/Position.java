package com.codecool.travelling.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

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

    @OneToOne(mappedBy = "position", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private RequiredPersonality requiredPersonality;

    @ElementCollection(fetch = FetchType.EAGER)
    @Singular
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonIgnore
    private List<String> requirements = new ArrayList<>();

    @ElementCollection(fetch = FetchType.EAGER)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonIgnore
    @Transient
    @Singular("salesman")
    private Map<Salesman, STATUS> applicants = new HashMap<>();
}
