package Cryptography.Methods;

import java.util.Arrays;

public class Vigenere implements CryptographyMethod {

    //Change this, if you gonna use another alphabet
    private final char firstChar = 'A';
    private final char lastChar = 'Z';

    private final int aLen = lastChar - firstChar + 2;

    @Override
    public String encrypt(String sourceStr, String key) {
        char[][] mat = generateMatrix();
        printMatrix(mat);
        String sKey = supplementKey(sourceStr, key);
        return makeEncryption(sourceStr, sKey, mat);
    }

    @Override
    public String decrypt(String sourceStr, String key) {
        char[][] mat = generateMatrix();
        printMatrix(mat);
        String sKey = supplementKey(sourceStr, key);
        return makeDecryption(sourceStr, sKey, mat);
    }

    private char[][] generateMatrix() {

        char[][] mat = new char[aLen][aLen];

        for (int i = 0; i < aLen - 1; i++) {
            char symbol = (char)(firstChar + i);
            mat[0][i + 1] = symbol;
            mat[i + 1][0] = symbol;
        }

        for (int i = 0; i < aLen - 1; i++) {
            for (int j = 0; j < aLen - 1; j++) {
                char symbol = (char)(firstChar + ((i + j) % (aLen - 1)));
                mat[i + 1][j + 1] = symbol;
            }
        }

        return mat;
    }

    private void printMatrix(char[][] mat) {
        for (char[] col : mat) {
            for (char symbol : col) {
                System.out.print(symbol + " ");
            }
            System.out.println();
        }
    }

    private String supplementKey(String sourceStr, String key) {
        int strLen = sourceStr.length();
        int keyLen = key.length();

        if (keyLen >= strLen) {
            return key;
        }

        StringBuilder sKey = new StringBuilder(key);

        //Progressive key
        int iterator = 0;
        int loop = keyLen;


        while (sKey.length() < strLen) {
            int nextSymbol = key.charAt(iterator) + loop / keyLen - firstChar;
            nextSymbol = firstChar + ((nextSymbol) % (aLen - 1));
            sKey.append((char)(nextSymbol));
            iterator = ++iterator % keyLen;
            loop++;
        }

//        //Basic key
//        int iterator = 0;
//
//        while (sKey.length() < strLen) {
//            sKey.append(key.charAt(iterator));
//            iterator = ++iterator % keyLen;
//        }

        return  sKey.toString();
    }

    //Encryption
    private String makeEncryption(String sourceStr, String key, char[][] mat) {

        StringBuilder str = new StringBuilder();
        for (int i = 0; i < sourceStr.length(); i++) {
            int col = Arrays.binarySearch(mat[0], key.charAt(i));
            int row = Arrays.binarySearch(mat[0], sourceStr.charAt(i));

            try {
                str.append(mat[col][row]);
            } catch (IndexOutOfBoundsException e) {
                char errorChar = col < row ? key.charAt(i) : sourceStr.charAt(i);
                System.out.format("Localization problem. Symbol '%c' out of alphabet\n", errorChar);
            }
        }

        return str.toString();
    }

    //Decryption
    private String makeDecryption(String sourceStr, String key, char[][] mat) {

        StringBuilder str = new StringBuilder();
        for (int i = 0; i < sourceStr.length(); i++) {
            int col = Arrays.binarySearch(mat[1], key.charAt(i));
            int row = new String(mat[col]).indexOf(sourceStr.charAt(i));

            try {
                str.append(mat[row][0]);
            } catch (IndexOutOfBoundsException e) {
                char errorChar = col < row ? key.charAt(i) : sourceStr.charAt(i);
                System.out.format("Localization problem. Symbol '%c' out of alphabet\n", errorChar);
            }
        }

        return str.toString();

    }

}
