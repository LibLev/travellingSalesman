package com.codecool.travelling.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
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
public class Salesman{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @NotEmpty
    private String username;

    @NotEmpty
    private String password;

    @NotEmpty
    private String firstname, lastname, middleName, gender, email, nationality, country, county, city, address;

    @NotNull
    private int postcode, houseNumber, phoneNumber;

    @NotNull
    @DateTimeFormat(pattern = "dd-mm-yyyy")
    private LocalDate birthDate;

    private boolean drivingLicense;

    @ElementCollection(fetch = FetchType.EAGER)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonIgnore
    @Transient
    private Map<String, String> oldPositionsWithCompany = new HashMap<>();

    @ElementCollection(fetch = FetchType.EAGER)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonIgnore
    @Transient
    private Map<String, String> degreeWithSchool = new HashMap<>();

    @ElementCollection(fetch = FetchType.EAGER)
    @Singular
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonIgnore
    private Set<String> languages = new HashSet<>();

    // roles of the user (ADMIN, USER,..)
    @ElementCollection(fetch = FetchType.EAGER)
    @Builder.Default
    private Set<String> roles = new HashSet<>();

    @OneToOne(mappedBy = "salesman", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonIgnore
    private Personality personality;
}
