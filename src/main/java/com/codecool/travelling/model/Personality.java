package com.codecool.travelling.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Entity
@Data
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
            objectiveDecisionMaking, entrepreneurship, creativity, humanityFocus;

    private int skillsItems = 5;
    private int personalityTraitItems = 9;
    private int personalityFocusItems = 3;

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
