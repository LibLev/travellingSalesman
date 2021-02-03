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
public class Salesman {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

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

/*    private Map<String, String> degreeWithSchool = new HashMap<>();

    private Map<String, String> oldPositionsWithCompany = new HashMap<>();

    @ElementCollection(fetch = FetchType.EAGER)
    @Builder.Default
    @Singular
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonIgnore
    private List<String> languages = new ArrayList<>();*/

    // roles of the user (ADMIN, USER,..)
    @ElementCollection(fetch = FetchType.EAGER)
    @Builder.Default
    private Set<String> roles = new HashSet<>();
}
