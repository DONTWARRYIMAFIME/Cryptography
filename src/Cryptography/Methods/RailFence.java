package Cryptography.Methods;

public class RailFence implements CryptographyMethod {

    @Override
    public String encrypt(String sourceStr, String key) {
        char[][] mat = generateEncryptionMatrix(sourceStr, key);
        printMatrix(mat);
        return convertEncryptionMatrix(mat);
    }

    @Override
    public String decrypt(String sourceStr, String key) {
        char[][] mat = generateDecryptionMatrix(sourceStr, key);
        printMatrix(mat);
        return convertDecryptionMatrix(mat);
    }

    private void printMatrix(char[][] mat) {
        for (char[] chars : mat) {
            for (char aChar : chars) {
                System.out.print(aChar != '\u0000' ? aChar + " " : "  ");
            }
            System.out.println();
        }
    }

    //Encryption
    private char[][] generateEncryptionMatrix(String sourceStr, String key) {
        int rows = key.length();
        int cols = sourceStr.length();

        char[][] mat = new char[rows][cols];

        int period = 2 * (rows - 1);

        for (int i = 0; i < sourceStr.length(); i++) {
            int ost = i % period;
            int row = rows - 1 - Math.abs(rows - 1 - ost);
            mat[row][i] = sourceStr.charAt(i);
        }

        return mat;
    }

    private String convertEncryptionMatrix(char[][] mat) {
        StringBuilder result = new StringBuilder();

        for (char[] rows : mat) {
            for (char col : rows) {
                if (col != ' ') {
                    result.append(col);
                }
            }
        }
        return result.toString();
    }

    //Decryption
    private char[][] generateDecryptionMatrix(String sourceStr, String key) {
        int rows = key.length();
        int cols = sourceStr.length();

        char[][] mat = new char[rows][cols];

        int period = 2 * (rows - 1);
        int position = 0;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                int ost = j % period;

                if (ost == i || ost == period - i) {
                    mat[i][j] = sourceStr.charAt(position);
                    position++;
                }
            }
        }

        return mat;
    }


    private String convertDecryptionMatrix(char[][] mat) {
        StringBuilder result = new StringBuilder();

        int rows = mat.length;
        int period = 2 * (rows - 1);

        for (int i = 0; i < mat[0].length; i++) {
            int ost = i % period;
            int row = rows - 1 - Math.abs(rows - 1 - ost);
            result.append(mat[row][i]);
        }

        return result.toString();
    }

}
