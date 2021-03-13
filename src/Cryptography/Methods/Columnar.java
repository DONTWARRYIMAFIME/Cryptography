package Cryptography.Methods;

public class Columnar implements CryptographyMethod {

    @Override
    public String encrypt(String sourceStr, String key) {
        char[][] mat = generateEncryptionMatrix(sourceStr, key);
        System.out.println("Normal matrix: ");
        printMatrix(mat);
        sortMatrix(mat);
        System.out.println("Sorted matrix: ");
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
                System.out.print(aChar + " ");
            }
            System.out.println();
        }
    }

    //Encryption
    private char[][] generateEncryptionMatrix(String sourceStr, String key) {

        int cols = key.length();
        int rows = (sourceStr.length() - 1) / cols + 2;

        char[][] mat = new char[rows][cols];

        //Fill first row with key
        System.arraycopy(key.toCharArray(), 0, mat[0], 0, cols);

        //Fill matrix with source
        for (int i = 0; i < sourceStr.length(); i++) {
            int r = i / cols + 1;
            int c = i % cols;

            mat[r][c] = sourceStr.charAt(i);
        }

        return mat;
    }

    private String convertEncryptionMatrix(char[][] mat) {
        StringBuilder str = new StringBuilder();

        for (int i = 0; i < mat[0].length; i++) {
            for (int j = 1; j < mat.length; j++) {
                str.append(mat[j][i]);
            }
        }

        return str.toString();
    }

    private void sortMatrix(char[][] mat) {

        for (int i = 0; i < mat[0].length - 1; i++)
        {
            for (int j = (mat[0].length - 1); j > i; j--)
            {
                if (mat[0][j - 1] > mat[0][j])
                {
                    for (int k = 0; k < mat.length; k++) {
                        char temp = mat[k][j - 1];
                        mat[k][j - 1] = mat[k][j];
                        mat[k][j] = temp;
                    }
                }
            }
        }
    }

    //Decryption
    private char[][] generateDecryptionMatrix(String sourceStr, String key) {

        int cols = key.length();
        int rows = (sourceStr.length() - 1) / cols + 2;

        char[][] mat = new char[rows][cols];

        //Fill first row with key
        System.arraycopy(key.toCharArray(), 0, mat[0], 0, cols);

        //Fill matrix with source
        int fullBlocks = sourceStr.length() % cols;
        int iterator = 0;

        for (int i = 0; i < cols; i++) {
            int col = getMinId(mat);

            int blockLen = col < fullBlocks ? rows : rows - 1;

            for (int j = 1; j < blockLen; j++) {
                mat[j][col] = sourceStr.charAt(iterator);
                iterator++;
            }
        }

        return mat;
    }

    private int getMinId(char[][] mat) {
        int minId = -1;
        int minVal = 0;

        for (int i = 0; i < mat[0].length; i++) {
            if (mat[1][i] == '\u0000') {
                if (minId == -1 || mat[0][i] < minVal ) {
                    minId = i;
                    minVal = mat[0][minId];
                }
            }
        }

        return minId;
    }

    private String convertDecryptionMatrix(char[][] mat) {
        StringBuilder str = new StringBuilder();

        for (int i = 1; i < mat.length; i++) {
            for (int j = 0; j < mat[i].length; j++) {
                str.append(mat[i][j]);
            }
        }

        return str.toString();
    }

}
