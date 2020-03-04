package com.rabo.assignment.time;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class TimePatternCounter {

	public int count(final String startTime, final String endTime) {
		
		int appereanceCount = 0;
		final LocalTime startTimeRange = LocalTime.parse(startTime, DateTimeFormatter.ISO_TIME);
		final LocalTime endTimeRange = LocalTime.parse(endTime, DateTimeFormatter.ISO_TIME);

		LocalTime varHourTimeRange = startTimeRange;
		
		while (varHourTimeRange.isBefore(endTimeRange)) {
			if (varHourTimeRange.getHour() >= varHourTimeRange.getMinute()) {
				appereanceCount = appereanceCount + lookForSimilarMinutesMatch(varHourTimeRange, endTimeRange);
			}
			varHourTimeRange = varHourTimeRange.getHour() < 23 ? LocalTime.of(varHourTimeRange.getHour() + 1, 0)
					: endTimeRange;
		}
		return appereanceCount;
	}

	private int lookForSimilarMinutesMatch(final LocalTime varInputTimeRange, final LocalTime endTimeRange) {

		int minMatchCount = 0;
		LocalTime varMinutesTimeRange = varInputTimeRange;

		while (varMinutesTimeRange.getMinute() <= varInputTimeRange.getHour()) {
			if (varMinutesTimeRange.getMinute() >= varMinutesTimeRange.getSecond()) {
				minMatchCount = minMatchCount + lookForSimilarSecondsMatch(varMinutesTimeRange, endTimeRange);
			}
			varMinutesTimeRange = LocalTime.of(varMinutesTimeRange.getHour(), varMinutesTimeRange.getMinute() + 1, 0);
		}
		return minMatchCount;
	}

	private int lookForSimilarSecondsMatch(final LocalTime varInputTimeRange, final LocalTime endTimeRange) {

		int secMatchCount = 0;
		LocalTime varSecondsTimeRange = varInputTimeRange;
		while ((varSecondsTimeRange.getSecond() <= varInputTimeRange.getMinute())
				&& varSecondsTimeRange.compareTo(endTimeRange) <= 0) {
			if ((varSecondsTimeRange.getHour() == varSecondsTimeRange.getMinute())
					&& varSecondsTimeRange.getSecond() == 0) {
				secMatchCount++;
			}
			if ((varSecondsTimeRange.getHour() == varSecondsTimeRange.getMinute())
					&& (varSecondsTimeRange.getMinute() == varSecondsTimeRange.getSecond())) {
				secMatchCount++;
			}
			varSecondsTimeRange = LocalTime.of(varSecondsTimeRange.getHour(), varSecondsTimeRange.getMinute(),
					varSecondsTimeRange.getSecond() + 1);
		}
		return secMatchCount;
	}
}
