package A202209;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BJ_1541_잃어버린괄호 {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();
		int len = str.length();
		int ans = 0;
		int idx = 0;
		int minusIndex = str.indexOf('-');
		if(minusIndex == -1) {
			for(int i = 0 ; i < len; i++ ) {
				if(str.charAt(i) == '+') {
					ans += Integer.parseInt(str.substring(idx, i));
					idx = i+1;
				}
			}
			ans += Integer.parseInt(str.substring(idx, len));
		}else {
			for(int i = 0 ; i < minusIndex; i++ ) {
				if(str.charAt(i) == '+') {
					ans += Integer.parseInt(str.substring(idx, i));
					idx = i+1;
				}
			}
			ans += Integer.parseInt(str.substring(idx, minusIndex));
			idx = minusIndex+1;
			for(int i = minusIndex+1 ; i < len; i++ ) {
				if(str.charAt(i) == '+' || str.charAt(i) == '-') {
					ans -= Integer.parseInt(str.substring(idx, i));
					idx = i+1;
				}
			}
			ans -= Integer.parseInt(str.substring(idx, len));
		}
		
		System.out.println(ans);
	}

}
