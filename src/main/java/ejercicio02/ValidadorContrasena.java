package ejercicio02;

public final class ValidadorContrasena {

	public ValidadorContrasena() {
	}

	public boolean isValid(String password) {
		if (password == null) {
			return false;
		}
		if (password.length() < 8) {
			return false;
		}

		boolean hasUpper = false;
		boolean hasLower = false;
		boolean hasDigit = false;
		boolean hasSymbol = false;

		for (int i = 0; i < password.length(); i++) {
			char c = password.charAt(i);
			if (Character.isWhitespace(c)) {
				return false;
			}
			if (Character.isUpperCase(c)) {
				hasUpper = true;
			} else if (Character.isLowerCase(c)) {
				hasLower = true;
			} else if (Character.isDigit(c)) {
				hasDigit = true;
			} else {
				hasSymbol = true;
			}
		}

		return hasUpper && hasLower && hasDigit && hasSymbol;
	}
}
