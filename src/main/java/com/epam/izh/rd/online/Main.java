package com.epam.izh.rd.online;

public class Main {

    static String[] A = new String[] {"Книга о разном", "Nothing"};
    public static void main(String[] args) {
        String B = "Книга о разном";
        if (getString()==B) System.out.printf("Equals");
    }

    static String getString(){
        return A[0];
    }

}
