package com.rabo.assignment.time;

import static org.junit.Assert.*;

import java.time.format.DateTimeParseException;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class TimePatternCounterTest {

	@Test
	public void testCountGivenExample() {
		TimePatternCounter tCal = new TimePatternCounter();
		int count = tCal.count("16:15:00", "17:00:00");
		assertEquals(2, count);
	}

	@Test
	public void testCountHigherStartMinute() {
		TimePatternCounter tCal = new TimePatternCounter();
		int count = tCal.count("16:17:00", "17:00:00");
		assertEquals(0, count);
	}

	@Test
	public void testCountHigherStartSeconds() {
		TimePatternCounter tCal = new TimePatternCounter();
		int count = tCal.count("16:50:50", "17:17:00");
		assertEquals(1, count);
	}

	@Test
	public void testCountLessEndMinute() {
		TimePatternCounter tCal = new TimePatternCounter();
		int count = tCal.count("16:15:00", "19:15:00");
		assertEquals(6, count);
	}

	@Test
	public void testCountLessEndSecond() {
		TimePatternCounter tCal = new TimePatternCounter();
		int count = tCal.count("16:15:00", "19:19:05");
		assertEquals(7, count);
	}

	@Test
	public void testCountHigherEndMinute() {
		TimePatternCounter tCal = new TimePatternCounter();
		int count = tCal.count("16:15:00", "19:45:32");
		assertEquals(8, count);
	}

	@Test
	public void testCountExtremeBoundery() {
		TimePatternCounter tCal = new TimePatternCounter();
		int count = tCal.count("00:00:01", "23:59:59");
		assertEquals(46, count);
	}

	@Rule
	public ExpectedException exceptionRule = ExpectedException.none();

	@Test
	public void testCountBadStartInput() {
		exceptionRule.expect(DateTimeParseException.class);
		exceptionRule.expectMessage(
				"Text '25:15:00' could not be parsed: Invalid value for HourOfDay (valid values 0 - 23): 25");
		TimePatternCounter tCal = new TimePatternCounter();
		tCal.count("25:15:00", "17:00:00");
	}

	@Test
	public void testCountBadEndInput() {
		exceptionRule.expect(DateTimeParseException.class);
		exceptionRule.expectMessage("Text '17:00:70' could not be parsed");
		TimePatternCounter tCal = new TimePatternCounter();
		tCal.count("16:15:00", "17:00:70");
	}

}
