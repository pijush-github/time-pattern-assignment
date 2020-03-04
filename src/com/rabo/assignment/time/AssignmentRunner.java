package com.rabo.assignment.time;

public class AssignmentRunner {

	public static void main(String[] args) {

		TimePatternCounter timePatternCounter = new TimePatternCounter();
		int expectedCount = timePatternCounter.count(args[0], args[1]);
		System.out.println("Appearance of combination of 2 digits consecutively in the given range : " + expectedCount);

	}
}
