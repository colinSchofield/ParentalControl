package com.skilltest.sky.parentalcontrol.ParentalControl.movie.service;

import com.skilltest.sky.parentalcontrol.ParentalControl.movie.exception.TechnicalFailureException;
import com.skilltest.sky.parentalcontrol.ParentalControl.movie.exception.TitleNotFoundException;

/**
 * The MovieService Interface has been provided here while we are waiting for the movie meta data team to complete their
 * implementation
 */
public interface MovieService {
    String getParentalControlLevel(String movieId)
            throws TitleNotFoundException, TechnicalFailureException;
}