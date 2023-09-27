package com.ssn;

public class Pair<F, S> {
    public final F first;
    public final S second;

    public Pair(F first, S second) {
        this.first = first;
        this.second = second;
    }
    
    public static <F, S> Pair <F, S> create(F a, S b) {
        return new Pair<F, S>(a, b);
    }
}
