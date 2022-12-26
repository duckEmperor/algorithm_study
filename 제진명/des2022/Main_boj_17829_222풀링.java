package 제진명.des2022;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main_boj_17829_222풀링 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        int [][] matrix = new int [N][N];

        for (int i = 0 ; i < N ; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0 ; j < N ; j++) {
                matrix[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        while (N != 1) {
            int [][] newMatrix = new int[N/2][N/2];
            for (int i = 0 ; i < N ; i += 2) {
                for (int j = 0 ; j < N ; j += 2) {
                    ArrayList<Integer> seq = new ArrayList<>();
                    for (int m = i ; m < i+2 ; m++) {
                        for (int n = j ; n < j+2 ; n++) {
                            seq.add(matrix[m][n]);
                        }
                    }
                    Collections.sort(seq);
                    newMatrix[i/2][j/2] = seq.get(2);
                }
            }

            N /= 2;

            matrix = newMatrix;
        }

        System.out.println(matrix[0][0]);
    }
}
