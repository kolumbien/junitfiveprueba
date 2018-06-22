package com.learn.testing.interfacecontracts;

public class StringTests implements EqualsContract<String> {
    @Override
    public String createNotEqualValue() {
        return "foo bar";
    }

    @Override
    public String createValue() {
        return "hola lola";
    }
}
