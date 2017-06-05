package com.skilltest.sky.parentalcontrol.ParentalControl.movie.service;


import com.skilltest.sky.parentalcontrol.ParentalControl.movie.exception.TechnicalFailureException;
import com.skilltest.sky.parentalcontrol.ParentalControl.movie.exception.TitleNotFoundException;
import org.springframework.stereotype.Service;

/** Blank implementation - this will be mocked out, during Unit Testing, using Mockito */
@Service
public class MovieServiceImpl implements MovieService {
    @Override
    public String getParentalControlLevel(String movieId) throws TitleNotFoundException, TechnicalFailureException {
        return null;
    }
}
