package com.sergio.service;

import jakarta.enterprise.context.ApplicationScoped;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@ApplicationScoped
public class ReverseService {
    public List<Integer> reverse(final List<Integer> collection) {
        List<Integer> result = new ArrayList<>(collection);
        Collections.reverse(result);
        return result;
    }
}
