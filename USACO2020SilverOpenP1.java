/*
 * USACO 2020 Open Silver P1: Social Distancing
 * Date: 12/22/2024
 * Comments: I think I got the idea for this fairly quickly with binary searching the answer, since I saw something
 * similar that Bruce showed once at Olympiads. Most of the issues came up with implementation, and I had a WA because
 * I missed that the distance between consecutive grass patches could be less than the distance you're currently checking.
 * Other than that it was one of my quicker solves, and I'm happy because it's the first USACO SILVER problem that I've solved.
 */

import java.io.*;
import java.util.*;

public class USACO2020SilverOpenP1 {
	static BufferedReader br;
	static PrintWriter pw;
	static StringTokenizer in;

	static Pair[] arr;
	static int n, m;

	public static void main(String[] args) throws IOException {
		// Older USACO Setup requires file I/O
		br = new BufferedReader(new FileReader("socdist.in"));
		pw = new PrintWriter(new BufferedWriter(new FileWriter("socdist.out")));

		// Reading input
		n = readInt();
		m = readInt();

		arr = new Pair[m];
		for (int i = 0; i < m; i++) {
			long a = readLong();
			long b = readLong();
			arr[i] = new Pair(a, b);
		}

		Arrays.sort(arr);

		// Binary Searching Possible Max Distances
		long lo = 1, hi = (long) 1e18, ans = (long) (1e18); // min and max positions are 1 and 1e18;

		while (lo <= hi) {
			long mid = (lo + hi) / 2;
			// if current value doesn't work, we need to shrink our max distance tested to
			// [lo, mid-1]
			if (!check(mid)) {
				ans = mid;
				hi = mid - 1;
			} else { // Otherwise, we test interval [mid+1, hi] to see if there are valid solutions
				lo = mid + 1;
			}
		}
		pw.println(ans - 1); // Resulting answer is one above the actual answer because we don't update the
								// answer in "else"
		pw.close();
	}

	public static boolean check(long dist) {
		int patch = 0; // stores the current patch we're on
		long last = arr[0].a; // stores placement of last cow

		for (int i = 1; i < n; i++) { // Loop through the number of cows
			long sum = last + dist; // the minimum possible next placement is the last placement + tested distance
									// value
			if (sum >= arr[patch].a && sum <= arr[patch].b) { // if this is within the current patch, continue loop
				last = sum;
				continue;
			}
			while (arr[patch].b < sum) { // moves to next patch until the sum is less than the right bound of the patch
				patch++;
				if (patch >= arr.length) // if we do not have enough patches, this distance is impossible
					return false;
			}
			if (sum >= arr[patch].a && sum <= arr[patch].b) { // if last + dist is within a patch, we set the last
																// placement to "sum"
				last = sum;
				continue;
			}
			last = arr[patch].a; // Otherwise the placement that maximizes remaining space is placing at the left
									// bound the patch
		}
		return true; // if we pass through the loop without hitting a false return, this distance is
						// valid
	}

	static class Pair implements Comparable<Pair> {
		long a, b;

		public Pair(long a, long b) {
			this.a = a;
			this.b = b;
		}

		public int compareTo(Pair p) {
			if (this.a < p.a)
				return -1;
			else if (this.a == p.a)
				return 0;
			else
				return 1;
		}

		public String toString() {
			return a + " " + b;
		}
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