package com.codecool.travelling.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Email;
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
    @Column(unique=true)
    private String username;

    @NotEmpty
    private String password;

    @NotEmpty
    @Column(unique = true)
    @Email
    private String email;

    @NotEmpty
    private String firstname, lastname, middleName, gender, nationality, country, county, city, address;

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
    @Singular("onePosition")
    private Map<String, String> oldPositionsWithCompany = new HashMap<>();

    @ElementCollection(fetch = FetchType.EAGER)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonIgnore
    @Transient
    @Singular("oneDegree")
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

    @Enumerated(EnumType.ORDINAL)
    private MATCH_LEVEL matchLevel;


    @OneToOne(mappedBy = "salesman", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonIgnore
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Personality personality;
}
