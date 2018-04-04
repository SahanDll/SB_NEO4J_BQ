package com.dev.ra.util;

import java.util.Date;

public class T {
    public static void main(String[] args) {
        long i = Long.parseLong("1522554240261001")/1000;
        System.out.println(new Date(i));
    }
}
