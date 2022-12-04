package A202211;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class BJ_숫자카드_10815 {
	static int N;
	static List<Integer> numList;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		N = Integer.parseInt(br.readLine());
		numList = new ArrayList<>();
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			numList.add(Integer.parseInt(st.nextToken()));
		}
		Collections.sort(numList);
		int Q = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		while (Q-- > 0) {
			if (binarySearch(Integer.parseInt(st.nextToken())))
				sb.append(1 + " ");
			else
				sb.append(0 + " ");
		}
		System.out.println(sb);
	}

	private static boolean binarySearch(int x) {
		int min = 0;
		int max = N - 1;
		int mid = 0;
		while (min <= max) {
			mid = (min + max) / 2;
			if (numList.get(mid) < x)
				min = mid + 1;
			else if (numList.get(mid) > x)
				max = mid - 1;
			else
				return true;
		}
		return false;
	}
}
