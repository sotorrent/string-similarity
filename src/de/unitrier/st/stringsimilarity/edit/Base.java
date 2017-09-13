package de.unitrier.st.stringsimilarity.edit;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import static de.unitrier.st.stringsimilarity.Normalization.normalizeForEdit;

/*
 * Edit-based similarity metrics.
 *
 * All base metrics must return a value between 0.0 and 1.0.
 */
public class Base {
    public static final double THRESHOLD = 0.6; // TODO: set after evaluation

    /*
     * Jaro-Winkler excluded because it is optimized for single words.
     * See: https://en.wikipedia.org/wiki/Jaro%E2%80%93Winkler_distance
     */

    /*
     * Similarity metric based on Levenshtein distance.
     * Implements dynamic programming approach by Wagner–Fischer (space in O(min(n,m)), runtime in O(nm)).
     *
     * See: https://en.wikipedia.org/wiki/Levenshtein_distance
     * See: https://en.wikipedia.org/wiki/Wagner%E2%80%93Fischer_algorithm
     * See: https://github.com/tdebatty/java-string-similarity#levenshtein
     */
    static double levenshtein(String str1, String str2) {
        int n = str1.length();
        int m = str2.length();

        if (n==0 && m==0) {
            return 1.0; // empty strings have similarity 1
        }

        // ensure space in O(min(n,m))
        if (m>n) {
            String tmpStr = str1;
            str1 = str2;
            str2 = tmpStr;
            int tmpInt = n;
            n = m;
            m = tmpInt;
        }

        int[] current_row = new int[m+1];
        int[] previous_row = new int[m+1];

        // initialize first row
        for (int i=0; i<m+1; i++) {
            previous_row[i] = i; // str1 empty => str2 is i deletions aways from str1
        }

        for (int i=1; i<n+1; i++) {
            current_row[0] = i+1; // str1 is max. i+1 deletions away from str1

            for (int j=1; j<m+1; j++) {
                if (str1.charAt(i-1) == str2.charAt(j-1)) {
                    current_row[j] = previous_row[j-1];
                } else {
                    current_row[j] = Math.min(Math.min(
                            previous_row[j-1] + 1, // substitution
                            previous_row[j] + 1), // deletion
                            current_row[j-1] + 1 // insertion
                    );
                }
            }

            // current row is previous row in next iteration
            int[] tmp = previous_row;
            previous_row = current_row;
            current_row = tmp;
        }

        return (double) (Math.max(n, m) - previous_row[m]) / Math.max(n, m);
    }

    /*
     * Levenshtein base variants
     */

    // normalization
    static double levenshteinNormalized(String str1, String str2) {
        return Base.levenshtein(
                normalizeForEdit(str1),
                normalizeForEdit(str2)
        );
    }


    /*
     * Similarity metric based on Damerau-Levenshtein distance.
     * Implements dynamic programming approach by Lowrance-Wagner (space in O(nm), runtime in O(nm)).
     *
     * See: https://en.wikipedia.org/wiki/Damerau%E2%80%93Levenshtein_distance
     * See: ttps://github.com/tdebatty/java-string-similarity#damerau-levenshtein
     */
    static double damerauLevenshtein(String str1, String str2) {
        int n = str1.length();
        int m = str2.length();

        if (n==0 && m==0) {
            return 1.0; // empty strings have similarity 1
        }

        int[][] dist = new int[n+2][m+2]; // initialized with 0s
        int maxDist = n+m;

        // initialize array
        dist[0][0] = maxDist;
        for (int i=1; i<n+2; i++) {
            dist[i][0] = maxDist;
            dist[i][1] = i-1;
        }
        for (int j=1; j<m+2; j++) {
            dist[0][j] = maxDist;
            dist[1][j] = j-1;
        }

        HashMap<Character, Integer> d_str1 = new HashMap<>();
        for (int pos=0; pos<str1.length(); pos++) {
            d_str1.put(str1.charAt(pos), 1);
        }
        for (int pos=0; pos<str2.length(); pos++) {
            d_str1.put(str2.charAt(pos), 1);
        }
        int d_str2;

        for (int i=2; i<n+2; i++) {
            d_str2 = 1;
            for (int j=2; j<m+2; j++) {
                int i_t = d_str1.get(str2.charAt(j-2));
                int j_t = d_str2;
                int cost;

                if (str1.charAt(i-2) == str2.charAt(j-2)) {
                    cost = 0;
                    d_str2 = j;
                } else {
                    cost = 1;
                }

                dist[i][j] = Math.min(Math.min(Math.min(
                        dist[i-1][j-1] + cost, // substitution
                        dist[i-1][j] + 1), // deletion
                        dist[i][j-1] + 1), // insertion
                        dist[i_t-1][j_t-1] + (i-i_t-1) + 1 + (j-j_t-1) // transposition
                );
            }
            d_str1.put(str1.charAt(i-2), i);
        }

        return (double) (Math.max(n, m) - dist[n][m]) / Math.max(n, m);
    }

    /*
     * Damerau-Levenshtein base variants
     */

    // normalization
    static double damerauLevenshteinNormalized(String str1, String str2) {
        return Base.damerauLevenshtein(
                normalizeForEdit(str1),
                normalizeForEdit(str2)
        );
    }


