package com.abdelwahab.cryptography;

public class Vigenere {

    private char [][] vigenereTable;
    private String alphabetic="abcdefghijklmnopqrstuvwxyz";
    public Vigenere(){
        this.vigenereTable=new char[26][26];
        //vigenere table filling
        for (int i = 0; i < 26; i++) {
            for (int j = 0; j < 26; j++) {
                this.vigenereTable[i][j]=this.alphabetic.charAt((i+j)%26);
            }
        }
    }
    public String encrypt(String message,String key,boolean numericOrAlphabetic){
        //boolean value is true for numeric and false for alphabetic
        if(numericOrAlphabetic)return this.encryptNumeric(message,key);
        else return this.encryptAlphabetic(message,key);
    }
    public String decrypt(String message,String key,boolean numericOrAlphabetic){
        //boolean value is true for numeric and false for alphabetic
        if(numericOrAlphabetic)return this.decryptNumeric(message,key);
        else return this.decryptAlphabetic(message,key);
    }
    private String encryptAlphabetic(String message,String key){
        String encryptedMessage="";
        for (int i = 0; i < message.length(); i++) {
            encryptedMessage=encryptedMessage+this.vigenereTable[this.getIndex(message.charAt(i))][this.getIndex(key.charAt(i%key.length()))];
        }
        return encryptedMessage;
    }
    private String decryptAlphabetic(String message,String key){
        String decryptedMessage="";
        for (int i = 0; i < message.length(); i++) {
            decryptedMessage=decryptedMessage+this.vigenereTable[0][this.getDecryptionIndex(message.charAt(i),this.getIndex(key.charAt(i%key.length())))];
        }
        return decryptedMessage;
    }
    private int getIndex(char c){
        for (int i = 0; i < 26; i++) {
            if(this.alphabetic.charAt(i)==c)return i;
        }
        return -1;
    }
    private int getDecryptionIndex(char c,int index){
        for (int i = 0; i < 26; i++) {
            if(this.vigenereTable[index][i]==c)return i;
        }
        return -1;
    }

    private String encryptNumeric(String message,String key){
        String encryptedMessage="";
        message=message.toLowerCase();
        int helper1,helper2;
        for (int i = 0; i < message.length(); i++) {
            helper1=message.charAt(i)-97;
            helper2=key.charAt(i%key.length())-97;
            helper1=(helper1+helper2)%26;
            encryptedMessage+=Character.toString(helper1+97);
        }
        return encryptedMessage;
    }

    private String decryptNumeric(String message,String key){
        String decryptedMessage="";
        message=message.toLowerCase();
        int helper1,helper2;
        for (int i = 0; i < message.length(); i++) {
            helper1=message.charAt(i)-97;
            helper2=key.charAt(i%key.length())-97;
            helper1=(helper1-helper2);
            helper1=helper1<0?123+helper1:helper1+97;
            decryptedMessage+=Character.toString(helper1);
        }
        return decryptedMessage;
    }




}
