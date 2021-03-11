package Cryptography.Methods;

import Cryptography.RegularMath;

public class Vigenere implements CryptographyMethod {

    //Change this, if you gonna use another alphabet
    private final char firstChar = 'A';
    private final char lastChar = 'Z';

    private final int aLen = lastChar - firstChar + 2;

    @Override
    public String encrypt(String sourceStr, String key) {
        Character[][] mat = generateMatrix();
        printMatrix(mat);
        String sKey = supplementKey(sourceStr, key);
        return makeEncryption(sourceStr, sKey, mat);
    }

    @Override
    public String decrypt(String sourceStr, String key) {
        Character[][] mat = generateMatrix();
        printMatrix(mat);
        String sKey = supplementKey(sourceStr, key);
        return makeDecryption(sourceStr, sKey, mat);
    }

    private Character[][] generateMatrix() {

        Character[][] mat = new Character[aLen][aLen];

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

    private void printMatrix(Character[][] mat) {
        for (Character col[] : mat) {
            for (Character symbol : col) {
                System.out.print(symbol != null ? symbol + " " : "  ");
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
    private String makeEncryption(String sourceStr, String key, Character[][] mat) {

        StringBuilder str = new StringBuilder();
        for (int i = 0; i < sourceStr.length(); i++) {
            int row = RegularMath.linearSearch(mat[0], key.charAt(i));
            int col = RegularMath.linearSearch(mat[0], sourceStr.charAt(i));

            try {
                str.append(mat[row][col]);
            } catch (IndexOutOfBoundsException e) {
                System.out.format(
                        "Localization problem. Symbol '%c' out of alphabet\n",
                        row < 0 ? key.charAt(i) : sourceStr.charAt(i)
                );
            }
        }

        return str.toString();
    }

    //Decryption
    private String makeDecryption(String sourceStr, String key, Character[][] mat) {

        StringBuilder str = new StringBuilder();
        for (int i = 0; i < sourceStr.length(); i++) {
            int row = RegularMath.linearSearch(mat[0], key.charAt(i));
            int col = RegularMath.linearSearch(mat[row], sourceStr.charAt(i));

            try {
                str.append(mat[1][col]);
            } catch (IndexOutOfBoundsException e) {
                System.out.format(
                        "Localization problem. Symbol '%c' out of alphabet\n",
                        row < 0 ? key.charAt(i) : sourceStr.charAt(i)
                );
            }
        }

        return str.toString();

    }

}
