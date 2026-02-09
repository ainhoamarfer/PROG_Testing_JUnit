package ejercicio03;

import java.math.BigDecimal;
import java.math.RoundingMode;

public final class CalculadoraPrecioFinal {

	public CalculadoraPrecioFinal() {
	}

	public double calculate(double basePrice, double discountPercent, double vatPercent, RoundingMode roundingMode) {
		validateFinite(basePrice, "basePrice");
		validateFinite(discountPercent, "discountPercent");
		validateFinite(vatPercent, "vatPercent");

		if (basePrice < 0) {
			throw new IllegalArgumentException("basePrice cannot be negative");
		}
		if (discountPercent < 0 || discountPercent > 100) {
			throw new IllegalArgumentException("discountPercent out of range");
		}
		if (vatPercent < 0 || vatPercent > 100) {
			throw new IllegalArgumentException("vatPercent out of range");
		}

		double discounted = basePrice * (1.0 - (discountPercent / 100.0));
		double taxed = discounted * (1.0 + (vatPercent / 100.0));

		return BigDecimal.valueOf(taxed).setScale(2, roundingMode).doubleValue();
	}

	private void validateFinite(double value, String name) {
		if (Double.isNaN(value) || Double.isInfinite(value)) {
			throw new IllegalArgumentException(name + " must be finite");
		}
	}
}
