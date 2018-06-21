package com.learn.testing;



public class FizzBuzz {

    public String play(int number) {

        if(number==0) throw new IllegalArgumentException("Dont use Zero");
        if(number%3==0) return "Fizz";
        if(number%5==0) return "Buzz";



        return String.valueOf(number);
    }
}
