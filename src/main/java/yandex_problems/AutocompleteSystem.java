package yandex_problems;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class TreeNode {
    Map<Character, TreeNode> children;
    int maxPopularity;
    int maxPopularityIndex;

    TreeNode() {
        children = new HashMap<>();
        maxPopularity = 0;
        maxPopularityIndex = -1;
    }
}

class Tree {
    private final TreeNode root;

    Tree() {
        root = new TreeNode();
    }

    void insert(String word, int index, int popularity) {
        TreeNode node = root;
        for (char c : word.toCharArray()) {
            node.children.putIfAbsent(c, new TreeNode());
            node = node.children.get(c);
            if (node.maxPopularity < popularity) {
                node.maxPopularity = popularity;
                node.maxPopularityIndex = index;
            }
        }
    }

    int autocomplete(String prefix) {
        TreeNode node = root;
        for (char c : prefix.toCharArray()) {
            if (!node.children.containsKey(c)) {
                return -1;
            }
            node = node.children.get(c);
        }
        return node.maxPopularityIndex;
    }
}

public class AutocompleteSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int N = scanner.nextInt();
        int Q = scanner.nextInt();
        scanner.nextLine();

        Tree tree = new Tree();

        for (int i = 0; i < N; i++) {
            String[] line = scanner.nextLine().split(" ");
            String word = line[0];
            int popularity = Integer.parseInt(line[1]);
            tree.insert(word, i + 1, popularity);
        }

        StringBuilder t = new StringBuilder();
        for (int i = 0; i < Q; i++) {
            String query = scanner.nextLine();
            if (query.charAt(0) == '+') {
                t.append(query.charAt(2));
            } else if (query.charAt(0) == '-') {
                if (t.length() > 0) {
                    t.setLength(t.length() - 1);
                }
            }
            System.out.println(tree.autocomplete(t.toString()));
        }
    }
}
