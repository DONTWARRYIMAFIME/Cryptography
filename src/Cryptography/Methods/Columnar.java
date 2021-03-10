package Cryptography.Methods;

import Cryptography.RegularMath;

import java.util.Arrays;
import java.util.Objects;

public class Columnar implements CryptographyMethod {

    @Override
    public String encrypt(String sourceStr, String key) {
        String[][] mat = generateMatrix(sourceStr, key);
        printMatrix(mat);
        return convertMatrix(mat);
    }

    @Override
    public String decrypt(String sourceStr, String key) {
        return "null";
    }

    private void printMatrix(String[][] mat) {
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[i].length; j++) {
                System.out.print(mat[i][j] != null ? String.format("%4s", mat[i][j]) : "  ");
            }
            System.out.println();
        }
    }

    //Encryption
    private String[][] generateMatrix(String sourceStr, String key) {
        int keyLen = key.length();

        int rows = (sourceStr.length() - 1) / keyLen + 3;
        int cols = keyLen;

        String[][] mat = new String[rows][cols];

        //Fill first row with key
        for (int i = 0; i < cols; i++) {
            mat[0][i] = Character.toString(key.charAt(i));
        }

        String[] sorted = key.split("");
        Arrays.sort(sorted);

        //Calculate read order
        for (int i = 0; i < sorted.length; i++) {
            int id = getId(mat, sorted[i]);
            mat[1][id] = Integer.toString(i + 1);
        }

        //Fill matrix with source
        for (int i = 0; i < sourceStr.length(); i++) {
            int r = i / keyLen + 2;
            int c = i % keyLen;

            mat[r][c] = Character.toString(sourceStr.charAt(i));
        }

        return mat;
    }

    private int getId(String[][] mat, String value) {
        for (int i = 0; i < mat[0].length; i++) {
            if (Objects.equals(value, mat[0][i]) && mat[1][i] == null) {
                return i;
            }
        }

        return 0;
    }

    private String convertMatrix(String[][] mat) {
        StringBuilder str = new StringBuilder();

        for (int i = 0; i < mat[0].length; i++) {
            int index = RegularMath.linearSearch(mat[1], Integer.toString(i + 1));
            for (int j = 2; j < mat.length; j++) {
                String symbol = mat[j][index];

                if (symbol != null) {
                    str.append(symbol);
                }
            }

        }

        return str.toString();
    }

}
