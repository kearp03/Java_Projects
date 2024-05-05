package collatz;

public class Collatz {
	static long collatz(long n) {
		if(n % 2 == 1)
			return (3*n + 1);
		else return n/2;
	}

	static void collatzConjecture(long max, long[] counts) {
		int total = 0;
		for(int i = 1; i <= max; i++) {
			long n = i, count = 1;
			//System.out.print(n + " ");
			do {
				n = collatz(n);
				//System.out.print(n + " ");
				count++;
			}while(n != 1);
//			System.out.println(i + ": " + count);
			counts[i-1] = count;
			total += count;
		}
		long maxCount = counts[0];
		for(int i = 1; i < max; i++) {
			if(counts[i] > maxCount) maxCount = counts[i];
		}
		System.out.println("Max: " + maxCount);
		System.out.println("Average: " + total/max);
	}
	
	public static void main(String[] args) {
		long max = 10000000;
		long[] counts = new long[(int) max];
		collatzConjecture(max, counts);
	}
}
