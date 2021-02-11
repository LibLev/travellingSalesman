package com.codecool.travelling.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.*;

@Data
@Builder
@NoArgsConstructor
@Component
public class RoleIdeal {

    @ElementCollection(fetch = FetchType.EAGER)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonIgnore
    private final List<Integer> studyIndex = new ArrayList<>(Arrays.asList(6,7,8,9));
    @ElementCollection(fetch = FetchType.EAGER)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonIgnore
    private final List<Integer> vocabulary = new ArrayList<>(Arrays.asList(6, 7, 8));
    @ElementCollection(fetch = FetchType.EAGER)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonIgnore
    private final List<Integer> readingLiteracy = new ArrayList<>(Arrays.asList(7,8,9));
    @ElementCollection(fetch = FetchType.EAGER)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonIgnore
    private final List<Integer> calculation = new ArrayList<>(Arrays.asList(6,7,8));
    @ElementCollection(fetch = FetchType.EAGER)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonIgnore
    private final List<Integer> numberComprehension = new ArrayList<>(Arrays.asList(7,8,9));

    @ElementCollection(fetch = FetchType.EAGER)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonIgnore
    private final List<Integer> energyLevel = new ArrayList<>(Arrays.asList(6, 7, 8));
    @ElementCollection(fetch = FetchType.EAGER)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonIgnore
    private final List<Integer> assertiveness = new ArrayList<>(Arrays.asList(7,8,9));
    @ElementCollection(fetch = FetchType.EAGER)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonIgnore
    private final List<Integer> socialContacts = new ArrayList<>(Arrays.asList(5,6,7));
    @ElementCollection(fetch = FetchType.EAGER)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonIgnore
    private final List<Integer> compliance = new ArrayList<>(Arrays.asList(6,7,8));
    @ElementCollection(fetch = FetchType.EAGER)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonIgnore
    private final List<Integer> attitude = new ArrayList<>(Arrays.asList(7,8,9));
    @ElementCollection(fetch = FetchType.EAGER)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonIgnore
    private final List<Integer> decisionMaking = new ArrayList<>(Arrays.asList(5,6,7));
    @ElementCollection(fetch = FetchType.EAGER)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonIgnore
    private final List<Integer> adaptability = new ArrayList<>(Arrays.asList(5,6,7));
    @ElementCollection(fetch = FetchType.EAGER)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonIgnore
    private final List<Integer> independence = new ArrayList<>(Arrays.asList(4,5,6));
    @ElementCollection(fetch = FetchType.EAGER)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonIgnore
    private final List<Integer> objectiveDecisionMaking = new ArrayList<>(Arrays.asList(5,6,7));

    private final int entrepreneurship = 1;
    private final int creativity = 2;
    private final int humanityFocus = 3;
    private final int mechanical = 6;
    private final int scientificProfessional = 6;
    private final int administrative = 6;


    private final int skillsItems = 5;
    private final int personalityTraitItems = 9;
    private final int personalityFocusItems = 6;

}
/**
 * Three personality traits:
 *
 * skills: studyIndex, vocabulary, readingLiteracy, calculation,numberComprehension
 *
 * personalityTrait: energyLevel, assertiveness, socialContacts,
 *             compliance, attitude, decisionMaking, adaptability, independence,
 *             objectiveDecisionMaking
 *
 * personalityFocus: entrepreneurship, creativity, humanityFocus
 *       + administrative, scientificProfessional, mechanical,
 */
