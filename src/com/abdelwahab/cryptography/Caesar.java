package com.abdelwahab.cryptography;

public class Caesar {
    private char[] alphabet;
    public Caesar(){
        this.alphabet=new char[26];
        int j=0;
        for (char i = 'a'; i <= 'z'; i++) {
            this.alphabet[j]=i;
            j++;
        }
    }
    public String encrypt(String message,int key,boolean numericOrAlphabetic){
        //boolean value is true for numeric and false for alphabetic
        if(numericOrAlphabetic)return this.encryptNumeric(message,key);
        else return this.encryptAlphabetic(message,key);
    }
    public String decrypt(String message,int key,boolean numericOrAlphabetic){
        //boolean value is true for numeric and false for alphabetic
        if(numericOrAlphabetic)return this.decryptNumeric(message,key);
        else return this.decryptAlphabetic(message,key);
    }

    private String encryptAlphabetic(String message,int key){
        String encryptedMessage="";
        message=message.toLowerCase();
        for (int i = 0; i < message.length(); i++) {
            encryptedMessage=encryptedMessage+this.alphabet[(this.getIndex(message.charAt(i))+key)%26];
        }
        return encryptedMessage;
    }
    private String decryptAlphabetic(String message,int key){
        return this.encryptAlphabetic(message,26-key);
    }
    private int getIndex(char c){
        for (int i = 0; i < this.alphabet.length; i++) {
            if(this.alphabet[i]==c)return i;
        }
        return -1;
    }
    private String encryptNumeric(String message,int key){
        message=message.toLowerCase();
        String encryptedMessage="";
        int helper;
        for(int i=0;i<message.length();i++){
            helper=message.charAt(i);
            helper=(helper-97+key)%26;
            helper= helper<0?123+helper:helper+97;
            encryptedMessage+=Character.toString(helper);
        }
        return encryptedMessage;
    }

    private String decryptNumeric(String message,int key){
        return this.encryptNumeric(message,-key);
    }



}
