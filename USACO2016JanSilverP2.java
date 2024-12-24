/*
 * USACO 2016 Open Silver P2: Subsequences Summing to Sevens
 * Date: 12/24/2024
 * Comments: This should have been really quick but it wasn't because I didn't read the question properly.
 * I didn't see that the question asked for a consecutive group, which would have made things a lot easier.
 * I ended up trying to look at case work and recursion because I thought that the items didn't have to be consecutive.
 * Only after I opened the editorial did I realize that the question was meant to have consecutive groups.
 * Mostly what I ended up learning from this was that I should read better, because the idea in the question about
 * taking the first and last values for each remainder shouldn't have been too difficult to see I think even if I 
 * didn't read the editorial.
 */

import java.io.*;
import java.util.*;

public class USACO2016JanSilverP2 {
	static BufferedReader br;
	static PrintWriter pw;
	static StringTokenizer in;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new FileReader("div7.in"));
		pw = new PrintWriter(new FileWriter("div7.out"));

		int n = readInt();

		// Taking prefix sum
		long[] psa = new long[n + 1];
		for (int i = 1; i < n + 1; i++) {
			psa[i] = psa[i - 1] + readLong();
		}

		int[] first = new int[7];
		int[] last = new int[7];
		Arrays.fill(first, -1);
		Arrays.fill(last, -1);

		// Taking the first index that has each remainder when mod 7
		for (int i = 1; i <= n; i++) {
			int rem = (int) (psa[i] % 7);
			if (first[rem] == -1)
				first[rem] = i;
		}
		// Taking the last index that has each remainder when mod 7
		for (int i = n; i >= 1; i--) {
			int rem = (int) (psa[i] % 7);
			if (last[rem] == -1)
				last[rem] = i;
		}

		int ans = 0;
		// Checking each possible remainder, and taking the maximum difference between
		// first and last occurrence
		for (int i = 0; i < 7; i++) {
			ans = Math.max(ans, last[i] - first[i]);
		}

		pw.println(ans);
		pw.close();
	}

	static String next() throws IOException {
		while (in == null || !in.hasMoreTokens())
			in = new StringTokenizer(br.readLine());
		return in.nextToken();
	}

	static long readLong() throws IOException {
		return Long.parseLong(next());
	}

	static int readInt() throws IOException {
		return Integer.parseInt(next());
	}

	static double readDouble() throws IOException {
		return Double.parseDouble(next());
	}

	static char readChar() throws IOException {
		return next().charAt(0);
	}

	static String readLine() throws IOException {
		return br.readLine();
	}
}