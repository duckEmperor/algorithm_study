package A202208;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BJ_2869_달팽이는올라가고싶다 {

	public static void main(String[] args) throws IOException {
		/*
		 * BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		 * BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		 * StringTokenizer st = new StringTokenizer(br.readLine()); int c =
		 * Integer.valueOf(st.nextToken()); int s = c-Integer.valueOf(st.nextToken());
		 * int t = Integer.valueOf(st.nextToken())-c;
		 * 
		 * int num = 1; num += t/s; if(t%s != 0)num++;
		 * 
		 * bw.write(String.valueOf(num)); bw.flush(); bw.close(); br.close();
		 */
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		int A = Integer.parseInt(st.nextToken());
		int B = Integer.parseInt(st.nextToken());
		int V = Integer.parseInt(st.nextToken());

		int day = (V - B) / (A - B);
		if ((V - B) % (A - B) != 0)
			day++;
		System.out.print(day);
	}

}
