package com.codecool.travelling.service;

import com.codecool.travelling.model.MATCH_LEVEL;
import com.codecool.travelling.model.Personality;
import com.codecool.travelling.model.Position;
import com.codecool.travelling.model.Salesman;
import com.codecool.travelling.repository.CompanyRepository;
import com.codecool.travelling.repository.PositionRepository;
import com.codecool.travelling.repository.SalesmanRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@AllArgsConstructor
public class PersonalityService {

    private SalesmanRepository salesmanRepository;
    private CompanyRepository companyRepository;
    private PositionRepository positionRepository;
    private final int THRESHOLD = 4;

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


    private MATCH_LEVEL isSkillRecommended(int[] skillDifference) {
        double averageDif = Arrays.stream(skillDifference).average().getAsDouble();
        double countOfDif = Arrays.stream(skillDifference).filter(n->n <= THRESHOLD).count();
        if (averageDif == 0 && countOfDif == 0) return MATCH_LEVEL.PERFECT;
        if (averageDif > 2 && countOfDif > 2) return MATCH_LEVEL.RECOMMENDED;
        if (averageDif > 4 && countOfDif > 4) return MATCH_LEVEL.GOOD;
        if (averageDif > 5 && countOfDif > 5) return MATCH_LEVEL.ACCEPTABLE;
        return MATCH_LEVEL.NOT_RECOMMENDED;
    }

    /**
     * personality matching of all services for the sale's personality
     * 1st value List value of return map is MATCH_LEVEL for skills
     * 2nd value List value of return map is MATCH_LEVEL for personalityTraits
     *
     * Map looks like this:
     * a position, MATCH_LEVEL for skills, MATCH_LEVEL for traits
     */

    public Map<Position, List<MATCH_LEVEL>> getAllMatchingPositions(Personality salesPersonality) {
      Map<Position, List<MATCH_LEVEL>> result = new HashMap<>();
      List<Position> positions = positionRepository.findAll();
        ListIterator<Position> allPositions = positions.listIterator();
            while(allPositions.hasNext()){
                Personality positionPersonality = allPositions.next().getRequiredPersonality();

                // skill
                int[] skillDifference = calculateDifferenceForSkill(salesPersonality, positionPersonality);

                // personality trait
                int[] traitDifference = calculateDifferenceForPersonalityTrait(salesPersonality, positionPersonality);

                result.put(allPositions.next(),
                        Arrays.asList(isSkillRecommended(skillDifference), isSkillRecommended(traitDifference)));
            }
        return result;
     }

    /**
     * personality matching of all salesmen for the position's requested personality
     * 1st value List value of return map is MATCH_LEVEL for skills
     * 2nd value List value of return map is MATCH_LEVEL for personalityTraits
     *
     * Map looks like this:
     * a salesman, MATCH_LEVEL for skills, MATCH_LEVEL for traits
     */

    public Map<Salesman, List<MATCH_LEVEL>> getAllMatchingSalesman(Personality positionPersonality) {
        Map<Salesman, List<MATCH_LEVEL>> result = new HashMap<>();
        List<Salesman> salesmen = salesmanRepository.findAll();
        ListIterator<Salesman> allSalesmen = salesmen.listIterator();
        while(allSalesmen.hasNext()){
            Personality salesPersonality = allSalesmen.next().getSalesmanPersonality();

            // skill
            int[] skillDifference = calculateDifferenceForSkill(salesPersonality, positionPersonality);

            // personality trait
            int[] traitDifference = calculateDifferenceForPersonalityTrait(salesPersonality, positionPersonality);

            result.put(allSalesmen.next(),
                    Arrays.asList(isSkillRecommended(skillDifference), isSkillRecommended(traitDifference)));
        }
        return result;
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
