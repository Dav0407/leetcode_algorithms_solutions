package yandex_problems;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Game {

    public static int[] finalPosition(String commands) {
        int x = 0;
        int direction = 1;

        for (char command : commands.toCharArray()) {
            if (command == 'F') x += direction;
            else if (command == 'R') direction = 1;
            else if (command == 'L') direction = -1;
        }
        return new int[]{x, direction};
    }

    public static int countPossiblePositions(int N, String commands) {
        Set<Integer> positions = new HashSet<>();

        for (int i = 0; i < N; i++) {
            char originalCommand = commands.charAt(i);
            for (char newCommand : new char[]{'F', 'R', 'L'}) {
                if (newCommand == originalCommand) continue;
                String newCommands = commands.substring(0, i) + newCommand + commands.substring(i + 1);
                int[] result = finalPosition(newCommands);
                positions.add(result[0]);
            }
        }
        return positions.size();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        scanner.nextLine();
        String commands = scanner.nextLine();
        int result = countPossiblePositions(N, commands);
        System.out.println(result);
        scanner.close();
    }

}
