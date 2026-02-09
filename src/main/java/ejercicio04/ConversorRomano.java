package ejercicio04;

import java.util.HashMap;
import java.util.Map;

public final class ConversorRomano {

	private final Map<Character, Integer> values;

	public ConversorRomano() {
		Map<Character, Integer> map = new HashMap<>();
		map.put('I', 1);
		map.put('V', 5);
		map.put('X', 10);
		map.put('L', 50);
		map.put('C', 100);
		map.put('D', 500);
		map.put('M', 1000);
		this.values = map;
	}

	public int toInt(String roman) {
		if (roman == null) {
			throw new IllegalArgumentException("roman cannot be null");
		}
		String input = roman.trim().toUpperCase();
		if (input.isEmpty()) {
			throw new IllegalArgumentException("roman cannot be empty");
		}

		int total = 0;
		int i = 0;
		while (i < input.length()) {
			char c = input.charAt(i);
			Integer v = values.get(c);
			if (v == null) {
				throw new IllegalArgumentException("invalid roman character");
			}
			int current = v;

			if (i + 1 < input.length()) {
				char nextChar = input.charAt(i + 1);
				Integer next = values.get(nextChar);
				if (next == null) {
					throw new IllegalArgumentException("invalid roman character");
				}
				if (next > current) {
					total += (next - current);
					i += 2;
					continue;
				}
			}

			total += current;
			i++;
		}

		if (total < 1 || total > 3999) {
			throw new IllegalArgumentException("roman out of range");
		}

		String canonical = fromInt(total);
		if (!canonical.equals(input)) {
			throw new IllegalArgumentException("invalid roman format");
		}
		return total;
	}

	public String fromInt(int value) {
		if (value < 1 || value > 3999) {
			throw new IllegalArgumentException("value out of range");
		}
		int remaining = value;
		StringBuilder out = new StringBuilder();

		int[] numbers = { 1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1 };
		String[] symbols = { "M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I" };

		for (int i = 0; i < numbers.length; i++) {
			while (remaining >= numbers[i]) {
				out.append(symbols[i]);
				remaining -= numbers[i];
			}
		}

		return out.toString();
	}
}
