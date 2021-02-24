package com.codecool.travelling.service;

import com.codecool.travelling.model.Position;
import com.codecool.travelling.repository.PositionRepository;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Optional;
import java.util.UUID;

import static org.junit.Assert.assertNotNull;
import static org.mockito.BDDMockito.given;

public class PositionServiceTest {

    private PositionRepository positionRepository = Mockito.mock(PositionRepository.class);

    private PositionService positionService = new PositionService(positionRepository);

    @Test
    public void testGetPositionById(){
        Optional<Position> object = Optional.of(new Position());
        given(positionRepository.findById(Mockito.any(UUID.class))).willReturn(object);
        Position returnedPosition = positionService.getPositionById(UUID.fromString("9ee504da-0eff-4d37-8a21-78b26c17d900"));
        Mockito.verify(positionRepository).findById(Mockito.any(UUID.class));
        assertNotNull(returnedPosition);
    }
}