package yandex_problems;

import java.util.HashSet;
import java.util.Set;
import java.util.Scanner;

public class PlatformerGame {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        String commands = scanner.next();
        scanner.close();

        System.out.println(findPossiblePositions(N, commands));
    }

    private static int findPossiblePositions(int N, String commands) {
        Set<Position> possiblePositions = new HashSet<>();

        // Calculate position with the original commands
        Position originalPosition = getFinalPosition(commands);
        possiblePositions.add(originalPosition);

        // Try changing each command to 'F', 'R', 'L' and calculate the resulting positions
        for (int i = 0; i < N; i++) {
            for (char newCmd : "FRL".toCharArray()) {
                if (commands.charAt(i) == newCmd) {
                    continue;
                }
                String newCommands = commands.substring(0, i) + newCmd + commands.substring(i + 1);
                Position newPosition = getFinalPosition(newCommands);
                possiblePositions.add(newPosition);
            }
        }

        return possiblePositions.size();
    }

    private static Position getFinalPosition(String commands) {
        int direction = 0; // 0 = right, 1 = down, 2 = left, 3 = up
        int x = 0, y = 0;

        for (char cmd : commands.toCharArray()) {
            switch (cmd) {
                case 'F':
                    if (direction == 0) x++;
                    else if (direction == 1) y--;
                    else if (direction == 2) x--;
                    else if (direction == 3) y++;
                    break;
                case 'R':
                    direction = (direction + 1) % 4;
                    break;
                case 'L':
                    direction = (direction - 1 + 4) % 4;
                    break;
            }
        }

        return new Position(x, y);
    }

    // Position class to store coordinates and ensure uniqueness in the set
    private static class Position {
        int x, y;

        Position(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Position position = (Position) o;
            return x == position.x && y == position.y;
        }

        @Override
        public int hashCode() {
            return 31 * x + y;
        }
    }
}

