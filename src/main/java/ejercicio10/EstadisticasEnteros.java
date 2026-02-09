package ejercicio10;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class EstadisticasEnteros {

	public EstadisticasEnteros() {
	}

	public Stats stats(List<Integer> values) {
		if (values == null) {
			throw new IllegalArgumentException("values cannot be null");
		}
		if (values.isEmpty()) {
			throw new IllegalArgumentException("values cannot be empty");
		}

		List<Integer> copy = new ArrayList<>(values.size());
		for (Integer v : values) {
			if (v == null) {
				throw new IllegalArgumentException("values cannot contain nulls");
			}
			copy.add(v);
		}

		int min = copy.get(0);
		int max = copy.get(0);
		long sum = 0L;
		for (int v : copy) {
			min = Math.min(min, v);
			max = Math.max(max, v);
			sum += v;
		}
		double mean = sum / (double) copy.size();

		Collections.sort(copy);
		double median;
		int n = copy.size();
		if (n % 2 == 1) {
			median = copy.get(n / 2);
		} else {
			long a = copy.get(n / 2 - 1);
			long b = copy.get(n / 2);
			median = (a + b) / 2.0;
		}

		return new Stats(min, max, mean, median);
	}

	public static final class Stats {
		private final int min;
		private final int max;
		private final double mean;
		private final double median;

		public Stats(int min, int max, double mean, double median) {
			this.min = min;
			this.max = max;
			this.mean = mean;
			this.median = median;
		}

		public int getMin() {
			return min;
		}

		public int getMax() {
			return max;
		}

		public double getMean() {
			return mean;
		}

		public double getMedian() {
			return median;
		}
	}
}
