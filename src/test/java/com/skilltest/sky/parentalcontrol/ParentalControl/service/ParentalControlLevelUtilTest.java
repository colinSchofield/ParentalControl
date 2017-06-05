package com.skilltest.sky.parentalcontrol.ParentalControl.service;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ParentalControlLevelUtilTest {

    /** Considered Parental Levels are, in order: U, PG, 12, 15, 18 */

    ParentalControlLevelUtil util;

    @Before
    public void setup() {
        util = new ParentalControlLevelUtil();
    }

    @Test
    public void testEqualParentalLevelsAreOkay() {

        assertTrue("Matching values are Okay (U).", util.isParentalControlLevelAcceptableForViewing("U", "U"));
        assertTrue("Matching values are Okay (PG).", util.isParentalControlLevelAcceptableForViewing("PG", "PG"));
        assertTrue("Matching values are Okay (12).", util.isParentalControlLevelAcceptableForViewing("12", "12"));
        assertTrue("Matching values are Okay (15).", util.isParentalControlLevelAcceptableForViewing("15", "15"));
        assertTrue("Matching values are Okay (18).", util.isParentalControlLevelAcceptableForViewing("18", "18"));
    }

    @Test
    public void testOlderParentalLevelsAreOkay() {

        assertTrue("Older Parental Levels are Okay (PG).", util.isParentalControlLevelAcceptableForViewing("PG", "U"));
        assertTrue("Older Parental Levels are Okay (18).", util.isParentalControlLevelAcceptableForViewing("18", "15"));
        assertTrue("Older Parental Levels are Okay (15).", util.isParentalControlLevelAcceptableForViewing("15", "PG"));
        assertTrue("Older Parental Levels are Okay (15).", util.isParentalControlLevelAcceptableForViewing("15", "PG"));
        assertTrue("Older Parental Levels are Okay (18).", util.isParentalControlLevelAcceptableForViewing("18", "15"));
    }

    @Test
    public void testYoungerParentalLevelsAreNotAcceptable() {

        assertFalse("Younger Parental Levels are not Okay (PG).", util.isParentalControlLevelAcceptableForViewing("U", "PG"));
        assertFalse("Younger Parental Levels are not Okay (18).", util.isParentalControlLevelAcceptableForViewing("15", "18"));
        assertFalse("Younger Parental Levels are not Okay (15).", util.isParentalControlLevelAcceptableForViewing("PG", "15"));
        assertFalse("Younger Parental Levels are not Okay (15).", util.isParentalControlLevelAcceptableForViewing("PG", "15"));
        assertFalse("Younger Parental Levels are not Okay (18).", util.isParentalControlLevelAcceptableForViewing("15", "18"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void invalidParentalControlStringThrowsAnException() {

        util.isParentalControlLevelAcceptableForViewing("UNKNOWN PCL", "U");
    }

    @Test(expected = IllegalArgumentException.class)
    public void invalidMovieRatingStringThrowsAnException() {

        util.isParentalControlLevelAcceptableForViewing("U", "UNKNOWN PCL");
    }

}