package A202208;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

public class BJ_1041_주사위 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		long N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		long[] num = new long[] {Integer.parseInt(st.nextToken()),
							Integer.parseInt(st.nextToken()),
							Integer.parseInt(st.nextToken()),
							Integer.parseInt(st.nextToken()),
							Integer.parseInt(st.nextToken()),
							Integer.parseInt(st.nextToken())};
		if(N==1) {
			Arrays.sort(num);
			System.out.println(num[0]+num[1]+num[2]+num[3]+num[4]);
			return;
		}
		List<List> occList = new ArrayList<>();
		
		int number[] = new int[3];
		for(int i = 0 ; i < 6; i++) {
			number[0] = i;
			for(int j = 0; j < 6; j++) {
				if(i==j || i+j == 5)continue;
				number[1] = j;
				for(int z = 0; z < 6; z++) {
					if(i==z || j==z || i+z == 5 || j+z == 5)continue;
					number[2] = z;
					List<Integer> occ = Arrays.stream(number)
						      .boxed()
						      .collect(Collectors.toList());
					occList.add(occ);
				}
			}
		}
		long ans = Long.MAX_VALUE;
		for(List<Integer> arr : occList) {
			long tmp = 0;
			N--;
			tmp += 4*(N*N*num[arr.get(0)] + N*num[arr.get(1)]); //bottom
			N--;
			tmp += N*N*num[arr.get(0)] + 4*(N+1)*(num[arr.get(0)]+num[arr.get(1)]) + 4*num[arr.get(2)];//top
			N+=2;
			ans = Math.min(ans, tmp);
		}
		
		System.out.println(ans);
	}

}