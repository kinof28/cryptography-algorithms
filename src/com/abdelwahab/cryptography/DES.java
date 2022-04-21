package com.abdelwahab.cryptography;

public class DES {

    // Initial Permutation Table
    private int[] firstPermutationMatrix = { 57,49,41,33,25,17,9,
                                            1,58,50,42,34,26,18,
                                            10,2,59,51,43,35,27,
                                            19,11,3,60,52,44,36,
                                            63,55,47,39,31,23,15,
                                            7,62,54,46,38,30,22,
                                            14,6,61,53,45,37,29,
                                            21,13,5,28,20,12,4  };


    private void generateKeys(String key){
        if(key.length()<64) {
            System.out.println("something is wrong ,key length is less then 64 , it is :"+key.length());
            return;
        }
        String key56="";
        for (int i = 0; i < this.firstPermutationMatrix.length; i++) {
            key56+=key.charAt(this.firstPermutationMatrix[i]-1);
        }
        String key56A=key56.substring(0,key56.length()/2);
        String key56B=key56.substring(key56.length()/2);
        System.out.println(key56);
        System.out.println("shifting : "+this.shiftLeft("111111000",5));
    }
    private String shiftLeft(String key,int amount){
        String shiftedKey=key.substring(amount);
        shiftedKey=shiftedKey+key.substring(0,amount);
        return shiftedKey;
    }
    public String encrypt(String message ,String key){
        String encryptedMessage="";
        this.generateKeys(key);
        return encryptedMessage;
    }

    public String decrypt(String message ,String key){
        String decryptedMessage="";

        return decryptedMessage;
    }

}
