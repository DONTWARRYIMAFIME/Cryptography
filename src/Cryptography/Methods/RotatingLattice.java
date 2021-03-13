package Cryptography.Methods;

import java.util.Random;

public class RotatingLattice implements CryptographyMethod {

    //Change this, if you gonna use another alphabet
    private final char firstChar = 'А';
    private final char lastChar = 'Я';

    private final int aLen = lastChar - firstChar;

    @Override
    public String encrypt(String sourceStr, String key) {
        char[][] keys = convertKey(key);
        char[][] mat = makeEncryption(sourceStr, keys);
        supplementMatrix(mat);
        printMatrix(mat);
        return convertEncryptionMatrix(mat);
    }

    @Override
    public String decrypt(String sourceStr, String key) {
        char[][] keys = convertKey(key);
        return makeDecryption(sourceStr, keys);
    }

    private void printMatrix(char[][] mat) {
        for (char[] chars : mat) {
            for (char aChar : chars) {
                System.out.print(aChar != '\u0000' ? aChar + " " : ". ");
            }
            System.out.println();
        }
    }

    private char[][] convertKey(String key) {
        char[] temp = key.toCharArray();
        int len = (int) Math.round(Math.sqrt(temp.length));

        char[][] keys = new char[len][len];

        for (int i = 0; i < len; i++) {
            System.arraycopy(temp, i * len, keys[i], 0, len);
        }

        return keys;
    }

    private char[][] rotateMatrix(char [][] mat){

        int len = mat.length;
        char[][] temp = new char[len][len];

        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                temp[i][j] = mat[len - j - 1][i];
            }
        }

        return temp;
    }

    //Encryption
    private char[][] makeEncryption(String sourceStr, char[][] keys) {
        int len = keys.length;
        char[][] mat = new char[len][len];

        int iterator = 0;

        for(;;) {
            for (int i = 0; i < len; i++) {
                for (int j = 0; j < len; j++) {
                    if (keys[i][j] == '1') {
                        try {
                            mat[i][j] = sourceStr.charAt(iterator);
                        } catch (StringIndexOutOfBoundsException e) {
                            return mat;
                        }
                        iterator++;
                    }
                }
            }

            keys = rotateMatrix(keys);
        }

    }

    private void supplementMatrix(char[][] mat) {
        Random random = new Random();

        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[i].length; j++) {
                if (mat[i][j] == '\u0000') {
                    mat[i][j] = (char) (random.nextInt(aLen + 1) + firstChar);
                }
            }
        }

    }

    private String convertEncryptionMatrix(char[][] mat) {
        StringBuilder str = new StringBuilder();

        for (char[] chars : mat) {
            for (char aChar : chars) {
                str.append(aChar);
            }
        }

        return str.toString();
    }

    //Decryption
    private String makeDecryption(String sourceStr, char[][] keys) {
        StringBuilder str = new StringBuilder();

        for (int k = 0; k < 3; k++) {
            for (int i = 0; i < keys.length; i++) {
                for (int j = 0; j < keys.length; j++) {
                    if (keys[i][j] == '1') {
                        str.append(sourceStr.charAt(j + i * keys.length));
                    }
                }
            }

            keys = rotateMatrix(keys);
        }

        return str.toString();
    }

}
