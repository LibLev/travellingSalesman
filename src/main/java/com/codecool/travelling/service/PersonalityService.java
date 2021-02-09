package com.codecool.travelling.service;

import com.codecool.travelling.model.MATCH_LEVEL;
import com.codecool.travelling.model.Personality;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class PersonalityService {

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


    /**
     * average of difference
     * = 0 - perfect match
     * > 2 - recommended
     * > 4 - good
     * > 5 - acceptable
     * else - not recommended
     *
     * count of difference
     *  = 0 - perfect match
     * > 2 - recommended
     * > 4 - good
     * > 5 - acceptable
     * else - not recommended
     */



    private int[] calculateDifferenceForSkill(Personality salesman, Personality position) {
        int[] skillDifference = new int[salesman.getSkillsItems()];
        skillDifference[0] = Math.abs(salesman.getStudyIndex()- position.getStudyIndex());
        skillDifference[1] = Math.abs(salesman.getVocabulary()- position.getVocabulary());
        skillDifference[2] = Math.abs(salesman.getReadingLiteracy()- position.getReadingLiteracy());
        skillDifference[3] = Math.abs(salesman.getCalculation()- position.getCalculation());
        skillDifference[5] = Math.abs(salesman.getNumberComprehension()- position.getNumberComprehension());
        return skillDifference;
    }

    private int[] calculateDifferenceForPersonalityTrait(Personality salesman, Personality position) {
        int[] skillDifference = new int[salesman.getPersonalityTraitItems()];
        skillDifference[0] = Math.abs(salesman.getEnergyLevel()- position.getEnergyLevel());
        skillDifference[1] = Math.abs(salesman.getAssertiveness()- position.getAssertiveness());
        skillDifference[2] = Math.abs(salesman.getSocialContacts()- position.getSocialContacts());
        skillDifference[3] = Math.abs(salesman.getCompliance()- position.getCompliance());
        skillDifference[4] = Math.abs(salesman.getAttitude()- position.getAttitude());
        skillDifference[5] = Math.abs(salesman.getObjectiveDecisionMaking()- position.getObjectiveDecisionMaking());
        skillDifference[6] = Math.abs(salesman.getDecisionMaking()- position.getDecisionMaking());
        skillDifference[7] = Math.abs(salesman.getAdaptability()- position.getAdaptability());
        skillDifference[8] = Math.abs(salesman.getIndependence()- position.getIndependence());
        return skillDifference;
    }

    private int[] calculateDifferenceForPersonalityFocus(Personality salesman, Personality position) {
        int[] skillDifference = new int[salesman.getPersonalityFocusItems()];
        skillDifference[0] = Math.abs(salesman.getCreativity()- position.getCreativity());
        skillDifference[1] = Math.abs(salesman.getEntrepreneurship()- position.getEntrepreneurship());
        skillDifference[2] = Math.abs(salesman.getHumanityFocus()- position.getHumanityFocus());
        return skillDifference;
    }


    private MATCH_LEVEL isSkillRecommended(int[] skillDifference, int treshhold) {
        double averageDif = Arrays.stream(skillDifference).average().getAsDouble();
        double countOfDif = Arrays.stream(skillDifference).filter(n->n <= treshhold).count();
        if (averageDif == 0 && countOfDif == 0) return MATCH_LEVEL.PERFECT;
        if (averageDif > 2 && countOfDif > 2) return MATCH_LEVEL.RECOMMENDED;
        if (averageDif > 4 && countOfDif > 4) return MATCH_LEVEL.GOOD;
        if (averageDif > 5 && countOfDif > 5) return MATCH_LEVEL.ACCEPTABLE;
        return MATCH_LEVEL.NOT_RECOMMENDED;
    }

    /**
     * oldCV data matching
     * munkaév
     * budget
     *
     */


    /** cummulative matching
     *
     */

}