    /*
     * Similarity metric based on optimal alignment.
     * Implements dynamic programming approach (space in O(min(n,m)), runtime in O(nm)).
     *
     * See: https://en.wikipedia.org/wiki/Damerau%E2%80%93Levenshtein_distance#Optimal_string_alignment_distance
     * See: https://en.wikipedia.org/wiki/Needleman%E2%80%93Wunsch_algorithm
     */
    static <T> double optimalAlignment(List<T> tokens1, List<T> tokens2) {
        int n = tokens1.size();
        int m = tokens2.size();

        if (n==0 && m==0) {
            return 1.0; // empty strings have similarity 1
        }

        // ensure space in O(min(n,m))
        if (m>n) {
            List<T> tmp = tokens1;
            tokens1 = tokens2;
            tokens2 = tmp;
            int tmpInt = n;
            n = m;
            m = tmpInt;
        }

        int[] current_row = new int[m+1];
        int[] previous_row_1 = new int[m+1];
        int[] previous_row_2 = new int[m+1];

        // initialize first row
        for (int i=0; i<m+1; i++) {
            previous_row_1[i] = i; // str1 empty => str2 is i deletions aways from str1
        }

        for (int i=1; i<n+1; i++) {
            current_row[0] = i+1; // str1 is max. i+1 deletions away from str1

            for (int j=1; j<m+1; j++) {
                if (tokens1.get(i-1).equals(tokens2.get(j-1))) {
                    current_row[j] = previous_row_1[j-1];
                } else {
                    current_row[j] = Math.min(Math.min(
                            previous_row_1[j-1] + 1, // substitution
                            previous_row_1[j] + 1), // deletion
                            current_row[j-1] + 1 // insertion
                    );
                    if (i>1 && j>1 && tokens1.get(i-1).equals(tokens2.get(j-2)) && tokens1.get(i-2).equals(tokens2.get(j-1))) {
                        current_row[j] = Math.min(
                                current_row[j],
                                previous_row_2[j-2]+1 // transposition
                        );
                    }
                }
            }

            // current row is previous row 1 in next iteration
            int[] tmp = previous_row_1;
            previous_row_1 = current_row;
            current_row = tmp;
            // previous row 1 is previous row 2 in next iteration
            tmp = current_row; // stores previous row 1 after above swap
            current_row = previous_row_2;
            previous_row_2 = tmp;
        }

        return (double) (Math.max(n, m) - previous_row_1[m]) / Math.max(n, m);
    }

    static double optimalAlignment(String str1, String str2) {
        return optimalAlignment(
                str1.chars()
                        .mapToObj(i -> String.valueOf((char)i)) // see http://stackoverflow.com/a/22436638
                        .collect(Collectors.toList()),
                str2.chars()
                        .mapToObj(i -> String.valueOf((char)i)) // see http://stackoverflow.com/a/22436638
                        .collect(Collectors.toList())
        );
    }

    /*
     * Optimal alignment base variants
     */

    // normalization
    static double optimalAlignmentNormalized(String str1, String str2) {
        return Base.optimalAlignment(
                normalizeForEdit(str1),
                normalizeForEdit(str2)
        );
    }


    /*
     * Similarity metric based on longest common subseqence.
     * Implemented using dynamic programming (space in O(min(n,m)) and runtime in O(nm)).
     *
     * See paper Bakkelund09.
     * See: https://en.wikipedia.org/wiki/Longest_common_subsequence_problem
     * See: https://github.com/tdebatty/java-string-similarity#metric-longest-common-subsequence
     */
    static <T> double longestCommonSubsequence(List<T> tokens1, List<T> tokens2) {
        int n = tokens1.size();
        int m = tokens2.size();

        if (n==0 && m==0) {
            return 1.0; // empty strings have similarity 1
        }

        // ensure space in O(min(n,m))
        if (m>n) {
            List<T> tmp = tokens1;
            tokens1 = tokens2;
            tokens2 = tmp;
            int tmpInt = n;
            n = m;
            m = tmpInt;
        }

        int[] current_row = new int[m+1]; // initialized with 0s
        int[] previous_row = new int[m+1]; // initialized with 0s

        for (int i=1; i<n+1; i++) {
            for (int j=1; j<m+1; j++) {
                if (tokens1.get(i-1).equals(tokens2.get(j-1))) {
                    current_row[j] = previous_row[j-1] + 1; // increment LCS of previous position
                } else {
                    current_row[j] = Math.max(
                            current_row[j-1],     // LCS up to previous position in str2
                            previous_row[j]   // LCS up to previous position in str1
                    );
                }
            }

            // current row is previous row in next iteration
            int[] tmp = previous_row;
            previous_row = current_row;
            current_row = tmp;
        }

        return (double) previous_row[m] / Math.max(n, m);
    }

    static double longestCommonSubsequence(String str1, String str2) {
        return longestCommonSubsequence(
                str1.chars()
                        .mapToObj(i -> String.valueOf((char)i)) // see http://stackoverflow.com/a/22436638
                        .collect(Collectors.toList()),
                str2.chars()
                        .mapToObj(i -> String.valueOf((char)i)) // see http://stackoverflow.com/a/22436638
                        .collect(Collectors.toList())
        );
    }

    /*
     * Longest common subsequence base variants
     */

    // normalization
    static double longestCommonSubsequenceNormalized(String str1, String str2) {
        return longestCommonSubsequence(
                normalizeForEdit(str1),
                normalizeForEdit(str2)
        );
    }

}
