package Cryptograhy;

import Cryptograhy.Factory.CryptographyFactory;
import Cryptograhy.Factory.RailwayFactory;
import Cryptograhy.Methods.CryptographyMethod;

import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int key = scanner.nextInt();
        scanner.nextLine();
        String sourceStr = scanner.nextLine().replaceAll("\\s+", "").toUpperCase(Locale.ROOT);

        CryptographyFactory cryptographyFactory = new RailwayFactory();
        CryptographyMethod cryptographyMethod = cryptographyFactory.create();

        System.out.println(cryptographyMethod.encrypt(key, sourceStr));
    }

}


