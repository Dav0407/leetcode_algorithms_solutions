package leetcode_problems;

import java.nio.charset.CharsetEncoder;
import java.util.*;

public class LeetCodeProblems {
    /*
    Example 1:

    Input: arr = [2,6,4,1]
    Output: false
    Explanation: There are no three consecutive odds.

    Example 2:

    Input: arr = [1,2,34,3,4,5,7,23,12]
    Output: true
    Explanation: [5,7,23] are three consecutive odds.
    */
    public boolean threeConsecutiveOdds(int[] arr) {
        int j = 0;
        for (int i = 0; i < arr.length; i++) {
            if (i != arr.length - 1) {
                if (arr[i] % 2 != 0 && arr[i + 1] % 2 != 0)
                    j++;
                else
                    j = 0;
            } else return false;
        }
        return j == 2;
    }


    /*
    Example 1:

    Input: n = 2
    Output: [1,1]
    Explanation: Let a = 1 and b = 1.
    Both a and b are no-zero integers, and a + b = 2 = n.

            Example 2:

    Input: n = 11
    Output: [2,9]
    Explanation: Let a = 2 and b = 9.
    Both a and b are no-zero integers, and a + b = 9 = n.
    Note that there are other valid answers as [8, 3] that can be accepted.
    */

    public int[] getNoZeroIntegers(int n) {
        int[] noZeroIntegers = new int[2];
        int begin, end;
        for (int i = 1; i < n; i++) {
            begin = i;
            end = n - i;
            if (notContainsZero(begin) && notContainsZero(end)) {
                noZeroIntegers[0] = begin;
                noZeroIntegers[1] = end;
                break;
            }
        }
        return noZeroIntegers;
    }

    public boolean notContainsZero(int number) {
        int temp;
        while (number > 0) {
            temp = number % 10;
            number = number / 10;
            if (temp == 0)
                return false;
        }
        return true;
    }

    /*
    Example 1:

    Input: nums = [3,0,1]
    Output: 2
    Explanation: n = 3 since there are 3 numbers, so all numbers are in the range [0,3].
    2 is the missing number in the range since it does not appear in nums.

    Example 2:

    Input: nums = [0,1]
    Output: 2
    Explanation: n = 2 since there are 2 numbers, so all numbers are in the range [0,2].
    2 is the missing number in the range since it does not appear in nums.

    Example 3:

    Input: nums = [9,6,4,2,3,5,7,0,1]
    Output: 8
    Explanation: n = 9 since there are 9 numbers, so all numbers are in the range [0,9].
    8 is the missing number in the range since it does not appear in nums.
    */

    public int missingNumber2(int[] nums) {
        boolean numberExist = false;
        int missingNumber = 0, currentNumber = 0;
        for (int i = nums.length; i >= 0; i--) {
            currentNumber = i;
            for (int number : nums) {
                if (currentNumber == number) {
                    numberExist = true;
                    break;
                }
            }
            if (!numberExist)
                missingNumber = currentNumber;
            numberExist = false;
        }
        return missingNumber;
    }

    public int missingNumber(int[] nums) {
        int maxNumber = nums.length;
        int absoluteSum = 0;
        int sum = 0;
        while (maxNumber > 0) {
            absoluteSum += maxNumber;
            maxNumber--;
        }
        for (int num : nums) {
            sum += num;
        }
        return absoluteSum - sum;
    }

    /*
    Example 1:

    Input: nums = [1,5,0,3,5]
    Output: 3
    Explanation:
    In the first operation, choose x = 1. Now, nums = [0,4,0,2,4].
    In the second operation, choose x = 2. Now, nums = [0,2,0,0,2].
    In the third operation, choose x = 2. Now, nums = [0,0,0,0,0].

    Example 2:

    Input: nums = [0]
    Output: 0
    Explanation: Each element in nums is already 0 so no operations are needed.
    */

    public int minimumOperations(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            if (num == 0) continue;
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        return map.size();
    }

    /*
    Example 1:

    Input: s = "anagram", t = "nagaram"
    Output: true

    Example 2:

    Input: s = "rat", t = "car"
    Output: false
    */

    public boolean isAnagram(String s, String t) {

        if (s.length() != t.length()) return false;

        Map<Character, Integer> charFrequency = new HashMap<>();
        for (char character : s.toCharArray())
            charFrequency.put(character, charFrequency.getOrDefault(character, 0) + 1);

        for (char character : t.toCharArray()) {
            if (!charFrequency.containsKey(character)) return false;
            charFrequency.put(character, charFrequency.get(character) - 1);
            if (charFrequency.get(character) == 0)
                charFrequency.remove(character);
        }
        return charFrequency.isEmpty();
    }

