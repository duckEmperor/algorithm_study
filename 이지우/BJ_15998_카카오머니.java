package A202206;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BJ_15998_카카오머니 {
	static long GCF = Long.MAX_VALUE;
	static long max = 0;
	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        long [][] log = new long[N][2];
        //boolean[] getMoney = new boolean[N];
        ArrayList<Long> getMoneyArr = new ArrayList<>(); //가져온 금액들 저장
        for(int i = 0 ; i < N; i++) {
        	StringTokenizer st = new StringTokenizer(br.readLine());
        	log[i][0] = Long.parseLong(st.nextToken());
        	log[i][1] = Long.parseLong(st.nextToken());
        }
        
        for(int i = 0 ; i < N; i++) {
        	if(log[i][0] < 0) { //출금만 본다.
        		if(i == 0) { // 이거는 최초 로그에 출금인 경우다 
        			//getMoney[i] = true; //이때 돈을 가져온다고 표시하고
        			getMoneyArr.add(log[i][1] - log[i][0]); //가져온 금액을 저장한다.
        			max = Math.max(max, log[i][1]);
        		}else {//여기는 최초 로그가 아니기때문에 기존 금액을 고려해야한다.
        			if(log[i-1][1] < Math.abs(log[i][0])) { //이때만 돈을 가져와야한다.
        				//getMoney[i] = true;
        				getMoneyArr.add(log[i][1] - log[i][0] - log[i-1][1]);//가져온 금액을 저장한다.
        				max = Math.max(max, log[i][1]);
        			}else {
        				if(log[i-1][1] + log[i][0] != log[i][1]) {
        					System.out.println(-1);
        					return;
        				}
        			}
        		}
        	}else {
        		if(i == 0) {
        			if(log[i][0] != log[i][1]) {
            			System.out.println(-1);
            			return;
            		}
        		}else {
        			if(log[i][0] + log[i-1][1] != log[i][1]) {
            			System.out.println(-1);
            			return;
            		}
        		}
        	}
        }
        //1. 가져온 금액 최대공약수 구하기
        if(getMoneyArr.size() == 0) {
        	System.out.println(1);
        	return;
        }else if (getMoneyArr.size() == 1) {
        	GCF = getMoneyArr.get(0);
        }else {
        	GCF = getMoneyArr.get(0);
        	for(int i = 1; i < getMoneyArr.size(); i++) {
        		long next = getMoneyArr.get(i);
        		if(GCF > next) {
        			GCF = GCD(GCF, next);
        		}else if(GCF < next) {
        			GCF = GCD(next, GCF);
        		}
        	}
        }
//        System.out.println(getMoneyArr.toString());
//        System.out.println(GCF);
//        System.out.println(max);
        //2. 가져온 금액의 최소값 구하기
        if(max >= GCF) {
        	System.out.println(-1);
        }else {
        	System.out.println(GCF);
        }
        
	}
	private static long GCD(long a, long b) {
		if(b == 0)return a;
		else return GCD(b, a%b);
	}

}
