package com.codecool.travelling.service;

import com.codecool.travelling.model.Personality;
import com.codecool.travelling.model.RoleIdeal;
import com.codecool.travelling.repository.PersonalityRepository;
import com.codecool.travelling.repository.PositionRepository;
import com.codecool.travelling.repository.SalesmanRepository;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;

import static org.junit.jupiter.api.Assertions.*;


class PersonalityServiceTest {

    private static Personality personality = Mockito.mock(Personality.class);
    private static RoleIdeal roleIdeal = Mockito.mock(RoleIdeal.class);
    private PositionRepository positionRepository = Mockito.mock(PositionRepository.class);
    private SalesmanRepository salesmanRepository = Mockito.mock(SalesmanRepository.class);
    private PersonalityRepository personalityRepository = Mockito.mock(PersonalityRepository.class);

    private PersonalityService personalityService = new PersonalityService(salesmanRepository, positionRepository, personalityRepository, roleIdeal);

    @BeforeAll
    public static void init() {
        System.out.println("Instantiating PersonalityService Class");
        /**
         * Setting up personality trait elements for role ideal
         */
        when(roleIdeal.getStudyIndex()).thenReturn(new ArrayList<>(Arrays.asList(6, 7, 8, 9)));
        when(roleIdeal.getVocabulary()).thenReturn(new ArrayList<>(Arrays.asList(6, 7, 8)));
        when(roleIdeal.getReadingLiteracy()).thenReturn(new ArrayList<>(Arrays.asList(7, 8, 9)));
        when(roleIdeal.getCalculation()).thenReturn(new ArrayList<>(Arrays.asList(6, 7, 8)));
        when(roleIdeal.getNumberComprehension()).thenReturn(new ArrayList<>(Arrays.asList(7, 8, 9)));

        /**
         * Setting up personality skill elements for role ideal
         */
        when(roleIdeal.getEnergyLevel()).thenReturn(new ArrayList<>(Arrays.asList(6, 7, 8)));
        when(roleIdeal.getAssertiveness()).thenReturn(new ArrayList<>(Arrays.asList(7, 8, 9)));
        when(roleIdeal.getSocialContacts()).thenReturn(new ArrayList<>(Arrays.asList(5, 6, 7)));
        when(roleIdeal.getCompliance()).thenReturn(new ArrayList<>(Arrays.asList(6, 7, 8)));
        when(roleIdeal.getAttitude()).thenReturn(new ArrayList<>(Arrays.asList(7, 8, 9)));
        when(roleIdeal.getDecisionMaking()).thenReturn(new ArrayList<>(Arrays.asList(5, 6, 7)));
        when(roleIdeal.getAdaptability()).thenReturn(new ArrayList<>(Arrays.asList(5, 6, 7)));
        when(roleIdeal.getIndependence()).thenReturn(new ArrayList<>(Arrays.asList(4, 5, 6)));
        when(roleIdeal.getObjectiveDecisionMaking()).thenReturn(new ArrayList<>(Arrays.asList(5, 6, 7)));

        /**
         * Difference arrays need a size based on length of the personality groups
         * 5 for Skills and 9 for traits
         */
        when(personality.getSkillsItems()).thenReturn(5);
        when(personality.getPersonalityTraitItems()).thenReturn(9);


        /**
         * Since we don't need all personality trait elements to change for us to
         * confirm proper work of the code, we are setting up some of the personality
         * traits in advance.
         * 5 from the available 9 is set, so 4 remains for testing purposes
         */
        when(personality.getEnergyLevel()).thenReturn(7);
        when(personality.getAssertiveness()).thenReturn(7);
        when(personality.getSocialContacts()).thenReturn(7);
        when(personality.getCompliance()).thenReturn(7);
        when(personality.getAttitude()).thenReturn(7);

    }

    @AfterAll
    public static void teardown() {
        System.out.println("Tearing down PersonalityService test");
    }


    @Test
    public void testCalculateDifferenceForSkill_WhenThereIsNoDifferenceFromBenchmark() {
        when(personality.getStudyIndex()).thenReturn(7);
        when(personality.getVocabulary()).thenReturn(7);
        when(personality.getReadingLiteracy()).thenReturn(7);
        when(personality.getCalculation()).thenReturn(7);
        when(personality.getNumberComprehension()).thenReturn(7);
        assertArrayEquals(new int[]{0, 0, 0, 0, 0}, personalityService.calculateDifferenceForSkill(personality));
    }

    @Test
    public void testCalculateDifferenceForSkill_WhenThereIsOneSmallDifferenceFromBenchmark() {
        when(personality.getStudyIndex()).thenReturn(5);
        when(personality.getVocabulary()).thenReturn(7);
        when(personality.getReadingLiteracy()).thenReturn(10);
        when(personality.getCalculation()).thenReturn(7);
        when(personality.getNumberComprehension()).thenReturn(7);
        when(personality.getSkillsItems()).thenReturn(5);
        assertArrayEquals(new int[]{1, 0, 1, 0, 0}, personalityService.calculateDifferenceForSkill(personality));
    }

    @Test
    public void testCalculateDifferenceForSkill_WhenThereIsLargeDifferenceFromBenchmark() {
        when(personality.getStudyIndex()).thenReturn(0);
        when(personality.getVocabulary()).thenReturn(0);
        when(personality.getReadingLiteracy()).thenReturn(10);
        when(personality.getCalculation()).thenReturn(7);
        when(personality.getNumberComprehension()).thenReturn(7);
        when(personality.getSkillsItems()).thenReturn(5);
        assertArrayEquals(new int[]{6, 6, 1, 0, 0}, personalityService.calculateDifferenceForSkill(personality));
    }

    @Test
    public void testCalculateDifferenceForTraits_WhenThereIsNoDifferenceFromBenchmark() {
        when(personality.getDecisionMaking()).thenReturn(7);
        when(personality.getAdaptability()).thenReturn(7);
        when(personality.getIndependence()).thenReturn(6);
        when(personality.getObjectiveDecisionMaking()).thenReturn(7);
        assertArrayEquals(new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0}, personalityService.calculateDifferenceForPersonalityTrait(personality));
    }
}