package org.sotorrent.stringsimilarity;

/*
 * Basic utility methods that apply for all similarity metrics.
 */
public class Similarity {
    public static final double DELTA_MAX = 0.000001;

    /*
     * Deal with rounding errors when comparing similarity values.
     * See: http://stackoverflow.com/a/960098
      */
    public static boolean equals(double sim1, double sim2) {
        return Math.abs(sim1 - sim2) < DELTA_MAX;
    }
}
