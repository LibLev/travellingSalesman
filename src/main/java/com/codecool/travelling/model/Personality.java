package com.codecool.travelling.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.UUID;


@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Personality {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @NotNull
    private int studyIndex, vocabulary, readingLiteracy, calculation, 
            numberComprehension, energyLevel, assertiveness, socialContacts,
            compliance, attitude, decisionMaking, adaptability, independence,
            objectiveDecisionMaking, entrepreneurship, creativity, humanityFocus,
            mechanical, scientificProfessional, administrative;

    private final int skillsItems = 5;
    private final int personalityTraitItems = 9;
    private final int personalityFocusItems = 3;

    @OneToOne(fetch = FetchType.LAZY)
    private Salesman salesman;

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
 */
