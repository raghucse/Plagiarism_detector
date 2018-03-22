package edu.neu.comparison;

import java.util.ArrayList;

public class LVDistance {


    public static int calculateLD(ArrayList<String> tree1, ArrayList<String> tree2, int tree1Length, int tree2Length) {

        int cost[][] = new int[tree1Length + 1][tree2Length + 1];

        for (int i = 0; i <= tree1Length; i++) {
            for (int j = 0; j <= tree2Length; j++) {
                if (i == 0)
                    cost[i][j] = j;

                else if (j == 0)
                    cost[i][j] = i;

                else if (tree1.get(i - 1).equals(tree2.get(j - 1)))
                    cost[i][j] = cost[i - 1][j - 1];

                else
                    cost[i][j] = 1 + Math.min(cost[i - 1][j - 1], Math.min(cost[i][j - 1], cost[i - 1][j]));
            }
        }

        return cost[tree1Length][tree2Length];

    }

    public static void main(String[] args) {
        ArrayList<String> tree1 = new ArrayList<String>();
        ArrayList<String> tree2 = new ArrayList<String>();

        tree1.add("file_input");
        tree1.add("funcdef");
        tree1.add("parameters");
        tree1.add("suite");
        tree1.add("simple_stmt");
        tree1.add("atom_expr");
        tree1.add("atom");
        tree1.add("trailer");
        tree1.add("atom");
        tree1.add("simple_stmt");
        tree1.add("atom_expr");
        tree1.add("atom");
        tree1.add("trailer");

        tree2.add("file_input");
        tree2.add("funcdef");
        tree2.add("parameters");
        tree2.add("typedargslist");
        tree2.add("tfpdef");
        tree2.add("tfpdef");
        tree2.add("suite");
        tree2.add("simple_stmt");
        tree2.add("return_stmt");
        tree2.add("arith_expr");
        tree2.add("atom");
        tree2.add("atom");
        tree2.add("funcdef");
        tree2.add("parameters");
        tree2.add("suite");
        tree2.add("simple_stmt");
        tree2.add("atom_expr");
        tree2.add("atom");
        tree2.add("trailer");
        tree2.add("atom");
        tree2.add("simple_stmt");
        tree2.add("atom_expr");
        tree2.add("atom");
        tree2.add("trailer");


        int cost = LVDistance.calculateLD(tree1, tree2, tree1.size(), tree2.size());
        System.out.println(cost);

    }

}