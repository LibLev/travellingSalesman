package com.codecool.travelling.service;

import com.codecool.travelling.model.*;
import com.codecool.travelling.repository.CompanyRepository;
import com.codecool.travelling.repository.PositionRepository;
import com.codecool.travelling.repository.SalesmanRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.IntStream;

@Service
@AllArgsConstructor
public class PersonalityService {

    private SalesmanRepository salesmanRepository;
    private CompanyRepository companyRepository;
    private PositionRepository positionRepository;
    //private RoleIdeal roleIdeal;
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


    /**
     * Three personality traits:
     *
     * skills: studyIndex, vocabulary, readingLiteracy, calculation,numberComprehension
     *
     * personalityTrait: energyLevel, assertiveness, socialContacts,
     *             compliance, attitude, decisionMaking, adaptability, independence,
     *             objectiveDecisionMaking
     *
     * personalityFocus: entrepreneurship, creativity, humanityFocus (the other 3 does not matter
     * in the matching algorithm)
     */



    /**
     * If the valueToCheck is within the range of permittedRange, return 0 otherwise return
     * the distance from the closest range member
     * @param valueToCheck
     * @param permittedRange
     * @return
     */

    private int calculateSingleDifference(int valueToCheck, int[] permittedRange) {
        int max = IntStream.of(permittedRange).max().orElse(Integer.MIN_VALUE);
        int min = IntStream.of(permittedRange).min().orElse(Integer.MAX_VALUE);
        return (valueToCheck >= min && valueToCheck <= max)
                ? 0
                : Math.min(Math.abs(valueToCheck-min), Math.abs(valueToCheck-max));
    }


    /**
     * Take indvidual personality skills from a salesman, and match it up to the permitted
     * range described in roleIdeal
     * @param salesman
     * @return int[] with the difference values
     */

    private int[] calculateDifferenceForSkill(Personality salesman) {
        int[] skillDifference = new int[salesman.getSkillsItems()];
        skillDifference[0] = calculateSingleDifference(salesman.getStudyIndex(), studyIndex);
        skillDifference[1] = calculateSingleDifference(salesman.getVocabulary(), vocabulary);
        skillDifference[2] = calculateSingleDifference(salesman.getReadingLiteracy(), readingLiteracy);
        skillDifference[3] = calculateSingleDifference(salesman.getCalculation(), calculation);
        skillDifference[4] = calculateSingleDifference(salesman.getNumberComprehension(), numberComprehension);
        return skillDifference;
    }

    /**
     * Take individual personality traits from a salesman, and match it up to the permitted
     * range described in roleIdeal
     * @param salesman
     * @return int[] with the difference values
     */

    private int[] calculateDifferenceForPersonalityTrait(Personality salesman) {
        int[] skillDifference = new int[salesman.getPersonalityTraitItems()];
        skillDifference[0] = calculateSingleDifference(salesman.getEnergyLevel(), energyLevel);
        skillDifference[1] = calculateSingleDifference(salesman.getAssertiveness(), assertiveness);
        skillDifference[2] = calculateSingleDifference(salesman.getSocialContacts(), socialContacts);
        skillDifference[3] = calculateSingleDifference(salesman.getCompliance(), compliance);
        skillDifference[4] = calculateSingleDifference(salesman.getAttitude(), attitude);
        skillDifference[5] = calculateSingleDifference(salesman.getObjectiveDecisionMaking(), objectiveDecisionMaking);
        skillDifference[6] = calculateSingleDifference(salesman.getDecisionMaking(), decisionMaking);
        skillDifference[7] = calculateSingleDifference(salesman.getAdaptability(), adaptability);
        skillDifference[8] = calculateSingleDifference(salesman.getIndependence(), independence);
        return skillDifference;
    }

    /**
     * Based on difference from optimal range, and the number of parameters that differ
     * the individual groups of personality aspects will be given enum values of MATCH_LEVEL
     * @param skillDifference
     * @return
     */

    private MATCH_LEVEL isSkillRecommendedSkills(int[] skillDifference) {
        System.out.println("első csoport eltérései "+Arrays.toString(skillDifference));
        int biggestDif = (int) Arrays.stream(skillDifference).max().getAsInt();
        int countOfDif = (int) Arrays.stream(skillDifference).filter(n-> n > 0).count();
        System.out.println("legnagyobb különbség = "+ biggestDif);
        System.out.println("hány elemben tér el = "+ countOfDif);
        if (biggestDif == 0 && countOfDif == 0) return MATCH_LEVEL.PERFECT;
        if (biggestDif <= 2 && countOfDif <= 1) return MATCH_LEVEL.RECOMMENDED;
        if (biggestDif <= 2 && countOfDif <= 2) return MATCH_LEVEL.ACCEPTABLE;
        return MATCH_LEVEL.NOT_RECOMMENDED;
    }

