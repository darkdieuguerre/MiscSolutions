import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.io.*;
import java.math.*;
import java.text.*; 
import java.util.*;
import java.util.regex.*;
/*
	  br = new BufferedReader(new FileReader("input.txt"));
	  pw = new PrintWriter(new BufferedWriter(new FileWriter("output.txt")));
	  br = new BufferedReader(new InputStreamReader(System.in));
	  pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
 */

public class Doom {
	private static BufferedReader br;
	private static StringTokenizer st;
	private static PrintWriter pw;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		//int qq = 1;
		//int qq = Integer.MAX_VALUE;
		int qq = readInt();
		for(int casenum = 1; casenum <= qq; casenum++)	{
			int n = readInt();
			long[] dp = new long[101];
			Arrays.fill(dp, Integer.MAX_VALUE);
			dp[100] = 0;
			while(n-- > 0) {
				long[] next = new long[101];
				Arrays.fill(next, Integer.MAX_VALUE);
				int k = readInt();
				while(k-- > 0) {
					int time = readInt();
					int add = -readInt();
					for(int i = 0; i < dp.length; i++) {
						if(i+add <= 0) continue;
						next[Math.min(100, i+add)] = Math.min(next[Math.min(100, i+add)], dp[i] + time);
					}
				}
				dp = next;
			}
			long ret = Integer.MAX_VALUE;
			for(long out: dp) {
				ret = Math.min(ret, out);
			}
			String ans = ret == Integer.MAX_VALUE ? "IMPOSSIBLE" : Long.toString(ret);
			pw.println("Case " + casenum + ": " + ans);
		}	
		exitImmediately();
	}
	
	private static void exitImmediately() {
		pw.close();
		System.exit(0);
	}

	private static long readLong() throws IOException {
		return Long.parseLong(nextToken());
	}

	private static double readDouble() throws IOException {
		return Double.parseDouble(nextToken());
	}

	private static int readInt() throws IOException {
		return Integer.parseInt(nextToken());
	}

	private static String nextLine() throws IOException  {
		if(!br.ready()) {
			exitImmediately();
		}
		st = null;
		return br.readLine();
	}

	private static String nextToken() throws IOException  {
		while(st == null || !st.hasMoreTokens())  {
			if(!br.ready()) {
				exitImmediately();
			}
			st = new StringTokenizer(br.readLine().trim());
		}
		return st.nextToken();
	}
}
