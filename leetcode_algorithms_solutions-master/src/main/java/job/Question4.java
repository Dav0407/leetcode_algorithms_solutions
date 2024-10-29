package job;

import java.util.*;

// K deganda men Azizbek oylagan son necha xonaligini chundim
public class Question4 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int N = scanner.nextInt();
        Set<Character> digits = new HashSet<>();
        for (int i = 0; i < N; i++) {
            digits.add(scanner.next().charAt(0));
        }
        int K = scanner.nextInt();

        String result = findLuckyNumber(digits, K);
        System.out.println(result);
    }

    public static String findLuckyNumber(Set<Character> digits, int K) {
        List<Character> sortedDigits = new ArrayList<>(digits);
        Collections.sort(sortedDigits);
        Random random = new Random();
        StringBuilder luckyNumber = new StringBuilder();

        for (int i = 0; i < K; i++) {
            char randomDigit = sortedDigits.get(random.nextInt(sortedDigits.size()));
            luckyNumber.append(randomDigit);
        }
        return luckyNumber.toString();
    }

}
