package ejercicio06;

import java.util.ArrayList;
import java.util.List;

public final class ParserCsvLinea {

	public ParserCsvLinea() {
	}

	public List<String> parseLine(String line) {
		if (line == null) {
			throw new IllegalArgumentException("line cannot be null");
		}

		List<String> fields = new ArrayList<>();
		StringBuilder current = new StringBuilder();
		boolean inQuotes = false;

		for (int i = 0; i < line.length(); i++) {
			char ch = line.charAt(i);
			if (inQuotes) {
				if (ch == '"') {
					if (i + 1 < line.length() && line.charAt(i + 1) == '"') {
						current.append('"');
						i++;
					} else {
						inQuotes = false;
					}
				} else {
					current.append(ch);
				}
			} else {
				if (ch == ',') {
					fields.add(current.toString());
					current.setLength(0);
				} else if (ch == '"') {
					if (current.length() != 0) {
						throw new IllegalArgumentException("quote in middle of field");
					}
					inQuotes = true;
				} else {
					current.append(ch);
				}
			}
		}

		if (inQuotes) {
			throw new IllegalArgumentException("unterminated quotes");
		}

		fields.add(current.toString());
		return fields;
	}
}
