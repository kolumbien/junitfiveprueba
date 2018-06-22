package com.learn.utils;

import java.util.stream.Stream;

public class StringsProviders {
    static Stream<String> names() {
        return Stream.of("rosa", "jose", "lola");
    }
}

