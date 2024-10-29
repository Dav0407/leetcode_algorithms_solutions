package yandex_problems;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

class Segment {
    int start, end;

    Segment(int start, int end) {
        this.start = start;
        this.end = end;
    }
}

public class NonIntersectingSegments {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());

        List<Segment> segments = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String[] input = scanner.nextLine().split(" ");
            int start = Integer.parseInt(input[0]);
            int end = Integer.parseInt(input[1]);
            segments.add(new Segment(start, end));
        }

        segments.sort((a, b) -> {
            if (a.start == b.start) return Integer.compare(a.end, b.end);
            return Integer.compare(a.start, b.start);
        });

        List<Integer> endPoints = new ArrayList<>();
        int nonIntersectingCount = 0;

        for (Segment seg : segments) {
            while (!endPoints.isEmpty() && endPoints.get(0) < seg.start) endPoints.remove(0);
            if (endPoints.isEmpty()) nonIntersectingCount++;
            endPoints.add(seg.end);
            Collections.sort(endPoints);
        }

        System.out.println(nonIntersectingCount);
    }
}
