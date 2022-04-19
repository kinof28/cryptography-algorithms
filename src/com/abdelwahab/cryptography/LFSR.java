package com.abdelwahab.cryptography;

public class LFSR {

    public String encrypt(String message, String key) {
        String encryptedMessage = "";
        String messageInBinary = this.getMessageInBinary(message);
        System.out.println("message in binary : " + messageInBinary);
        String finalKey = this.getKey(key, messageInBinary.length());
        System.out.println("key : " + finalKey);
        for (int i = 0; i < messageInBinary.length(); i++) {
            encryptedMessage = messageInBinary.charAt(i) == finalKey.charAt(i) ? encryptedMessage + 0 : encryptedMessage + 1;
        }
        return encryptedMessage;
    }

    private String getMessageInBinary(String message) {
        String messageInBinary = "";
        for (int i = 0; i < message.length(); i++) {
            messageInBinary += charToBinary(message.charAt(i));
        }
        return messageInBinary;
    }

    private String charToBinary(char c) {
        String binary = Integer.toBinaryString(c);
        String leadingZeros = "00000000";
        String result = leadingZeros.substring(0, 8 - binary.length()) + binary;
        return result;
    }

    private String getKey(String key, int length) {
        String finalKey = key;
        for (int i = 0; i < length - key.length(); i++) {
            finalKey += this.getSingleKeyChar(finalKey.substring(i));
        }
        return finalKey;
    }

    private String getSingleKeyChar(String key) {
        String result = "";
        int first = Integer.parseInt(key.charAt(0) + "");
        int third = Integer.parseInt(key.charAt(2) + "");
        int fifth = Integer.parseInt(key.charAt(4) + "");
        int seventh = Integer.parseInt(key.charAt(6) + "");
        int eighth = Integer.parseInt(key.charAt(7) + "");
        result = (first + third + fifth + seventh + eighth) % 2 == 0 ? "0" : "1";
        return result;
    }

        private String convertBinaryToMessage(String messageInBinary){
        String result="";
        String part;
        for (int i = 0; i < messageInBinary.length(); i+=8) {
            part=messageInBinary.substring(i,i+8);
            int c=Integer.parseInt(part,2);
            result+=Character.toString(c);
        }
        return result;
    }
    public String decrypt(String message, String key) {
        String decryptedMessage = "";
        String finalKey = this.getKey(key, message.length());
        System.out.println("key : " + finalKey);
        for (int i = 0; i < message.length(); i++) {
            decryptedMessage = message.charAt(i) == finalKey.charAt(i) ? decryptedMessage + 0 : decryptedMessage + 1;
        }
        System.out.println("decrypted message :"+this.convertBinaryToMessage(decryptedMessage));
        return decryptedMessage;
    }


}
