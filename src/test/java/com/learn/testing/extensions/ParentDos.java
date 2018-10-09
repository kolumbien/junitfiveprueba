package com.learn.testing.extensions;

import com.learn.testing.extensions.beforeAllCallback.MyTestConfiguration;
import com.learn.testing.extensions.beforeAllCallback.Property;
import com.learn.testing.extensions.beforeAllCallback.TestConfiguration;
import com.learn.testing.extensions.beforeEachCallback.beforeEachClase;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith({ MyTestConfiguration.class, TestConfiguration.class})
public class ParentDos {

    @Disabled
    @Test
    public void hola(@Property("merchant_email") String email){

        System.out.println("ParentDos email:" +
                "" + email);
    }

}
