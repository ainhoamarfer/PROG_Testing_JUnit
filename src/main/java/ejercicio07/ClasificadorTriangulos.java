package ejercicio07;

public final class ClasificadorTriangulos {

	public enum TipoTriangulo {
		EQUILATERO, ISOCELES, ESCALENO
	}

	public ClasificadorTriangulos() {
	}

	public TipoTriangulo classify(double a, double b, double c) {
		validateSide(a, "a");
		validateSide(b, "b");
		validateSide(c, "c");

		if (a + b <= c || a + c <= b || b + c <= a) {
			throw new IllegalArgumentException("not a valid triangle");
		}

		boolean ab = Double.compare(a, b) == 0;
		boolean ac = Double.compare(a, c) == 0;
		boolean bc = Double.compare(b, c) == 0;

		if (ab && ac) {
			return TipoTriangulo.EQUILATERO;
		}
		if (ab || ac || bc) {
			return TipoTriangulo.ISOCELES;
		}
		return TipoTriangulo.ESCALENO;
	}

	private void validateSide(double side, String name) {
		if (Double.isNaN(side) || Double.isInfinite(side)) {
			throw new IllegalArgumentException(name + " must be finite");
		}
		if (side <= 0) {
			throw new IllegalArgumentException(name + " must be positive");
		}
	}
}
