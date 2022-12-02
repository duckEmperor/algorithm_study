package 제진명.des2022;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Programmers_신고결과받기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String input = br.readLine();
        input = input.substring(2, input.length() - 2);

        String [] id_list = input.split("\", \"");

        input = br.readLine();
        input = input.substring(2, input.length() - 2);

        String [] report = input.split("\",\"");

        int k = Integer.parseInt(br.readLine());

        System.out.println(Arrays.toString(solution(id_list, report, k)));
    }

    static int[] solution(String[] id_list, String[] report, int k){
        int [] reportedCnt = new int[id_list.length];
        ArrayList<String> [] reportIds = new ArrayList [id_list.length];
        HashMap<String, Integer> index = new HashMap<>();
        int [] answer = new int[id_list.length];

        for (int i = 0 ; i < id_list.length ; i++) {
            index.put(id_list[i], i);
            reportIds[i] = new ArrayList<>();
        }

        for (int i = 0 ; i < report.length ; i++) {
            StringTokenizer st = new StringTokenizer(report[i]);
            String reporter = st.nextToken();
            String reported = st.nextToken();


            if (reportIds[index.get(reporter)].contains(reported)) continue;
            reportedCnt[index.get(reported)]++;
            reportIds[index.get(reporter)].add(reported);
        }

        for (int i = 0 ; i < reportIds.length ; i++) {
            for (int j = 0 ; j < reportIds[i].size() ; j++) {
                if (reportedCnt[index.get(reportIds[i].get(j))] >= k) answer[i]++;
            }
        }

        return answer;
    }
}