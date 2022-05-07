package com.abdelwahab.cryptography;

public class LFSR {
    // public function of encryption take message like "hello" and a key in binary like "011010110"
    // and returns our encryption
    public String encrypt(String message, String key) {
        // we initialise encryption as an empty string
        String encryptedMessage = "";
        // we convert the message to binary using our helper function
        String messageInBinary = this.getMessageInBinary(message);
        // here we displayed the message in binary for debugging purposes
        System.out.println("message in binary : " + messageInBinary);
        // we create our final key which hase a length equals the length of our message in binary
        // we do so using another helper function which take the original key and the length that we want
        String finalKey = this.getKey(key, messageInBinary.length());
        // we display our final key for debugging purposes
        System.out.println("key : " + finalKey);
        // encryption core where we do message xor key
        for (int i = 0; i < messageInBinary.length(); i++) {
            // we go throw each character in our message in binary and we Xor with our final key
            encryptedMessage = messageInBinary.charAt(i) == finalKey.charAt(i) ? encryptedMessage + 0 : encryptedMessage + 1;
        }
        // we return the encrypted message
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

    // Helper message that generate our final key that we use in encryption and decryption
    // take as parameters the initial key and the length
    private String getKey(String key, int length) {
        // we initialise our final key as our initial key since we will be adding new digits
        String finalKey = key;
        // we iterate from zero to length - initial key length because we need only to add extra digits to make it = length
        for (int i = 0; i < length - key.length(); i++) {
            // for each iteration we add a new character or digit
            // the new digit is calculated using a helper function that take a key and returns a new character
            // we give it as argument our final key wile omitting i character to ensure our movement in key generation
            finalKey += this.getSingleKeyChar(finalKey.substring(i));
        }
        // we return our final key
        return finalKey;
    }

    // helper function that generate a new character
    // it's in the format of f(x1,x3,x5,x7,x8)=x1+x3+x5+x7+x8
    private String getSingleKeyChar(String key) {
        String result = "";
        int first = Integer.parseInt(key.charAt(0) + "");
        int third = Integer.parseInt(key.charAt(2) + "");
        int fifth = Integer.parseInt(key.charAt(4) + "");
        int seventh = Integer.parseInt(key.charAt(6) + "");
        int eighth = Integer.parseInt(key.charAt(7) + "");
        // after selecting the digits from the key in the argument we perform binary Addition
        result = (first + third + fifth + seventh + eighth) % 2 == 0 ? "0" : "1";
        return result;
    }
    // helper function needed in decryption that convert back binary message to regular text
    private String convertBinaryToMessage(String messageInBinary) {
        // we initialise our result as an empty string
        String result = "";
        // helper variable that take the 8 digits that we want to convert in each iteration
        String part;
        for (int i = 0; i < messageInBinary.length(); i += 8) {
            // part takes the 8 digits
            part = messageInBinary.substring(i, i + 8);
            // convert binary to decimal
            int c = Integer.parseInt(part, 2);
            // convert decimal ASCII to a character
            result += Character.toString(c);
        }
        // return the result
        return result;
    }
    // public decryption function
    // similar to encryption we create the final key in the same way ,and we perform Xor operation also in same way
    public String decrypt(String message, String key) {
        String decryptedMessage = "";
        String finalKey = this.getKey(key, message.length());
        System.out.println("key : " + finalKey);
        for (int i = 0; i < message.length(); i++) {
            decryptedMessage = message.charAt(i) == finalKey.charAt(i) ? decryptedMessage + 0 : decryptedMessage + 1;
        }
        // we print our decrypted message in regular text using our helper function
        System.out.println("decrypted message :" + this.convertBinaryToMessage(decryptedMessage));
        // we return our decrypted message in binary format
        return decryptedMessage;
    }

}
