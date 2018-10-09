package com.learn.testing.extensions;


import com.learn.annotations.Adri;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static com.learn.testing.extensions.parameterResolver.ParameterResolverClase.MyParameter;

/*
* Para que llame el beforeEach de beforeEachClase
* Si se pone como PruebaTest extend beforeEachClase entonces no llama al beforeEach
*
* */
public class PruebaTest extends ParentClase{



  //  @BeforeEach
    public void mySetup(){
        System.out.println("PruebaTest.BeforeEach, mi metodo");
    }


    @Disabled
    @Test
    void miMetodo() {

        System.out.println("PruebaTest.mi metodo");
    }

    @Disabled
    @Test
    void segundoMetodo(@MyParameter String parametro, @MyParameter int num) {

        System.out.println("PruebaTest.Segundometodo con parametro " + parametro +  " " + num );
    }




}
