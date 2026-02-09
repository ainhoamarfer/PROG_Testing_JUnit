package ejercicio01;

import java.util.Locale;

public final class NormalizadorNombreCompleto {

	public NormalizadorNombreCompleto() {
	}

	public String normalize(String raw) {
		if (raw == null) {
			throw new IllegalArgumentException("raw cannot be null");
		}
		String trimmed = raw.trim();
		if (trimmed.isEmpty()) {
			throw new IllegalArgumentException("raw cannot be blank");
		}

		String[] parts = trimmed.split("\\s+");
		StringBuilder out = new StringBuilder();
		for (String part : parts) {
			if (part.isEmpty()) {
				continue;
			}
			String lower = part.toLowerCase(Locale.ROOT);
			String normalized;
			if (lower.length() == 1) {
				normalized = lower.toUpperCase(Locale.ROOT);
			} else {
				normalized = lower.substring(0, 1).toUpperCase(Locale.ROOT) + lower.substring(1);
			}
			if (out.length() > 0) {
				out.append(' ');
			}
			out.append(normalized);
		}

		if (out.length() == 0) {
			throw new IllegalArgumentException("raw cannot be blank");
		}
		return out.toString();
	}
}
