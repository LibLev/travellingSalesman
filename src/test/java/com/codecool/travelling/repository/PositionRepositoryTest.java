package com.codecool.travelling.repository;

import com.codecool.travelling.model.Company;
import com.codecool.travelling.model.MATCH_LEVEL;
import com.codecool.travelling.model.Position;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
public class PositionRepositoryTest {

    @Autowired
    PositionRepository positionRepository;

    @Autowired
    TestEntityManager entityManager;

    @Test
    public void saveSimpleOne(){
        Position newPosition = Position.builder()
                .nameOfPosition("Salesman")
                .city("Budapest")
                .salary(350000)
                .requirements(Arrays.asList("good communication skills", "fluent english", "problem solving", "agile thinking"))
                .requiredMatchLevel(MATCH_LEVEL.PERFECT)
                .build();
        newPosition.setCompany(Mockito.any(Company.class));
        positionRepository.save(newPosition);
        List<Position> positions = positionRepository.findAll();
        assertThat(positions).hasSize(1);
    }

}