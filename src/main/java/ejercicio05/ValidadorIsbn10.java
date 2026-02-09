package ejercicio05;

public final class ValidadorIsbn10 {

	public ValidadorIsbn10() {
	}

	public boolean isValidIsbn10(String isbn) {
		if (isbn == null) {
			return false;
		}

		String normalized = isbn.replace("-", "").replace(" ", "");
		if (normalized.length() != 10) {
			return false;
		}

		int sum = 0;
		for (int i = 0; i < 10; i++) {
			char c = normalized.charAt(i);
			int value;
			if (i == 9 && (c == 'X' || c == 'x')) {
				value = 10;
			} else if (Character.isDigit(c)) {
				value = c - '0';
			} else {
				return false;
			}

			if (i < 9 && value == 10) {
				return false;
			}

			sum += (i + 1) * value;
		}

		return sum % 11 == 0;
	}
}
