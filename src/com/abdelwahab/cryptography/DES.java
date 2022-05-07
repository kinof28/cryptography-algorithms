package com.abdelwahab.cryptography;

public class DES {

    // First Permutation Table for the 64 Key
    // Matrix to make a 64bit key to 56bit key
    private int[] firstPermutationMatrix = {
            57, 49, 41, 33, 25, 17, 9,
            1, 58, 50, 42, 34, 26, 18,
            10, 2, 59, 51, 43, 35, 27,
            19, 11, 3, 60, 52, 44, 36,
            63, 55, 47, 39, 31, 23, 15,
            7, 62, 54, 46, 38, 30, 22,
            14, 6, 61, 53, 45, 37, 29,
            21, 13, 5, 28, 20, 12, 4};
    // Second Permutation table for the 56 keys
    // Matrix to make 56bit key to 48bit key
    private int[] secondPermutationMatrix = {
            14, 17, 11, 24, 1, 5,
            3, 28, 15, 6, 21, 10,
            23, 19, 12, 4, 26, 8,
            16, 7, 27, 20, 13, 2,
            41, 52, 31, 37, 47, 55,
            30, 40, 51, 45, 33, 48,
            44, 49, 39, 56, 34, 53,
            46, 42, 50, 36, 29, 32};
    // list of 16 key of 56 bit length
    private String[] key56List = new String[16];
    // list of 16 key of 48 bit length
    // contains the result of permutation of the previous keys of length =56
    private String[] key48List = new String[16];
    // the final list of keys that contains 256 key of 48 bit length
    // result of shifting of the previous list
    private String[] key48ListFinal = new String[256];

    // helper function which we call to generate our 256 key ,
    // takes the initial key and initialise our list of keys
    // note that the key in argument must have length of 64 or higher
    private void generateKeys(String key) {
        // first we check the key's length if it's less than 64 we print and error message and return
        if (key.length() < 64) {
            System.out.println("something is wrong ,key length is less then 64 , it is :" + key.length());
            return;
        }
        // when the key's length is ok we create the 16 keys where key.length =56
        this.generate56Keys(key);
        // we use the previous 56 bit key list to generate our final 48 bit keys
        this.generate48Keys();
    }

    // the function that creates 16 key that have 56 bit in length
    private void generate56Keys(String key) {
        // we create a new key that have 56 bit in length by passing the initial key in the first permutation matrix
        // we initialise it by an empty string
        String key56 = "";
        for (int i = 0; i < this.firstPermutationMatrix.length; i++) {
            // add characters in the first permutation matrix order
            // -1 cause indexes in the matrix goes from 1 to 64
            key56 += key.charAt(this.firstPermutationMatrix[i] - 1);
        }
        // after generation of the first key we split it into two part
        // left and right part we called them here key56A and key56B
        // new key is the resulting key after shifting
        String key56A, key56B, newKey;
        // amount of shifting is generally 2, we did that to omit shifting table
        int amount = 2;
        for (int i = 0; i < 16; i++) {
            // key56A takes the left part by splitting the key in half
            key56A = key56.substring(0, key56.length() / 2);
            // key56B takes the right part
            key56B = key56.substring(key56.length() / 2);
            // amount in 1 2 9 16 rounds is different and =1
            if (i == 0 || i == 1 || i == 8 || i == 15) amount = 1;
            // the newKey takes first the left part after shifting using a helper function that shift the key with an amount
            newKey = this.shiftLeft(key56A, amount);
            // after the left part the right part is added after being shifted also
            newKey += this.shiftLeft(key56B, amount);
            // we save the new key in the list
            this.key56List[i] = newKey;
            // newKey is our new working key, so we can keep doing our shifting
            key56 = newKey;
        }
    }

