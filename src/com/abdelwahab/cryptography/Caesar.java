package com.abdelwahab.cryptography;

public class Caesar {
    //array of alphabets to use for alphabetic encryption
    private char[] alphabet;
    // initialisation in constructor
    // initialisation of alphabets array
    public Caesar(){
        this.alphabet=new char[26];
        int j=0;
        for (char i = 'a'; i <= 'z'; i++) {
            this.alphabet[j]=i;
            j++;
        }
    }
    //encryption public methode
    public String encrypt(String message,int key,boolean numericOrAlphabetic){
        //boolean value is true for numeric and false for alphabetic
        if(numericOrAlphabetic)return this.encryptNumeric(message,key);
        else return this.encryptAlphabetic(message,key);
    }
    //decryption public method
    public String decrypt(String message,int key,boolean numericOrAlphabetic){
        //boolean value is true for numeric and false for alphabetic
        if(numericOrAlphabetic)return this.decryptNumeric(message,key);
        else return this.decryptAlphabetic(message,key);
    }
    //encryption implementation in alphabetic methode
    private String encryptAlphabetic(String message,int key){
        String encryptedMessage="";
        //toLowerCase to make sure that message is from a to z
        message=message.toLowerCase();
        for (int i = 0; i < message.length(); i++) {
            //encryption real implementation using alphabets array
            //we use a helper function to get index of current letter ex:a=0 f=5
            encryptedMessage=encryptedMessage+this.alphabet[(this.getIndex(message.charAt(i))+key)%26];
        }
        return encryptedMessage;
    }
    //decryption implementation in alphabetic methode
    //we just call encryption methode with 0-key
    private String decryptAlphabetic(String message,int key){
        return this.encryptAlphabetic(message,26-key);
    }
    //helper function to get letter index in the alphabet arrray
    private int getIndex(char c){
        for (int i = 0; i < this.alphabet.length; i++) {
            if(this.alphabet[i]==c)return i;
        }
        return -1;
    }
    //encryption implementation in numeric methode
    private String encryptNumeric(String message,int key){
        message=message.toLowerCase();
        String encryptedMessage="";
        // we creat a helper index to perform some operation on ASCII code
        int helper;
        for(int i=0;i<message.length();i++){
            helper=message.charAt(i);//helper take ascii code of the encoded letter
            helper=(helper-97+key)%26;//we get the new letter index
            helper= helper<0?123+helper:helper+97;//we add some value to get ascii code ,
            // generally we add 97 but for decryption and negative key we made that check
            encryptedMessage+=Character.toString(helper);// convert out helper to a character
        }
        return encryptedMessage;
    }
    // decryption implementation in alphabetic methode
    // we call encryption function with 0-key
    private String decryptNumeric(String message,int key){
        return this.encryptNumeric(message,-key);
    }
}
