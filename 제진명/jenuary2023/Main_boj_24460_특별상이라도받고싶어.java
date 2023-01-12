package 제진명.jenuary2023;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main_boj_24460_특별상이라도받고싶어 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        int [][] seats = new int[N][N];

        for (int i = 0 ; i < N ; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0 ; j < N ; j++) {
                seats[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.println(split(new Point(0, 0), N, seats));

    }


    static int split (Point p1, int k, int [][] seats) {

        if (k == 1) {
            return seats[p1.x][p1.y];
        }

        ArrayList<Integer> res = new ArrayList<>();

        for (int d = 0 ; d < 4 ; d++) {
            res.add(split (new Point(p1.x + d%2*(k/2), p1.y + d/2*(k/2)), k / 2, seats));
        }

        Collections.sort(res);

        return res.get(1);
    }
}