    public boolean isAnagram2(String s, String t) {
        int[] count = new int[26];

        if (s.length() != t.length()) return false;

        for (char x : s.toCharArray()) {
            count[x - 'a']++;
        }

        for (char x : t.toCharArray()) {
            count[x - 'a']--;
        }

        for (int val : count) {
            if (val != 0) {
                return false;
            }
        }

        return true;
    }

    /*
    Example 1:

    Input: target = [1,2,3,4], arr = [2,4,1,3]
    Output: true
    Explanation: You can follow the next steps to convert arr to target:
            1- Reverse subarray [2,4,1], arr becomes [1,4,2,3]
            2- Reverse subarray [4,2], arr becomes [1,2,4,3]
            3- Reverse subarray [4,3], arr becomes [1,2,3,4]
    There are multiple ways to convert arr to target, this is not the only way to do so.

    Example 2:

    Input: target = [7], arr = [7]
    Output: true
    Explanation: arr is equal to target without any reverses.

    Example 3:

    Input: target = [3,7,9], arr = [3,7,11]
    Output: false
    Explanation: arr does not have value 9, and it can never be converted to target.
    */

    public boolean canBeEqual(int[] target, int[] arr) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int number : target)
            map.put(number, map.getOrDefault(number, 0) + 1);
        for (int number : arr) {
            if (map.containsKey(number))
                map.put(number, map.get(number) - 1);
            else
                return false;
            if (map.get(number) == 0)
                map.remove(number);
        }
        return map.isEmpty();
    }

    public boolean canBeEqualArray(int[] target, int[] arr) {
        int[] myArray = new int[1000 + 1];
        for (int i = 0; i < target.length; i++) {
            myArray[target[i]]++;
            myArray[arr[i]]--;
        }
        for (int number : myArray)
            if (number != 0) return false;

        return true;
    }

    /*
    Given an integer n, return a string array answer (1-indexed) where:

    answer[i] == "FizzBuzz" if i is divisible by 3 and 5.
    answer[i] == "Fizz" if i is divisible by 3.
    answer[i] == "Buzz" if i is divisible by 5.
    answer[i] == i (as a string) if none of the above conditions are true.

    Example 1:

    Input: n = 3
    Output: ["1","2","Fizz"]

    Example 2:

    Input: n = 5
    Output: ["1","2","Fizz","4","Buzz"]

    Example 3:

    Input: n = 15
    Output: ["1","2","Fizz","4","Buzz","Fizz","7","8","Fizz","Buzz","11","Fizz","13","14","FizzBuzz"]
    */

    public List<String> fizzBuzz(int n) {
        List<String> result = new ArrayList<>(n);
        for (int i = 1; i <= n; i++) {
            if (i % 3 == 0 && i % 5 == 0) result.add("FizzBuzz");

            else if (i % 3 == 0) result.add("Fizz");

            else if (i % 5 == 0) result.add("Buzz");

            else result.add(Objects.toString(i));
        }
        return result;
    }
    /*
    Example 1:

    Input: s = "A man, a plan, a canal: Panama"
    Output: true
    Explanation: "amanaplanacanalpanama" is a palindrome.

    Example 2:

    Input: s = "race a car"
    Output: false
    Explanation: "raceacar" is not a palindrome.

    Example 3:

    Input: s = " "
    Output: true
    Explanation: s is an empty string "" after removing non-alphanumeric characters.
    Since an empty string reads the same forward and backward, it is a palindrome.
    */

    public static boolean isPalindrome(String s) {
        if (s.isEmpty()) return true;
        s = s.toLowerCase();
        List<Character> chars = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
            if ((s.charAt(i) >= 65 && s.charAt(i) <= 90) || (s.charAt(i) >= 97 && s.charAt(i) <= 122) || (s.charAt(i) >= 48 && s.charAt(i) <= 57))
                chars.add(s.charAt(i));
        }
        int start = 0, end = chars.size() - 1;
        while (start < end) {
            if (chars.get(start) != chars.get(end)) {
                return false;
            }
            start++;
            end--;
        }
        return true;
    }

    public static boolean isPalindromeFaster(String s) {
        if (s.isEmpty()) return true;
        int left = 0, right = s.length() - 1;
        while (left < right) {
            char leftChar = s.charAt(left);
            char rightChar = s.charAt(right);
            if (!Character.isLetterOrDigit(leftChar)) {
                left++;
            } else if (!Character.isLetterOrDigit(rightChar)) {
                right--;
            } else {
                if (Character.toLowerCase(leftChar) != Character.toLowerCase(rightChar)) {
                    return false;
                }
                left++;
                right--;
            }
        }
        return true;
    }

    /*
    Example 1:

    Input: strs = ["flower","flow","flight"]
    Output: "fl"

    Example 2:

    Input: strs = ["dog","racecar","car"]
    Output: ""
    Explanation: There is no common prefix among the input strings.
    */

    public static String longestCommonPrefix(String[] strs) {
        StringBuilder common = new StringBuilder();
        Arrays.sort(strs);
        String first = strs[0];
        String last = strs[strs.length - 1];
        for (int i = 0; i < Math.min(first.length(), last.length()); i++) {
            if (first.charAt(i) != last.charAt(i))
                return common.toString();
            common.append(strs[0].charAt(i));
        }
        return common.toString();

    }

    /*
    Example 1:

    Input: nums = [2,2,1]
    Output: 1

    Example 2:

    Input: nums = [4,1,2,1,2]
    Output: 4

    Example 3:

    Input: nums = [1]
    Output: 1
    */

    public int singleNumber(int[] nums) {
        Map<Integer, Integer> frequency = new HashMap<>();
        for (int num : nums)
            frequency.put(num, frequency.getOrDefault(num, 0) + 1);

        for (int key : frequency.keySet())
            if (frequency.get(key) == 1) return frequency.get(key);
        return -1;
    }

    public int singleNumberFast(int[] nums) {
        int[] frequencyPositive = new int[3 * 10000 + 1];
        int[] frequencyNegative = new int[3 * 10000 + 1];
        for (int num : nums) {
            if (num >= 0) frequencyPositive[num]++;
            else frequencyNegative[-num]++;
        }

        for (int i = 0; i < frequencyPositive.length; i++) {
            if (frequencyPositive[i] == 1) return i;
            else if (frequencyNegative[i] == 1) return -i;
        }
        return -1;
    }


    /*
    Example 1:

    Input: nums = [1,2,3,1]
    Output: true

    Example 2:

    Input: nums = [1,2,3,4]
    Output: false

    Example 3:

    Input: nums = [1,1,1,3,3,4,3,2,4,2]
    Output: true
    */

    public static boolean containsDuplicate(int[] nums) {
        Map<Integer, Integer> frequency = new HashMap<>();

        for (int num : nums) {
            frequency.put(num, frequency.getOrDefault(num, 0) + 1);
            if (frequency.get(num) > 1) return true;
        }
        return false;
    }

    public static boolean containsDuplicateFast(int[] nums) {
        int[] frequencyPositive = new int[1_000_000_000];
        int[] frequencyNegative = new int[1_000_000_000];
        for (int num : nums) {
            if (num >= 0) frequencyPositive[num]++;
            else frequencyNegative[-num]++;
        }
        for (int i = 0; i < frequencyPositive.length; i++)
            if (frequencyPositive[i] > 1 || frequencyNegative[i] > 1) return true;
        return false;
    }

    public static boolean containsDuplicateSet(int[] nums) {
        Set<Integer> seen = new HashSet<>();
        for (int num : nums)
            if (!seen.add(num))
                return true;
        return false;
    }
    /*
    Example 1:

    Input: s = "leetcode"
    Output: 0

    Example 2:

    Input: s = "loveleetcode"
    Output: 2

    Example 3:

    Input: s = "aabb"
    Output: -1
    */

    public int firstUniqChar(String s) {
        int[] freq = new int[100_000 + 1];

        for (char c : s.toCharArray())
            freq[c]++;

        for (int i = 0; i < s.length(); i++)
            if (freq[s.charAt(i)] == 1) return i;

        return -1;
    }
    /*
    Example 1:

    Input: s = ["h","e","l","l","o"]
    Output: ["o","l","l","e","h"]

    Example 2:

    Input: s = ["H","a","n","n","a","h"]
    Output: ["h","a","n","n","a","H"]
    */

    public static void reverseString(char[] s) {
        char first, last;
        for (int i = 0; i < s.length; i++) {
            if (i > s.length - 1 - i) break;
            first = s[i];
            last = s[s.length - 1 - i];
            s[i] = last;
            s[s.length - 1 - i] = first;
        }
    }

    public static void reverseStringFast(char[] s) {
        int first = 0;
        int last = s.length - 1;
        while (first < last) {
            char temp = s[first];
            s[first] = s[last];
            s[last] = temp;
            first++;
            last--;
        }
    }
    /*
    Example 1:

    Input: n = 19
    Output: true
    Explanation:
            12 + 92 = 82
            82 + 22 = 68
            62 + 82 = 100
            12 + 02 + 02 = 1

    Example 2:

    Input: n = 2
    Output: false
    */

    public boolean isHappy(int n) {
        int slow = n;
        int fast = getDigitsSum(n);
        while (fast != 1 && fast != slow) {
            slow = getDigitsSum(slow);
            fast = getDigitsSum(getDigitsSum(fast));
        }
        return fast == 1;
    }

    public int getDigitsSum(int n) {
        int temp, sum = 0;
        while (n > 0) {
            temp = n % 10;
            n /= 10;
            sum += temp * temp;
        }
        return sum;
    }

    /*
    Example 1:

    Input: nums = [1,1,2]
    Output: 2, nums = [1,2,_]
    Explanation: Your function should return k = 2, with the first two elements of nums being 1 and 2 respectively.
    It does not matter what you leave beyond the returned k (hence they are underscores).

    Example 2:

    Input: nums = [0,0,1,1,1,2,2,3,3,4]
    Output: 5, nums = [0,1,2,3,4,_,_,_,_,_]
    Explanation: Your function should return k = 5, with the first five elements of nums being 0, 1, 2, 3, and 4 respectively.
    It does not matter what you leave beyond the returned k (hence they are underscores).
 */

    public int removeDuplicates(int[] nums) {
        if (nums.length == 0) return 0;
        int i = 0;
        for (int j = 1; j < nums.length; j++) {
            if (nums[j] != nums[i]) {
                i++;
                nums[i] = nums[j];
            }
        }
        return i + 1;
    }

    /*
    Example 1:

    Input: haystack = "sadbutsad", needle = "sad"
    Output: 0
    Explanation: "sad" occurs at index 0 and 6.
    The first occurrence is at index 0, so we return 0.

    Example 2:

    Input: haystack = "leetcode", needle = "leeto"
    Output: -1
    Explanation: "leeto" did not occur in "leetcode", so we return -1.
    */

    public int strStr(String haystack, String needle) {
        for (int i = 0; i <= haystack.length() - needle.length(); i++) {
            if (haystack.substring(i, i + needle.length()).equals(needle)) {
                return i;
            }
        }
        return -1;
    }

    /*
    Example 1:

    Input: n = 27
    Output: true
    Explanation: 27 = 33

    Example 2:

    Input: n = 0
    Output: false
    Explanation: There is no x where 3x = 0.

    Example 3:

    Input: n = -1
    Output: false
    Explanation: There is no x where 3x = (-1).
    */

    public boolean isPowerOfThree(int n) {
        var num = Math.log(n) / Math.log(3);
        return num == Math.floor(num);
    }

    /*
    Example 1:

    Input: prices = [7,1,5,3,6,4]
    Output: 5
    Explanation: Buy on day 2 (price = 1) and sell on day 5 (price = 6), profit = 6-1 = 5.
    Note that buying on day 2 and selling on day 1 is not allowed because you must buy before you sell.

    Example 2:

    Input: prices = [7,6,4,3,1]
    Output: 0
    Explanation: In this case, no transactions are done and the max profit = 0.
    */

    public int maxProfit(int[] prices) {
        int min = prices[0];
        int profit = 0;
        int maxProfit = 0;
        for (int price : prices) {
            if (price < min) {
                min = price;
            } else {
                profit = price - min;
                if (profit > maxProfit)
                    maxProfit = profit;
            }
        }
        return maxProfit;
    }

    //Binary search
    public int search(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                left = mid + 1;
            }
            else
            {
                right = mid - 1;
            }
        }
        return -1;
    }

    /*
    Example 1:

    Input: s = "()"

    Output: true

    Example 2:

    Input: s = "()[]{}"

    Output: true

    Example 3:

    Input: s = "(]"

    Output: false

    Example 4:

    Input: s = "([])"

    Output: true
    */

    public boolean isValid(String s) {
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
    }

}
