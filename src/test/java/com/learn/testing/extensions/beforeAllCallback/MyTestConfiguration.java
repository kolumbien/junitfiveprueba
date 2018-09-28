package com.learn.testing.extensions.beforeAllCallback;

import org.junit.jupiter.api.extension.*;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.NoSuchFileException;
import java.util.Properties;
import java.util.stream.Stream;


public class MyTestConfiguration implements BeforeAllCallback {


    public void beforeAll(ExtensionContext extensionContext) {


        System.setProperty("shop","TEINDA");

    }


}
