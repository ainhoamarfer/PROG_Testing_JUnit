package ejercicio09;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public final class DateRange {

	private final LocalDate start;
	private final LocalDate end;

	public DateRange(LocalDate start, LocalDate end) {
		if (start == null) {
			throw new IllegalArgumentException("start cannot be null");
		}
		if (end == null) {
			throw new IllegalArgumentException("end cannot be null");
		}
		if (start.isAfter(end)) {
			throw new IllegalArgumentException("start cannot be after end");
		}
		this.start = start;
		this.end = end;
	}

	public LocalDate getStart() {
		return start;
	}

	public LocalDate getEnd() {
		return end;
	}

	public boolean contains(LocalDate date) {
		if (date == null) {
			throw new IllegalArgumentException("date cannot be null");
		}
		return !(date.isBefore(start) || date.isAfter(end));
	}

	public boolean overlaps(DateRange other) {
		if (other == null) {
			throw new IllegalArgumentException("other cannot be null");
		}
		return !(this.end.isBefore(other.start) || other.end.isBefore(this.start));
	}

	public long days() {
		return ChronoUnit.DAYS.between(start, end) + 1;
	}
}
