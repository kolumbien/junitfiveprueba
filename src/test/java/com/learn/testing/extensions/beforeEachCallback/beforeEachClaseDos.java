package com.learn.testing.extensions.beforeEachCallback;

import com.learn.testing.extensions.ParentClase;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

import java.lang.reflect.Method;

public class beforeEachClaseDos implements BeforeEachCallback {


    @Override
    public void beforeEach(ExtensionContext extensionContext) throws Exception {
        System.out.println("beforeEachClaseDos:  " + extensionContext.getTestClass().toString());


        try {
            // method Long
            Class[] cArg = new Class[1];
            cArg[0] = Long.class;
            Method lMethod = extensionContext.getTestClass().get().getMethod("showLong", cArg);
            System.out.println("method = " + lMethod.toString());
            lMethod.invoke(new ParentClase(), 3l);


        } catch(NoSuchMethodException e) {
            System.out.println(e.toString());
        }
    }
}
