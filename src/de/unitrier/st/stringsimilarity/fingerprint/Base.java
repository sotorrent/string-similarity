package de.unitrier.st.stringsimilarity.fingerprint;

import com.google.common.collect.Multiset;
import de.unitrier.st.stringsimilarity.util.MultisetCollector;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/*
 * Fingerprint-based similarity metrics.
 * All metrics should return a value between 0.0 and 1.0.
 */
public class Base {

    public static List<Integer> fingerprintList(List<String> tokens) {
        return tokens
                .stream()
                .map(String::hashCode)
                .collect(Collectors.toList());
    }

    public static List<Integer> fingerprintList(List<String> tokens, int windowSize) {
        return IntStream
                .iterate(0, i -> i+windowSize)
                .limit((int)Math.ceil((double)tokens.size()/windowSize))
                .mapToObj(currentStartPos -> tokens.subList(currentStartPos, Math.min(tokens.size(), currentStartPos+windowSize-1)))
                .map(windowList -> windowList
                        .stream()
                        .map(String::hashCode)
                        .min(Integer::compare))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
    }

    public static Multiset<Integer> fingerprintMultiset(Multiset<String> tokens) {
        return tokens
                .stream()
                .map(String::hashCode)
                .collect(MultisetCollector.toMultiset());
    }

    public static Multiset<Integer> fingerprintMultiset(Multiset<String> tokens, int windowSize) {
        return fingerprintList(new ArrayList<>(tokens), windowSize)
                .stream()
                .collect(MultisetCollector.toMultiset());
    }

    public static Set<Integer> fingerprintSet(Set<String> tokens) {
        return tokens
                .stream()
                .map(String::hashCode)
                .collect(Collectors.toSet());
    }

    public static Set<Integer> fingerprintSet(Set<String> tokens, int windowSize) {
        return new HashSet<>(fingerprintList(new ArrayList<>(tokens), windowSize));
    }
}
