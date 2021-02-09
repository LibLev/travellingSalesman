package com.codecool.travelling.model;

import lombok.*;

import javax.validation.constraints.NotNull;
import java.util.UUID;


@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Personality {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @NotNull
    private int studyIndex, vocabulary, readingLiteracy, calculation, 
            numberComprehension, energyLevel, assertiveness, socialContacts,
            compliance, attitude, decisionMaking, adaptability, independence,
            objectiveDecisionMaking, entrepreneurship, creativity, humanityFocus;

    private final int skillsItems = 5;
    private final int personalityTraitItems = 9;
    private final int personalityFocusItems = 3;

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
