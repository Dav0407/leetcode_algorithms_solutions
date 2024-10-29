import org.junit.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Tes {

    @Test
    public void test(){
        String filePath = "src/main/resources/input.txt";  // Change this to your file path

        try (
                BufferedReader reader = new BufferedReader(new FileReader(filePath));
                PrintWriter writer = new PrintWriter(System.out, true)
        ) {
            char firstChar = '$';
            int numberOfCandidates = Integer.parseInt(reader.readLine().trim());
            List<String> candidates = new ArrayList<>();

            // Read all candidates
            for (int i = 0; i < numberOfCandidates; i++) {
                candidates.add(reader.readLine().trim());
            }

            List<String> outputs = new ArrayList<>(numberOfCandidates);

            for (String candidate : candidates) {
                StringBuilder dayMonth = new StringBuilder();
                Set<Character> uniqueChars = new HashSet<>();
                int sumDigits = 0;
                firstChar = '$';

                // Process each character in the candidate string
                for (char c : candidate.toCharArray()) {
                    if (firstChar == '$' && !Character.isDigit(c) && c != ',') {
                        firstChar = c;
                    }
                    if (Character.isDigit(c)) {
                        if (dayMonth.length() < 3) {
                            sumDigits += Character.getNumericValue(c);
                            dayMonth.append(c);
                        }
                    } else if (c != ',') {
                        uniqueChars.add(c);
                    }
                }

                // Calculate values
                int uniqueCharsNumber = uniqueChars.size();
                int firstCharIndex = firstChar == '$' ? 0 : firstChar - 64;
                int output = uniqueCharsNumber + sumDigits * 64 + firstCharIndex * 256;

                // Convert output to hex and ensure at least 3 characters
                String outputHex = Integer.toHexString(output).toUpperCase();
                if (outputHex.length() < 3) {
                    outputHex = String.format("%3s", outputHex).replace(' ', '0');
                } else {
                    outputHex = outputHex.substring(outputHex.length() - 3);
                }

                outputs.add(outputHex);
            }

            // Print all outputs
            for (String o : outputs) {
                writer.print(o + " ");
            }
            writer.println(); // Ensure a new line at the end of output

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
