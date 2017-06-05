package com.skilltest.sky.parentalcontrol.ParentalControl.service;

import com.skilltest.sky.parentalcontrol.ParentalControl.movie.exception.TechnicalFailureException;
import com.skilltest.sky.parentalcontrol.ParentalControl.movie.exception.TitleNotFoundException;
import com.skilltest.sky.parentalcontrol.ParentalControl.movie.service.MovieService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ParentalControlServiceTest {

    @Autowired
    @InjectMocks
    private ParentalControlService parentalControlService;

    @Mock
    MovieService movieService;

    @Spy
    ParentalControlLevelUtil parentalControlLevelUtil;

    @Test
    public void testValidParentalControlLevels() throws Exception {

        when(movieService.getParentalControlLevel("1")).thenReturn("PG");
        assertTrue(parentalControlService.isMovieViewableGivenParentalControlLevel("1", "15"));
    }

    @Test
    public void testValidParentalControlLevelsSameValues() throws Exception {

        when(movieService.getParentalControlLevel("1")).thenReturn("PG");
        assertTrue(parentalControlService.isMovieViewableGivenParentalControlLevel("1", "PG"));
    }

    @Test
    public void testInvalidParentalControlLevels() throws Exception {

        when(movieService.getParentalControlLevel("1")).thenReturn("PG");
        assertFalse(parentalControlService.isMovieViewableGivenParentalControlLevel("1", "U"));
    }

    @Test
    public void testInvalidParentalControlLevelsMovieIdNotRecognised() throws Exception {

        when(movieService.getParentalControlLevel("99")).thenThrow(TitleNotFoundException.class);
        assertFalse(parentalControlService.isMovieViewableGivenParentalControlLevel("1", "U"));
    }

    @Test
    public void testMovieServiceUnknownError() throws Exception {

        when(movieService.getParentalControlLevel("1")).thenThrow(TechnicalFailureException.class);
        assertFalse(parentalControlService.isMovieViewableGivenParentalControlLevel("1", "U"));
    }

    @Test
    public void testUnrecognisedParentalControlLevel() throws Exception {

        when(movieService.getParentalControlLevel("1")).thenReturn("U");
        assertFalse(parentalControlService.isMovieViewableGivenParentalControlLevel("1", "NOT RECOGNISED"));

    }
}