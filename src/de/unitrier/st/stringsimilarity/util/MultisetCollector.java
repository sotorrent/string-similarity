package de.unitrier.st.stringsimilarity.util;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;

import java.util.EnumSet;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

public class MultisetCollector<T, R extends Multiset<T>> implements Collector<T, Multiset<T>, Multiset<T>> {
    public static <T> Collector<T, Multiset<T>, Multiset<T>> toMultiset() {
        return new MultisetCollector<>();
    }

    @Override
    public Supplier<Multiset<T>> supplier() {
        return HashMultiset::create;
    }

    @Override
    public BiConsumer<Multiset<T>, T> accumulator() {
        return Multiset::add;
    }

    @Override
    public BinaryOperator<Multiset<T>> combiner() {
        return (set1, set2) -> {
            set1.addAll(set2);
            return set1;
        };
    }

    @Override
    public Function<Multiset<T>, Multiset<T>> finisher() {
        return Function.identity();
    }

    @Override
    public Set<Characteristics> characteristics() {
        return EnumSet.of(Characteristics.UNORDERED, Characteristics.IDENTITY_FINISH);
    }
}