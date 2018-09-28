package com.learn.testing.extensions;

import com.learn.testing.extensions.beforeAllCallback.BeforeAllClase;
import com.learn.testing.extensions.beforeAllCallback.BeforeAllClaseDos;
import com.learn.testing.extensions.beforeEachCallback.beforeEachClase;
import com.learn.testing.extensions.beforeEachCallback.beforeEachClaseDos;
import com.learn.testing.extensions.beforeTestExecutionCallback.beforeTestExecutionClass;
import com.learn.testing.extensions.parameterResolver.ParameterResolverClase;
import com.learn.testing.extensions.testInstancePostProcessor.TestInstancePostProcessorClase;
import org.junit.jupiter.api.extension.ExtendWith;

/**
 * Executes in order, first beforeEachClase.class second , beforeEachClaseDos.class
 */
@ExtendWith({
        BeforeAllClaseDos.class,
        BeforeAllClase.class,
        TestInstancePostProcessorClase.class,
        beforeEachClase.class,
        beforeTestExecutionClass.class,
        ParameterResolverClase.class})
@ExtendWith(beforeEachClaseDos.class)
public class ParentClase {

    public void showLong(Long l) {
        System.out.println("showLong:" + l);
    }

}
