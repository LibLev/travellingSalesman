package com.codecool.travelling.model;

import lombok.*;

import javax.validation.constraints.NotNull;


@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RoleIdeals {


    private final int[] studyIndex = {6,7,8, 9};
    private final int[] vocabulary = {6, 7, 8};
    private final int[] readingLiteracy = {7, 8, 9};
    private final int[] calculation = {6, 7, 8};
    private final int[] numberComprehension = {7, 8, 9};

    private final int[] energyLevel = {6, 7, 8};
    private final int[] assertiveness = {7, 8, 9};
    private final int[] socialContacts = {5, 6, 7};
    private final int[] compliance = {6, 7, 8};
    private final int[] attitude = {7, 8, 9};
    private final int[] decisionMaking = {5, 6, 7};
    private final int[] adaptability = {5, 6, 7};
    private final int[] independence = {4, 5, 6};
    private final int[] objectiveDecisionMaking = {5, 6, 7};

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
