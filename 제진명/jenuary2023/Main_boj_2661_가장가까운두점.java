package 제진명.jenuary2023;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main_boj_2661_가장가까운두점 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        ArrayList<Point> dots = new ArrayList<>();

        for (int i = 0 ; i < N ; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            dots.add(new Point(x, y));
        }

        Collections.sort(dots, (d1, d2) -> d1.x - d2.x);

        System.out.println(closestPair(dots, 0, N - 1));
    }

    static int getDist (Point d1, Point d2) {
        return (int) Math.pow(d1.x - d2.x, 2) + (int) Math.pow(d1.y - d2.y, 2);
    }

    static int getMinimumDist (int start, int end, ArrayList<Point> dots) {
        int dist = Integer.MAX_VALUE;
        for (int i = start ; i < end ; i++) {
            for (int j = i+1 ; j <= end ; j++) {
                dist = Math.min(dist, getDist(dots.get(i), dots.get(j)));
            }
        }

        return dist;
    }

    static int closestPair(ArrayList<Point> dots, int start, int end) {
        if (end - start < 3) {
            return getMinimumDist(start, end, dots);
        }

        int mid = (start + end) / 2;

        int dist = Math.min(closestPair(dots, start, mid), closestPair(dots, mid + 1, end));

        ArrayList<Point> closerThanDistDots = new ArrayList<>();

        for (int i = start ; i <= end ; i++) {
            if (Math.pow(dots.get(mid).x - dots.get(i).x, 2) < dist) {
                closerThanDistDots.add(dots.get(i));
            }
        }

        Collections.sort(closerThanDistDots, (d1, d2) -> d1.y - d2.y);

        for (int i = 0 ; i < closerThanDistDots.size() - 1 ; i++) {
            for (int j = i + 1 ; j < closerThanDistDots.size() ; j++) {
                if (Math.pow(closerThanDistDots.get(i).y - closerThanDistDots.get(j).y, 2) >= dist) break;
                dist = Math.min(dist, getDist(closerThanDistDots.get(i), closerThanDistDots.get(j)));
            }
        }

        return dist;
    }
}
