package com.codecool.travelling.repository;

import com.codecool.travelling.model.Personality;
import com.codecool.travelling.model.Salesman;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
public class PersonalityRepositoryTest {

    @Autowired
    PersonalityRepository personalityRepository;

    @Autowired
    TestEntityManager entityManager;

    @Test
    public void saveSimpleOne(){
        Personality newPersonality = Personality.builder()
                .energyLevel(6)
                .assertiveness(7)
                .socialContacts(7)
                .compliance(8)  //szabálykövetés
                .attitude(7)  //hozzáállás
                .decisionMaking(7)
                .adaptability(7) // alkalmazkodás
                .independence(6)
                .objectiveDecisionMaking(7)
                .studyIndex(7)
                .vocabulary(7)
                .readingLiteracy(7)
                .numberComprehension(7)
                .calculation(7)
                .creativity(2)
                .administrative(7)
                .scientificProfessional(7)
                .mechanical(7)
                .entrepreneurship(1)
                .humanityFocus(3)
                .build();
        newPersonality.setSalesman(Mockito.any(Salesman.class));
        personalityRepository.save(newPersonality);
        List<Personality> personalities = personalityRepository.findAll();
        assertThat(personalities).hasSize(1);
        System.out.println(personalities);
    }

}