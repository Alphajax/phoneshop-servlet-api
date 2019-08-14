package com.es.phoneshop.utils;

import java.util.Date;

public class DosCounter {
    private static int num;
    private static Date lastDate = new Date();


    public static int getNum() {
        return num;
    }

    public static Date getLastDate() {
        return lastDate;
    }

    public static void replaceDate(){
        if((lastDate.getTime() - new Date().getTime()) >= (1 * 60 * 1000)) {
            lastDate = new Date();
        }
    }

    public static void count(){
        num++;
    }
}
