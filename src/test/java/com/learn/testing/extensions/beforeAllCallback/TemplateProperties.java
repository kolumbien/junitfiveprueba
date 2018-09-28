package com.learn.testing.extensions.beforeAllCallback;

import java.util.ArrayList;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TemplateProperties extends Properties {

    public static final Pattern TOKEN_MATCHER = Pattern.compile("[$][{]([^}]+)[}]");

    public TemplateProperties() {
    }

    public TemplateProperties(Properties defaults) {
        super(defaults);
    }

    @Override
    public String getProperty(String key) {
        return evaluate(key, new ArrayList<>(), key);
    }

    protected String evaluate(String key, ArrayList<String> lookup, String property) {

        if (lookup.contains(key)) {
            throw new RuntimeException(String.format("Circular key lookup. Couldn't retrieve value for key '%s' to evaluate '%s'.", key, property));
        }

        String value = super.getProperty(key);

        if (value == null) {
            return null;
        }

        Matcher matcher = TOKEN_MATCHER.matcher(value);
        StringBuffer sb = new StringBuffer();

        lookup.add(key);

        while (matcher.find()) {
            String token = matcher.group(1);

            String evaluate = evaluate(token, lookup, property);

            if (evaluate == null) {
                evaluate = "";
            }

            matcher.appendReplacement(sb, evaluate);
        }

        matcher.appendTail(sb);

        lookup.remove(key);

        return sb.toString();
    }
}