package com.learn.utils;

import com.learn.testing.Person;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.aggregator.ArgumentsAggregator;

/**
 * Needs compile scope
 *
 * <dependency>
 *             <groupId>org.junit.jupiter</groupId>
 *             <artifactId>junit-jupiter-params</artifactId>
 *             <version>${junit.jupiter.version}</version>
 *             <scope>compile</scope>
 *         </dependency>
 */
public class PersonAggregator implements ArgumentsAggregator {
    @Override
    public Person aggregateArguments(ArgumentsAccessor arguments, ParameterContext context) {
        return new Person(arguments.getString(0),
                arguments.getString(1));
    }
}
