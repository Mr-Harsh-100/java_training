package com.example.logger;

public class Logger {
    public static void log(Object o){
        System.out.println(o);
    }

    public static class inner {
        public static boolean innerMethod(){
                return true;
        }
    }
}
