package codeSkeleton.core.collect.query;

public class WebQueryTimer {
	static long start = 0;

	public static void start() {
		start = System.currentTimeMillis();
	}

	public static double end() {
		return (System.currentTimeMillis() - start) * 1.00 /  1000;
	}
}
