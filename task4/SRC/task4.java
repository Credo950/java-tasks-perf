package com.performance.tasks;

public class task4 {

    public static void main(String[] args) {
        String firstStr = args[0], secondStr = args[1];
        boolean flagOK = true;
        String[] strs = secondStr.split("\\*", 0);
        for (String str: strs){
            int now = firstStr.indexOf(str);
            if (now < 0) {
                flagOK = false;
                break;
            }
            firstStr=firstStr.substring(now + 1);
        }
        if (flagOK)
            System.out.println("OK");
        else
            System.out.println("KO");

    }
}
