package Cryptograhy.Methods;

import Cryptograhy.Methods.CryptographyMethod;

public class Railway implements CryptographyMethod {

    @Override
    public String encrypt(int key, String sourceStr) {
        Character[][] mat = new Character[key][sourceStr.length()];

        int period = 2 * (key - 1);

        for (int i = 0; i < sourceStr.length(); i++) {
            int ost = i % period;
            int row = key - 1 - Math.abs(key - 1 - ost);
            mat[row][i] = sourceStr.charAt(i);
        }

        printMatrix(mat);

        return convertMatrix(mat);
    }

    @Override
    public String decrypt() {
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
        for (Character rows[] : mat) {
            for (int j = 0; j < rows.length; j++) {
                System.out.print(rows[j] != null ? rows[j]  + " " : " ");
            }
            System.out.println();
        }
    }
}
