package com.project.movies.service.impl;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.movies.model.ApiResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class DirectorServiceTest {

    @Mock
    private RestTemplate restTemplate;

    ObjectMapper objectMapper = new ObjectMapper();

    private DirectorService directorService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        directorService = new DirectorService(restTemplate);
    }

    @Test
    public void testGetDirectorsWithMovieCountGreaterThanThreshold() throws IOException {

        ApiResponse mockResponse = objectMapper.readValue(
                Files.readAllBytes(new ClassPathResource("movies.json").getFile().toPath()),
                ApiResponse.class
        );

        when(restTemplate.getForObject(any(String.class), any())).thenReturn(mockResponse);

        List<String> directorsWithMovieCountGreaterThanThreshold_0 = directorService.getDirectorsWithMovieCountGreaterThanThreshold(0);
        assertEquals(6, directorsWithMovieCountGreaterThanThreshold_0.size());
        assertEquals("Clint Eastwood", directorsWithMovieCountGreaterThanThreshold_0.get(0));
        assertEquals("Quentin Tarantino", directorsWithMovieCountGreaterThanThreshold_0.get(4));

        List<String> directorsWithMovieCountGreaterThanThreshold_1 = directorService.getDirectorsWithMovieCountGreaterThanThreshold(1);
        assertEquals(4, directorsWithMovieCountGreaterThanThreshold_1.size());
        assertEquals("M. Night Shyamalan", directorsWithMovieCountGreaterThanThreshold_1.get(0));
        assertEquals("Woody Allen", directorsWithMovieCountGreaterThanThreshold_1.get(3));

        List<String> directorsWithMovieCountGreaterThanThreshold_2 = directorService.getDirectorsWithMovieCountGreaterThanThreshold(2);
        assertEquals(1, directorsWithMovieCountGreaterThanThreshold_2.size());
        assertEquals("Woody Allen", directorsWithMovieCountGreaterThanThreshold_2.get(0));

        List<String> directorsWithMovieCountGreaterThanThreshold_3 = directorService.getDirectorsWithMovieCountGreaterThanThreshold(3);
        assertEquals(0, directorsWithMovieCountGreaterThanThreshold_3.size());
    }
}