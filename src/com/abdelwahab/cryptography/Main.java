package com.abdelwahab.cryptography;

public class Main {
    public static void main(String[] args) {
        //test for Caesar Algorithm
//        Caesar caesar=new Caesar();
//        System.out.println(caesar.encrypt("HelloWorld",5,true));
//        System.out.println(caesar.decrypt("mjqqtbtwqi",5,false));
        Vigenere vigenere=new Vigenere();
        System.out.println(vigenere.encrypt("decodex","isi",true));
        System.out.println(vigenere.decrypt("lwkwvmf","isi",true));
        System.out.println(vigenere.encrypt("decodex","isi",false));
        System.out.println(vigenere.decrypt("lwkwvmf","isi",false));
//        System.out.println(vigenere.encryptNumeric("decodex","isi"));
//        System.out.println(vigenere.decrypt("lwkwvmf","isi"));
    }
}
