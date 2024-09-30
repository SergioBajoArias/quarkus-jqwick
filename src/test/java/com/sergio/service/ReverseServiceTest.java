package com.sergio.service;

import lombok.extern.slf4j.Slf4j;
import net.jqwik.api.*;

import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Slf4j
class ReverseServiceTest {

    ReverseService reverseService = new ReverseService();

    @Property
    public void testSize(@ForAll List<Integer> collection) {
        assertEquals(collection.size(), reverseService.reverse(collection).size());
    }

    @Property
    public void testDoubleReverse(@ForAll List<Integer> collection) {
        assertEquals(collection, reverseService.reverse(reverseService.reverse(collection)));
    }

    @Property
    public void testEncoding(@ForAll("latinAlphabet") String word) {
        byte[] encoded = Base64.getEncoder().encode(word.getBytes());
        byte[] decoded = Base64.getDecoder().decode(encoded);
        assertEquals(word, new String(decoded));
    }

    @Provide
    Arbitrary<String> latinAlphabet() {
        return Arbitraries.strings().alpha().numeric();
    }

    /**
     * This test is just for tutorial purposes
     */
    @Property
    public void testReverse(@ForAll List<Integer> collection) {
        List<Integer> reversedCollection = new ArrayList<>(collection);
        Collections.reverse(reversedCollection);
        assertEquals(reversedCollection, reverseService.reverse(collection));
    }

    @Property
    public void testIdempotence(@ForAll List<Integer> collection) {
        assertEquals(
                collection.stream()
                        .filter(this::greatherThan10)
                        .collect(Collectors.toList()),
                collection.stream()
                        .filter(this::greatherThan10)
                        .filter(this::greatherThan10)
                        .filter(this::greatherThan10)
                        .filter(this::greatherThan10)
                        .filter(this::greatherThan10)
                        .collect(Collectors.toList()));
    }

    public boolean greatherThan10(Integer number) {
        return number > 10;
    }

}