package com.codecool.travelling.service;

import com.codecool.travelling.model.Personality;
import com.codecool.travelling.model.RoleIdeal;
import com.codecool.travelling.repository.PersonalityRepository;
import com.codecool.travelling.repository.PositionRepository;
import com.codecool.travelling.repository.SalesmanRepository;
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

    private Personality personality = Mockito.mock(Personality.class);
    private RoleIdeal roleIdeal = Mockito.mock(RoleIdeal.class);
    private PositionRepository positionRepository = Mockito.mock(PositionRepository.class);
    private SalesmanRepository salesmanRepository = Mockito.mock(SalesmanRepository.class);
    private PersonalityRepository personalityRepository = Mockito.mock(PersonalityRepository.class);

    private PersonalityService personalityService = new PersonalityService(salesmanRepository,positionRepository,personalityRepository,roleIdeal);

    @Test
    public void testCalculateDifferenceForSkill(){
        when(roleIdeal.getStudyIndex()).thenReturn(new ArrayList<>(Arrays.asList(6,7,8,9,10)));
        when(roleIdeal.getVocabulary()).thenReturn(new ArrayList<>(Arrays.asList(6,7,8,9,10)));
        when(roleIdeal.getReadingLiteracy()).thenReturn(new ArrayList<>(Arrays.asList(6,7,8,9,10)));
        when(roleIdeal.getCalculation()).thenReturn(new ArrayList<>(Arrays.asList(6,7,8,9,10)));
        when(roleIdeal.getNumberComprehension()).thenReturn(new ArrayList<>(Arrays.asList(6,7,8,9,10)));
        when(personality.getStudyIndex()).thenReturn(7);
        when(personality.getVocabulary()).thenReturn(7);
        when(personality.getReadingLiteracy()).thenReturn(7);
        when(personality.getCalculation()).thenReturn(7);
        when(personality.getNumberComprehension()).thenReturn(7);
        when(personality.getSkillsItems()).thenReturn(5);
        //personalityService = new PersonalityService(salesmanRepository,positionRepository,personalityRepository,roleIdeal);
        List<Integer> mockedRoleIdealStudyIndex = roleIdeal.getStudyIndex();
        int mockedSalesmanStudyIndex = personality.getStudyIndex();
        assertArrayEquals(new int[]{ 0,0,0,0,0 },personalityService.calculateDifferenceForSkill(personality));
    }
}