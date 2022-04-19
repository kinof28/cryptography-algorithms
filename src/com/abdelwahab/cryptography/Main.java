package com.abdelwahab.cryptography;

public class Main {
    public static void main(String[] args) {
        Caesar caesar=new Caesar();
        System.out.println(caesar.encrypt("HelloWorld",5,true));
        System.out.println(caesar.decrypt("mjqqtbtwqi",5,false));
    }
}
