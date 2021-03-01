package com.codecool.travelling.service;

import com.codecool.travelling.model.Position;
import com.codecool.travelling.model.Salesman;
import com.codecool.travelling.repository.SalesmanRepository;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Optional;
import java.util.UUID;

import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;

public class SalesmanServiceTest {

    private SalesmanRepository salesmanRepository = Mockito.mock(SalesmanRepository.class);

    private SalesmanService salesmanService = new SalesmanService(salesmanRepository);

    @Test
    public void testGetSalesmanById(){
        Optional<Salesman> object = Optional.of(new Salesman());
        given(salesmanRepository.findById(Mockito.any(UUID.class))).willReturn(object);
        Salesman returnedSalesman = salesmanService.getSalesmanById(UUID.fromString("9ee504da-0eff-4d37-8a21-78b26c17d900"));
        Mockito.verify(salesmanRepository).findById(Mockito.any(UUID.class));
        assertNotNull(returnedSalesman);
    }

}