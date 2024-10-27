package yandex_school;

import java.util.Scanner;

public class Tasks {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String j, s;
        int count = 0;
        j = input.nextLine();
        s = input.nextLine();

        int k = 0;
        for (int i = 0; i < j.length(); i++) {
            if (s.length() == k) break;
            if(j.charAt(i) == s.charAt(k)) {
                count++;
            }
            k++;
        }

        System.out.println(count);


    }




}