    private MATCH_LEVEL isSkillRecommendedTraits(int[] skillDifference) {
        System.out.println("második csoport eltérései "+Arrays.toString(skillDifference));
        int biggestDif = (int) Arrays.stream(skillDifference).max().getAsInt();
        int countOfDif = (int) Arrays.stream(skillDifference).filter(n-> n > 0).count();
        System.out.println("legnagyobb különbség = "+ biggestDif);
        System.out.println("hány elemben tér el = "+ countOfDif);
        if (biggestDif == 0 && countOfDif == 0) return MATCH_LEVEL.PERFECT;
        if (biggestDif <= 2 && countOfDif <= 2) return MATCH_LEVEL.RECOMMENDED;
        if (biggestDif <= 1 && countOfDif <= 3) return MATCH_LEVEL.ACCEPTABLE;
        return MATCH_LEVEL.NOT_RECOMMENDED;
    }


    /**
     * The higher the number the higher the ranking in the importance and focus for the
     * salesPersonality.
     * 1 means it was the first in their focus.
     * numbers thus represent the order of these values
     * only 3 from the 6 possible orders matter
     * @param salesPersonality
     * @return
     */

    private MATCH_LEVEL isSkillRecommendedFocus(Personality salesPersonality) {

        if (salesPersonality.getEntrepreneurship() == 1 &&
                salesPersonality.getCreativity() == 2 &&
                salesPersonality.getHumanityFocus() == 3) {
            return MATCH_LEVEL.PERFECT;
        } else if (salesPersonality.getEntrepreneurship() <=3 &&
                salesPersonality.getHumanityFocus()<=3){
            return MATCH_LEVEL.RECOMMENDED;
        } else if (salesPersonality.getEntrepreneurship() == 1) {
            return MATCH_LEVEL.ACCEPTABLE;
        } else {
            return MATCH_LEVEL.NOT_RECOMMENDED;
        }
    }


    /**
     * personality matching of person seeking job to ideal personality for that job role
     * the evaluation is based on the weakest possible aspect. If anything is in the not recommended
     * then the person is not recommended.
     * If anything is in the good, it can only be acceptable.
     * Same rule applies
     *
     * @param salesPersonality
     * @return MATCH_LEVEL
     */

    public Optional<MATCH_LEVEL> matchPersonToRole (Personality salesPersonality) {

        Optional<MATCH_LEVEL> matchLevel = Optional.empty();
        MATCH_LEVEL skillsMatch = isSkillRecommendedSkills(calculateDifferenceForSkill(salesPersonality));
        MATCH_LEVEL traitMatch = isSkillRecommendedTraits(calculateDifferenceForPersonalityTrait(salesPersonality));
        MATCH_LEVEL focusMatch = isSkillRecommendedFocus(salesPersonality);

        if (skillsMatch == MATCH_LEVEL.NOT_RECOMMENDED || traitMatch == MATCH_LEVEL.NOT_RECOMMENDED
                || focusMatch == MATCH_LEVEL.NOT_RECOMMENDED) {
            matchLevel = Optional.of(MATCH_LEVEL.NOT_RECOMMENDED);
            return matchLevel;

        } else if (skillsMatch == MATCH_LEVEL.ACCEPTABLE || traitMatch == MATCH_LEVEL.ACCEPTABLE
                || focusMatch == MATCH_LEVEL.ACCEPTABLE) {
            matchLevel = Optional.of(MATCH_LEVEL.ACCEPTABLE);
            return matchLevel;

        } else if (skillsMatch == MATCH_LEVEL.RECOMMENDED || traitMatch == MATCH_LEVEL.RECOMMENDED
                || focusMatch == MATCH_LEVEL.RECOMMENDED) {
            matchLevel = Optional.of(MATCH_LEVEL.RECOMMENDED);
            return matchLevel;

        } else if (skillsMatch == MATCH_LEVEL.PERFECT || traitMatch == MATCH_LEVEL.PERFECT
                || focusMatch == MATCH_LEVEL.PERFECT) {
            matchLevel = Optional.of(MATCH_LEVEL.PERFECT);
            return matchLevel;
        }
        return matchLevel;

     }




    /**
     * oldCV data matching
     *
     * range
     *      birthday (counted as years; int)
     *      sizeOfCompany (small company, medium sized, large one, multi; enum)
     *
     * minimum
     *      school level (highschool, ba, bm; enum)
     *      coursesCount (How many courses he took part in; int)
     *      relevantExperience (months spent in fields relevant for the search, int)
     *      languageProficiency (for each relevant language: enum: A1, A2, B1, B2. C1. C2; enum)
     *      recommendationUploaded (number of recommendations - only relevant if requested; int)
     *      budgetHandled (Yearly budget handled/requested to be handled; double)
     *
     * yes/no matches (boolean)
     *     driversLicence
     *     careerStarter (sales: I am one, position: it is ok to be one)
     *     currentlyEmployed (sales: I am one, position: it is ok to wait till you tie up stuff)
     *     sectors(enum - should write up some mock sectors to add to sectors enum)
     *     locationOfWork
     *
     * special
     *      salary (it is minimum in regards of salesman, but maximum in regards of company; double)
     *      systemsUsed (minimum from company's side, irrelevant from other side; Collection - stream)
     */


    /** cummulative matching
     *
     */

}




