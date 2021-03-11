package Cryptography.Methods;

public class RailFence implements CryptographyMethod {

    @Override
    public String encrypt(String sourceStr, String key) {
        Character[][] mat = generateEncryptionMatrix(sourceStr, key);
        printMatrix(mat);
        return convertEncryptionMatrix(mat);
    }

    @Override
    public String decrypt(String sourceStr, String key) {
        Character[][] mat = generateDecryptionMatrix(sourceStr, key);
        printMatrix(mat);
        return convertDecryptionMatrix(mat);
    }

    private void printMatrix(Character[][] mat) {
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[i].length; j++) {
                System.out.print(mat[i][j] != null ? mat[i][j] + " " : "  ");
            }
            System.out.println();
        }
    }

    //Encryption
    private Character[][] generateEncryptionMatrix(String sourceStr, String key) {
        int rows = key.length();
        int cols = sourceStr.length();

        Character[][] mat = new Character[rows][cols];

        int period = 2 * (rows - 1);

        for (int i = 0; i < sourceStr.length(); i++) {
            int ost = i % period;
            int row = rows - 1 - Math.abs(rows - 1 - ost);
            mat[row][i] = sourceStr.charAt(i);
        }

        return mat;
    }

    private String convertEncryptionMatrix(Character[][] mat) {
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

    //Decryption
    private Character[][] generateDecryptionMatrix(String sourceStr, String key) {
        int rows = key.length();
        int cols = sourceStr.length();

        Character[][] mat = new Character[rows][cols];

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


    private String convertDecryptionMatrix(Character[][] mat) {
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
