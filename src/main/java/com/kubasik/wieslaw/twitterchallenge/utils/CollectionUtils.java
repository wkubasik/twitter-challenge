package com.kubasik.wieslaw.twitterchallenge.utils;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CollectionUtils {
    public static <T> List<T> concatenateListsAndSort(List<T> a, List<T> b) {
        return Stream.of(a, b).flatMap(Collection::stream).sorted().collect(Collectors.toList());
    }
}
