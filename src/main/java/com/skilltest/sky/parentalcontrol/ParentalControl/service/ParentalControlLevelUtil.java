package com.skilltest.sky.parentalcontrol.ParentalControl.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

/** This helper class can be used to compare the Parental control levels to determine if a given level is acceptable */
@Component
public class ParentalControlLevelUtil {

    /** The list of parental controls - note that the order is important, i.e. '18' can watch anything, '15' - everything except 18, etc
     *  TODO: This field _should_ be loaded from a Configuration element from the properties file */
    private List<String> parentalControlOrderedList = Arrays.asList("U", "PG", "12", "15", "18");

    private Logger log = LoggerFactory.getLogger(ParentalControlLevelUtil.class);

    public boolean isParentalControlLevelAcceptableForViewing(String parentalControlLevel, String movieRating) {

        if (!parentalControlOrderedList.contains(parentalControlLevel) || !parentalControlOrderedList.contains(movieRating)) {

            throw new IllegalArgumentException("The parental controls (or movie rating) were not found!? (parentalControlLevel: " +
                    parentalControlLevel + ", movieRating: " + movieRating);
        }

        log.debug("Parental control assessment: { parentalControlLevel {}, movieRating {}, okToWatch {} }.", parentalControlLevel,
                movieRating, parentalControlOrderedList.indexOf(parentalControlLevel) >= parentalControlOrderedList.indexOf(movieRating));

        return parentalControlOrderedList.indexOf(parentalControlLevel) >= parentalControlOrderedList.indexOf(movieRating);
    }
}
