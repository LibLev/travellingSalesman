package com.codecool.travelling.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.*;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @NotEmpty
    private String password;

    @NotEmpty
    private String nameOfCompany, country, county, city, address, taxNumber, email;

    @NotNull
    private int houseNumber, postcode, phoneNumber;

    @NotNull
    @DateTimeFormat(pattern = "dd-mm-yyyy")
    private LocalDate dateOfFoundation;

    // roles of the user (ADMIN, USER,..)
    @ElementCollection(fetch = FetchType.EAGER)
    @Builder.Default
    private Set<String> roles = new HashSet<>();

    @OneToMany
    private List<Position> positions = new ArrayList<>();
}
