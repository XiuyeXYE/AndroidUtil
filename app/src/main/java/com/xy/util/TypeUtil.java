package com.xy.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class TypeUtil {

    public static <K, V> Map<K, V> map() {
        return new HashMap<>();
    }

    public static <V> Set<V> set() {
        return new HashSet<>();
    }

    public static <V> List<V> list() {
        return new ArrayList<>();
    }

    public static <T> List<T> toList(T[] a) {
        List<T> L = list();
        for (T e : a) {
            L.add(e);
        }
        return L;
    }

    public static List<Integer> toList(int[] a) {
        List<Integer> L = list();
        for (int e : a) {
            L.add(e);
        }
        return L;
    }


}
