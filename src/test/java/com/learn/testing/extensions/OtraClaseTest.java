package com.learn.testing.extensions;

import com.learn.annotations.Adri;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class OtraClaseTest extends ParentClase{

    @BeforeAll
    public static void mySetupAll(){
        System.out.println("OtraClaseTest.BeforeAll, mi metodo");
    }


    @BeforeEach
    public void mySetup(){
        System.out.println("OtraClaseTest.BeforeEach, mi metodo");
    }

    @Adri
    @Test
    void miMetodo() {

        System.out.println("OtraClaseTest.mi metodo");
    }
}
