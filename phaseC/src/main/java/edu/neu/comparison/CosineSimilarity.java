package edu.neu.comparison;

import java.util.*;

/**
 * Calculate the cosine similarity between two AST trees
 *
 * @author Junhao Qu
 * @see https://en.wikipedia.org/wiki/Cosine_similarity
 */
public class CosineSimilarity {

    public double cosSim(ArrayList<String> tree1, ArrayList<String> tree2) {

        // Build a bow
        Set<String> bow = new HashSet<>();
        bow.addAll(tree1);
        bow.addAll(tree2);

        // Build two vectors
        List<String> newBow = new ArrayList<>();
        newBow.addAll(bow);
        int[] vec1 = new int[newBow.size()];
        int[] vec2 = new int[newBow.size()];
        for (int i = 0; i < newBow.size(); i++) {
            vec1[i] = tree1.contains(newBow.get(i)) ? 1 : 0;
            vec2[i] = tree2.contains(newBow.get(i)) ? 1 : 0;
        }

        // Calculate result
        int up = crossMul(vec1, vec2);
        double down = square(vec1) * square(vec2);
        return up / down;
    }

    private int crossMul(int[] A, int[] B) {
        int res = 0;
        for (int i = 0; i < A.length; i++) {
            res += A[i] * B[i];
        }
        return res;
    }

    private double square(int[] A) {
        int res = 0;
        for (int i : A) {
            res += i * i;
        }
        return Math.sqrt(res);
    }

}
