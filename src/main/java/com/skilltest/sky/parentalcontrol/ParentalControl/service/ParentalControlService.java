package com.skilltest.sky.parentalcontrol.ParentalControl.service;

import com.skilltest.sky.parentalcontrol.ParentalControl.movie.exception.TechnicalFailureException;
import com.skilltest.sky.parentalcontrol.ParentalControl.movie.exception.TitleNotFoundException;
import com.skilltest.sky.parentalcontrol.ParentalControl.movie.service.MovieService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * The ParentalControlService returns whether a movie is age appropriate for the viewer
 * <p>
 * Note: If <i>any</i> exceptions are thrown, the method will return that the movie cannot be watched
 *
 * @see MovieService
 */
@Service
public class ParentalControlService {

    Logger log = LoggerFactory.getLogger(ParentalControlService.class);

    @Autowired
    MovieService movieService;

    @Autowired
    ParentalControlLevelUtil util;

    /**
     * The isMovieViewableGivenParentalControlLevel method returns whether the selected movie is age appropriate for
     * the viewer.
     *
     * @param movieId the Id of the movie, as provided for the MovieService
     * @param parentControlLevel The acceptable level of viewership for the movie (i.e. U, PG, 12, 15, 18)
     * @return true if a movie can be viewed, given the provided parental control level
     */
    public boolean isMovieViewableGivenParentalControlLevel(String movieId, String parentControlLevel) {

        try {

            log.debug("About to call the MovieService: { movieId: {}, parentControlLevel: {} }.", movieId, parentControlLevel);
            String movieRating = movieService.getParentalControlLevel(movieId);
            log.debug("Returned movie rating of {}.", movieRating);

            return util.isParentalControlLevelAcceptableForViewing(parentControlLevel, movieRating);
        } catch (TitleNotFoundException tnf) {

            log.error("The movie service could not find the given movie (movieId: " + movieId + " exception cause: " + tnf.getMessage(), tnf);
            return false;
        } catch (TechnicalFailureException tf) {

            log.error("The movie service returned an unknown error (movieId: " + movieId + " exception cause: " + tf.getMessage(), tf);
            return false;
        } catch (Exception ex) {

            log.error("An unknown exception occurred: " + ex.getMessage(), ex);
            return false;
        }
    }
}