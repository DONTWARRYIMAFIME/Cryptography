package Cryptography.Methods;

import java.util.Arrays;
import java.util.Objects;

public class Columnar implements CryptographyMethod {

    @Override
    public String encrypt(String sourceStr, String key) {
        int keyLen = key.length();

        int rows = sourceStr.length() / keyLen + 3;
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

//        //Fill matrix with source
//        for (int i = 2; i < mat.length; i++) {
//            for (int j = 0; j < mat[i].length; j++) {
//                mat[i][j] = sourceStr.charAt(j + (i - 2) * mat[i].length);
//            }
//        }

        printMatrix(mat);

        return "null";
    }

    @Override
    public String decrypt(String sourceStr, String key) {
        return "null";
    }

    private int getId(String[][] mat, String value) {
        for (int i = 0; i < mat[0].length; i++) {
            if (Objects.equals(value, mat[0][i]) && mat[1][i] == null) {
                return i;
            }
        }

        return 0;
    }

    private void printMatrix(String[][] mat) {
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[i].length; j++) {
                System.out.print(mat[i][j] != null ? mat[i][j] + " " : "  ");
            }
            System.out.println();
        }
    }
}
