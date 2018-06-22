package com.learn.utils;

public class Book {
    private final String title;

    private Book(String title) {
        this.title = title;
    }

    public static Book fromTitle(String title) {
        return new Book(title);
    }

    /*
    //when enabling the testWithImplicitFallbackArgumentConversion wont work
    public static Book other(String title){
        return new Book("foo");
    }*/

    public String getTitle() {
        return this.title;
    }
}
