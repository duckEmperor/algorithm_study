package A202208;

public class BJ_4673_셀프넘버 {
	static boolean[] num;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) {
		num = new boolean[10001];
		
		for(int i = 1; i <= 10000; i++) {
			if(!num[i]) {
				sb.append(i).append("\n");
				selfNumber(i);
			}
		}
		System.out.println(sb.toString());
	}
	private static void selfNumber(int n) {
		if(n > 10000 || num[n])return;
		num[n] = true;
		int tmp = n;
		int four = tmp / 1000;
		tmp %= 1000;
		int three = tmp / 100;
		tmp %= 100;
		int two = tmp / 10;
		tmp %= 10;
		selfNumber(n + four + three + two + tmp);
	}

}
