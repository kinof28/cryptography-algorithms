package com.abdelwahab.cryptography;

public class Main {
    public static void main(String[] args) {
        //test for Caesar Algorithm
//        Caesar caesar=new Caesar();
//        System.out.println(caesar.encrypt("HelloWorld",5,true));
//        System.out.println(caesar.decrypt("mjqqtbtwqi",5,false));
        //test for Vigenere Algorithm
//        Vigenere vigenere=new Vigenere();
//        System.out.println(vigenere.encrypt("decodex","isi",true));
//        System.out.println(vigenere.decrypt("lwkwvmf","isi",true));
//        System.out.println(vigenere.encrypt("decodex","isi",false));
//        System.out.println(vigenere.decrypt("lwkwvmf","isi",false));
        //test for LFSR Algorithm
        LFSR lfsr=new LFSR();
        System.out.println("encryption : "+lfsr.encrypt("decodex","01010101"));
        System.out.println("message in binary : "+lfsr.decrypt("00110001101011010011001101110101000101001001100001101110","01010101"));
    }
}
