public class SumTo {

	/**
	 * Following program computes the sum of multiples of 3 or 5 up to n.
	 * The sum of multiples of 3, ie 3+6+9..etc can be written as
	 * 3*(1+2+3+4..x), where x is the number of multiples of 3 less than n.
	 * The sum of (1+2+3+..x) can be written as (1+x)*x/2
	 * Same thing applies to multiples of 5.
	 * To prevent over counting, the multiples of 15 are subtracted.
	 * @param args[0] = n
	 */
	public static void main(String[] args) {
		int n = Integer.parseInt(args[0]);
		if (n < 1) {
			System.out.print("Input must be a positive integer");
		} else {
			int num_three = (n - 1) / 3; // Number of multiples of 3 less than n
			int num_five = (n - 1) / 5; // Number of multiples of 5 less than n
			int num_fifteen = (n - 1) / 15; // Number of multiples of 15 less
											// than n
			int sum = 3 * (1 + num_three) * num_three / 2 + 5 * (1 + num_five)
					* num_five / 2 - 15 * (1 + num_fifteen) * num_fifteen / 2;
			System.out.print(sum);
		}
	}

}
