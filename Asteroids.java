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

public class Asteroids {
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
			int s = readInt();
			int[] x = new int[n];
			int[] y = new int[n];
			int[] r = new int[n];
			Map<State, ArrayList<Integer>> map = new HashMap<State, ArrayList<Integer>>();
			for(int i = 0; i < n; i++) {
				x[i] = readInt();
				y[i] = readInt();
				r[i] = readInt();
				State key = new State(x[i] / 100, y[i] / 100);
				if(!map.containsKey(key)) {
					map.put(key, new ArrayList<Integer>());
				}
				map.get(key).add(i);
			}
			int ret = 0;
			while(s-- > 0) {
				int ax = readInt();
				int ay = readInt();
				boolean can = false;
				int kx = ax / 100;
				int ky = ay / 100;
				for(int dx = -1; !can && dx <= 1; dx++) {
					for(int dy = -1; !can && dy <= 1; dy++) {
						State key = new State(kx + dx, ky + dy);
						ArrayList<Integer> value = map.get(key);
						if(value != null) {
							for(int out: value) {
								int xx = x[out] - ax;
								int yy = y[out] - ay;
								if(xx*xx+yy*yy <= r[out]*r[out]) {
									can = true;
									break;
								}
							}
						}
					}
				}
				if(can) {
					ret++;
				}
			}
			pw.println("Case " + casenum + ": " + ret);
		}	
		exitImmediately();
	}
	
	static class State {
		public int x,y;
		public State(int a, int b) {
			x=a;
			y=b;
		}
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + x;
			result = prime * result + y;
			return result;
		}
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			State other = (State) obj;
			if (x != other.x)
				return false;
			if (y != other.y)
				return false;
			return true;
		}
		
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
