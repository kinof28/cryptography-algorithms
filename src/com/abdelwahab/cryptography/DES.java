package com.abdelwahab.cryptography;

public class DES {

    // Initial Permutation Table
    private int[] firstPermutationMatrix = {
            57, 49, 41, 33, 25, 17, 9,
            1, 58, 50, 42, 34, 26, 18,
            10, 2, 59, 51, 43, 35, 27,
            19, 11, 3, 60, 52, 44, 36,
            63, 55, 47, 39, 31, 23, 15,
            7, 62, 54, 46, 38, 30, 22,
            14, 6, 61, 53, 45, 37, 29,
            21, 13, 5, 28, 20, 12, 4};
    private int[] secondPermutationMatrix={
            14,17,11,24,1,5,
            3,28,15,6,21,10,
            23,19,12,4,26,8,
            16,7,27,20,13,2,
            41,52,31,37,47,55,
            30,40,51,45,33,48,
            44,49,39,56,34,53,
            46,42,50,36,29,32 };
    private String[] key56List=new String[16];

    private void generateKeys(String key) {
        if (key.length() < 64) {
            System.out.println("something is wrong ,key length is less then 64 , it is :" + key.length());
            return;
        }
        String key56 = "";
        for (int i = 0; i < this.firstPermutationMatrix.length; i++) {
            key56 += key.charAt(this.firstPermutationMatrix[i] - 1);
        }
        String key56A, key56B,newKey;
        int amount;
        for (int i = 0; i < 16; i++) {
            key56A = key56.substring(0, key56.length() / 2);
            key56B = key56.substring(key56.length() / 2);
            amount=2;
            if(i==0||i==1||i==8||i==15)amount=1;
            newKey=this.shiftLeft(key56A,amount);
            newKey+=this.shiftLeft(key56B,amount);
            this.key56List[i]=newKey;
            key56=newKey;
        }
        System.out.println(this.key56List.length);
        for (int i = 0; i < 16; i++) {
            System.out.println(this.key56List[i]);
        }
    }
    private void generate48Keys(){


    }
    private String shiftLeft(String key, int amount) {
        String shiftedKey = key.substring(amount);
        shiftedKey = shiftedKey + key.substring(0, amount);
        return shiftedKey;
    }

    public String encrypt(String message, String key) {
        String encryptedMessage = "";
        this.generateKeys(key);
        return encryptedMessage;
    }

    public String decrypt(String message, String key) {
        String decryptedMessage = "";

        return decryptedMessage;
    }

}