    // function that creates the final list of 256 key of 48 bits
    private void generate48Keys() {
        // we go throw the 56bit keys and reduce them to be 48 bits keys using a helper function
        // that takes a 56bits key as entry and returns 48 bits key
        for (int i = 0; i < this.key56List.length; i++) {
            // we fill them in the initial list of 16 key
            this.key48List[i] = this.generate48Key(this.key56List[i]);
        }
        // creation of variables where key48A takes the left part and key48B takes the right part
        String key48A, key48B, newKey;
        int amount = 2;
        for (int i = 0; i < this.key48List.length; i++) {
            // we go throw the list of 16 48 bits keys and for each we perform a 16 shift and save each new key
            for (int j = 0; j < 16; j++) {
                // for each key we take the left and right parts by devising it into 2 parts
                key48A = this.key48List[i].substring(0, this.key48List[i].length() / 2);
                key48B = this.key48List[i].substring(this.key48List[i].length() / 2);
                // same as previous amount equals 1 in those rounds
                if (j == 0 || j == 1 || j == 8 || j == 15) amount = 1;
                // the use of the same helper function of shifting
                newKey = this.shiftLeft(key48A, amount);
                newKey += this.shiftLeft(key48B, amount);
                // we save the new key in the last list of keys that goes from 0 to 256
                // i goes from 0 to 15 and j from 0 to 15 so to go from 0 to 255 we do (i*16)+j
                this.key48ListFinal[(i * 16) + j] = newKey;
                // we replace our initial 48bit key with the new key, so we can continue our shifting
                this.key48List[i] = newKey;
            }
        }
    }

    // function takes 56bit key and returns 48bit key
    private String generate48Key(String key) {
        // we create a new key of 48 bit in length by passing the key in the second permutation matrix
        // we initialise it by an empty string
        String generatedKey = "";
        for (int i = 0; i < this.secondPermutationMatrix.length; i++) {
            // add characters in the second permutation matrix order
            // -1 cause indexes in the matrix goes from 1 to 56
            generatedKey += key.charAt(this.secondPermutationMatrix[i] - 1);
        }
        // return the generated key
        return generatedKey;
    }

    // helper function that shift a key with an amount
    // takes as obvious the key to be shifted and the amount of shifting
    private String shiftLeft(String key, int amount) {
        // start by omitting first characters . ex: 100000 , amount =1 we omit the 1
        // and save it in the new key called shiftedKey
        String shiftedKey = key.substring(amount);
        // append the omitted leading characters to the end
        shiftedKey = shiftedKey + key.substring(0, amount);
        // return the new shiftedKey
        return shiftedKey;
    }

    // the public encryption function takes the message and the key of 64 bits as entry and keyNumber(ex keyNumber = 100)
    public String encrypt(String message, String key, int keyNumber) {
        // initialise the encryption by empty String
        String encryptedMessage = "";
        // generate Keys
        this.generateKeys(key);
        // convert the message to binary
        String messageInBinary = this.getMessageInBinary(message);
        // go throw the binary message and perform Xor with the selected key
        for (int i = 0; i < messageInBinary.length(); i++) {
            encryptedMessage = messageInBinary.charAt(i) == this.key48ListFinal[keyNumber].charAt(i % 48) ? encryptedMessage + 0 : encryptedMessage + 1;
        }
        // return the encrypted Key
        return encryptedMessage;
    }

    // Helper function that convert message or any String to binary where each character take 8 bits
    private String getMessageInBinary(String message) {
        // we initialise the result as an empty String
        String messageInBinary = "";
        for (int i = 0; i < message.length(); i++) {
            // we go throw the message in the argument, and we convert each character to 8 bits binary message
            // using another helper function which convert a single character to 8 bits binary code
            messageInBinary += charToBinary(message.charAt(i));
        }
        // we return our result
        return messageInBinary;
    }

    // Helper function that take a character and return 8 bits of binary code
    private String charToBinary(char c) {
        // we get the binary code of our character c in the arguments. ex: a=10010
        String binary = Integer.toBinaryString(c);
        // a dummy String with zeros to use it later in normalising our binary code
        String leadingZeros = "00000000";
        // adding leading 0 to make it 8 bits and make it our result
        String result = leadingZeros.substring(0, 8 - binary.length()) + binary;
        // we return the result
        return result;
    }

    // public decryption function similar to encryption function but takes message in binary
    public String decrypt(String message, String key, int keyNumber) {
        String decryptedMessage = "";
        // check if the list of keys is empty we initialise them else we just use the already initialised lise
        if (this.key48ListFinal[0] == null) {
            this.generateKeys(key);
        }
        // go throw message which is in binary already and perform a Xor with the selected key
        for (int i = 0; i < message.length(); i++) {
            decryptedMessage = message.charAt(i) == this.key48ListFinal[keyNumber].charAt(i % 48) ? decryptedMessage + 0 : decryptedMessage + 1;
        }
        // return decryption in binary
        return decryptedMessage;
    }

}