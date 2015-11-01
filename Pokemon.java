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


public class Pokemon {
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
			int e = readInt();
			LinkedList<Edge>[] edges = new LinkedList[n];
			for(int i = 0; i < n; i++) {
				edges[i] = new LinkedList<Edge>();
			}
			while(e-- > 0) {
				int a = readInt();
				int b = readInt();
				double d = readDouble();
				edges[a].add(new Edge(b, d));
				edges[b].add(new Edge(a, d));
			}
			double[] dp = new double[n];
			dp[0] = 1;
			PriorityQueue<Vertex> q = new PriorityQueue<Vertex>();
			q.add(new Vertex(0, 1));
			while(!q.isEmpty()) {
				Vertex curr = q.poll();
				if(dp[curr.v] != curr.w) continue;
				for(Edge out: edges[curr.v]) {
					int nextD = out.d;
					double nextW = curr.w * out.w;
					if(nextW > dp[nextD]) {
						dp[nextD] = nextW;
						q.add(new Vertex(nextD, dp[nextD]));
					}
				}
			}
			pw.printf("Case %d: %.2f\n", casenum, dp[1]);
		}	
		exitImmediately();
	}
	
	static class Edge {
		public int d;
		public double w;
		public Edge(int a, double b) {
			d=a;
			w=b;
		}
	}
	
	static class Vertex implements Comparable<Vertex> {
		public int v;
		public double w;
		public Vertex(int a, double b) {
			v=a;
			w=b;
		}
		public int compareTo(Vertex curr) {
			return Double.compare(curr.w, w);
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
