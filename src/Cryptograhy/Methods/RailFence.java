package Cryptograhy.Methods;

import Cryptograhy.Methods.CryptographyMethod;

public class RailFence implements CryptographyMethod {

    private Character[][] mat;

    @Override
    public String encrypt(String sourceStr, String key) {
        int keyLen = key.length();

        mat = new Character[keyLen][sourceStr.length()];

        int period = 2 * (keyLen - 1);

        for (int i = 0; i < sourceStr.length(); i++) {
            int ost = i % period;
            int row = keyLen - 1 - Math.abs(keyLen - 1 - ost);
            mat[row][i] = sourceStr.charAt(i);
        }

        printMatrix(mat);

        return convertMatrix(mat);
    }

    @Override
    public String decrypt(String sourceStr, String key) {
        return "none";
    }

    private String convertMatrix(Character[][] mat) {
        StringBuilder result = new StringBuilder();
        for (Character[] rows : mat) {
            for (Character col : rows) {
                if (col != null) {
                    result.append(col);
                }
            }
        }
        return result.toString();
    }

    private void printMatrix(Character[][] mat) {
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[i].length; j++) {
                System.out.print(mat[i][j] != null ? mat[i][j]  + " " : "  ");
            }
            System.out.println();
        }
    }
}
