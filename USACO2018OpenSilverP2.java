/*
 * USACO 2018 Open Silver P2: Lemonade Line
 * Date: 12/22/2024
 * Comments: Quick ans straightforward sorting + reverse looping
 */

import java.io.*;
import java.util.*;

public class USACO2018OpenSilverP2 {
	static BufferedReader br;
	static PrintWriter pw;
	static StringTokenizer in;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new FileReader("lemonade.in"));
		pw = new PrintWriter(new FileWriter("lemonade.out"));

		int n = readInt();
		int[] a = new int[n];
		for (int i = 0; i < n; i++) {
			a[i] = readInt();
		}
		Arrays.sort(a);
		int cnt = 0;

		for (int i = n - 1; i >= 0; i--) {
			if (cnt <= a[i])
				cnt++;
			else
				break;
		}
		pw.println(cnt);
		pw.close();
	}

	// Template
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