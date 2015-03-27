/*
 * Copyright 2014 João Lourenço and Hampus Backman.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package group.project.unknown.utils;

/**
 * @author João Lourenço and Hampus Backman
 *
 */
public final class TimeUtils {

	private TimeUtils() {
	}

	public static double currentNanos() {
		return currentMicros() * 1000.0;
	}

	public static double currentMicros() {
		return currentMillis() * 1000.0;
	}

	public static double currentMillis() {
		return currentSeconds() * 1000.0;
	}

	public static double currentSeconds() {
		return System.currentTimeMillis() / 1000.0;
	}

	public static double currentTime(Unit unit) {
		switch (unit) {
		case NANOS:
			return currentNanos();
		case MICROS:
			return currentMicros();
		case MILLIS:
			return currentMillis();
		default:
			break;
		}

		return currentSeconds();
	}

	public static double currentTime() {
		return currentTime(getDefaultTimeUnit());
	}

	public static double convert(double time, Unit source, Unit target) {
		if (source == target) return time;

		double factor = 1;

		if (source == Unit.SECONDS) {
			if (target == Unit.MILLIS) factor = 1000.0;
			else if (target == Unit.MICROS) factor = 1000000.0;
			else factor = 1000000000.0;
		} else if (source == Unit.MILLIS) {
			if (target == Unit.SECONDS) factor = 1 / 1000.0;
			else if (target == Unit.MICROS) factor = 1000.0;
			else factor = 1000000.0;
		} else if (source == Unit.MICROS) {
			if (target == Unit.SECONDS) factor = 1 / 1000000.0;
			else if (target == Unit.MILLIS) factor = 1 / 1000.0;
			else factor = 1000.0;
		} else {
			if (target == Unit.SECONDS) factor = 1 / 1000000000.0;
			else if (target == Unit.MILLIS) factor = 1 / 1000000.0;
			else if (target == Unit.MICROS) factor = 1 / 1000.0;
		}

		return time * factor;
	}

	public static Unit getDefaultTimeUnit() {
		return Unit.SECONDS;
	}

	public static enum Unit {
		NANOS, MICROS, MILLIS, SECONDS
	}
}