package ejercicio08;

import java.text.Normalizer;

public final class DetectorPalindromos {

	public DetectorPalindromos() {
	}

	public boolean isPalindrome(String text) {
		if (text == null) {
			return false;
		}

		String noAccents = Normalizer.normalize(text, Normalizer.Form.NFD).replaceAll("\\p{M}", "");
		StringBuilder normalized = new StringBuilder();
		for (int i = 0; i < noAccents.length(); i++) {
			char c = noAccents.charAt(i);
			if (Character.isLetterOrDigit(c)) {
				normalized.append(Character.toLowerCase(c));
			}
		}

		int left = 0;
		int right = normalized.length() - 1;
		while (left < right) {
			if (normalized.charAt(left) != normalized.charAt(right)) {
				return false;
			}
			left++;
			right--;
		}
		return true;
	}
}
