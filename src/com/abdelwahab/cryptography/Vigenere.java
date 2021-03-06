package com.abdelwahab.cryptography;

public class Vigenere {
    // vigenere table is assisting table for the alphabetic encryption and decryption functions
    private char [][] vigenereTable;
    // Helper string with all alphabets (a-z) for vigenere table filling
    private String alphabetic="abcdefghijklmnopqrstuvwxyz";
    public Vigenere(){
        // the initialisation is done in the constructor here we fill the table to use it later
        this.vigenereTable=new char[26][26];
        //vigenere table filling
        for (int i = 0; i < 26; i++) {
            for (int j = 0; j < 26; j++) {
                this.vigenereTable[i][j]=this.alphabetic.charAt((i+j)%26);
            }
        }
    }
    // the public function for encryption
    // take the message and the key and a boolean value to decide which implementation will be used numeric or alphabetic
    public String encrypt(String message,String key,boolean numericOrAlphabetic){
        //boolean value is true for numeric and false for alphabetic
        if(numericOrAlphabetic)return this.encryptNumeric(message,key);
        else return this.encryptAlphabetic(message,key);
    }
    // the public function for decryption
    // take the encryption and the key and a boolean value to decide which implementation will be used numeric or alphabetic
    public String decrypt(String message,String key,boolean numericOrAlphabetic){
        //boolean value is true for numeric and false for alphabetic
        if(numericOrAlphabetic)return this.decryptNumeric(message,key);
        else return this.decryptAlphabetic(message,key);
    }
    // encryption implementation in alphabetic method
    private String encryptAlphabetic(String message,String key){
        // declaration of encryption as an empty string
        String encryptedMessage="";
        for (int i = 0; i < message.length(); i++) {
            // real encryption part
            // adding the new encryption letter for each iteration throw the length of the entry message
            // the new encryption letter is the result from vigenere table were the line is the index of the letter from the message
            // and the column are the index of the letter from the key while the key is repeated by i%key.length()
            // we use a helper function getIndex to get the index of a character (in alphabetic order from 0 to 25)
            encryptedMessage=encryptedMessage+this.vigenereTable[this.getIndex(message.charAt(i))][this.getIndex(key.charAt(i%key.length()))];
        }
        // return of result
        return encryptedMessage;
    }
    // decryption implementation in alphabetic method
    private String decryptAlphabetic(String message,String key){
        // declaration of decryption as an empty string
        String decryptedMessage="";
        for (int i = 0; i < message.length(); i++) {
            // real decryption part
            // for each iteration throw the hole encryption we decrypt the letter and add it to the decrypted message
            // the decrypted letter is gain from vigenere table from the first line and for the column we use a helper function  
            // to get the decryption index which take the character from the encryption and the index of corresponding character from key
            // we also use the getIndex helper function
            decryptedMessage=decryptedMessage+this.vigenereTable[0][this.getDecryptionIndex(message.charAt(i),this.getIndex(key.charAt(i%key.length())))];
        }
        
        return decryptedMessage;
    }
    // function which take a character and return its index in alphabets from 0 for 'a' to 25 for 'z'
    private int getIndex(char c){
        for (int i = 0; i < 26; i++) {
            // when the character it's found the index is returned
            if(this.alphabetic.charAt(i)==c)return i;
        }
        // -1 is returned in case of non-alphabetic character
        return -1;
    }
    // helper function for decryption in the alphabetic method to get the column index 
    // it take the encryption letter and the key character index
    // it go vigenere table and go throw the index line and return the index of column where the character is found
    private int getDecryptionIndex(char c,int index){
        for (int i = 0; i < 26; i++) {
            if(this.vigenereTable[index][i]==c)return i;
        }
        // return -1 for non-alphabetic character
        return -1;
    }
    // encryption implementation in numeric method
    private String encryptNumeric(String message,String key){
        String encryptedMessage="";
        // converting message to lower case to make sure that ASCII code is between 97(a) and 123(z)
        message=message.toLowerCase();
        // declaring new integer helpers
        int helper1,helper2;
        for (int i = 0; i < message.length(); i++) {
            // for each iteration we assign ascii code of the letter from the message and we omit 97
            // to ensure that helper1 has value from 0 to 25 for characters from a to z
            helper1=message.charAt(i)-97;
            // helper2 has value from 0 to 25 for character from a to z but of the character from the key
            helper2=key.charAt(i%key.length())-97;
            // we add helper1 and helper2 to get the encrypted character and to make sure it stays from 0 to 25 we make mod operation
            helper1=(helper1+helper2)%26;
            // we convert back 0 to 25 to real ascii code by adding 97 that we take it earlier and convert it to character
            // and add it to the encrypted message
            encryptedMessage+=Character.toString(helper1+97);
        }
        // return encrypted message
        return encryptedMessage;
    }
    // decryption implementation in numeric method
    // the most part is the same as encryption function with some differences we comment them out
    private String decryptNumeric(String message,String key){
        String decryptedMessage="";
        message=message.toLowerCase();
        int helper1,helper2;
        for (int i = 0; i < message.length(); i++) {
            helper1=message.charAt(i)-97;
            helper2=key.charAt(i%key.length())-97;
            helper1=(helper1-helper2);
            // the only difference is that helper1 may be negative, so we take it out from 'z' not from 'a'
            // we make this ternary condition so if it's positive we add 97 ('a' code) and if it's negative we add 123 ('z' code)
            helper1=helper1<0?123+helper1:helper1+97;
            decryptedMessage+=Character.toString(helper1);
        }
        return decryptedMessage;
    }
}
