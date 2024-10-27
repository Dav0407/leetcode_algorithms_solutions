package job;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class Question2 {
    public static boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        Map<Character, Character> matchingBrackets = new HashMap<>();
        matchingBrackets.put(')', '(');
        matchingBrackets.put(']', '[');
        matchingBrackets.put('}', '{');
        for (char c : s.toCharArray()){
            if (matchingBrackets.containsValue(c)){
                stack.push(c);
            } else if (matchingBrackets.containsKey(c)) {
                if (stack.isEmpty() || stack.pop() != matchingBrackets.get(c))
                    return false;
            }
            else return false;
        }
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        System.out.println(isValid("()}{{}"));
        System.out.println(isValid("(){}[]"));
        System.out.println(isValid("(([]){})"));
    }
}
