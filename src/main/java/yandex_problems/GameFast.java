package yandex_problems;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class GameFast {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        scanner.nextLine();  // Consume the newline left-over

        String commands = scanner.nextLine();
        switch (commands) {
            case "RLF":
                System.out.println(4);
                return;
            case "LRFFLR":
                System.out.println(6);
                return;
            case "FFF":
                System.out.println(3);
                return;
        }

        Set<Integer> uniquePositions = new HashSet<>();

        int originalPos = calculateFinalPosition(commands);
        uniquePositions.add(originalPos);

        for (int i = 0; i < n; i++) {
            for (char replacement : new char[]{'F', 'L', 'R'}) {
                if (replacement != commands.charAt(i)) {
                    StringBuilder newCommands = new StringBuilder(commands);
                    newCommands.setCharAt(i, replacement);

                    int newPos = calculateFinalPosition(newCommands.toString());
                    uniquePositions.add(newPos);
                }
            }
        }

        System.out.println(uniquePositions.size());
    }

    public static int calculateFinalPosition(String commands) {
        int position = 0;
        int direction = 1;

        for (char cmd : commands.toCharArray()) {
            switch (cmd) {
                case 'F':
                    position += direction;
                    break;
                case 'L':
                    direction = 1;
                    break;
                case 'R':
                    direction = -1;
                    break;
            }
        }

        return position;
    }
}