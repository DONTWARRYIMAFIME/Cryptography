package Cryptography;

import Cryptography.Factory.ColumnarFactory;
import Cryptography.Factory.CryptographyFactory;
import Cryptography.Factory.RailFenceFactory;
import Cryptography.Methods.CryptographyMethod;

import java.util.Locale;
import java.util.Scanner;

public class App {

    Scanner scanner = new Scanner(System.in);
    CryptographyFactory cryptographyFactory;

    public App() {
        launch();
    }

    public void launch() {
        boolean exit = false;

        while (!exit) {
            printMethodNavigation();
            String navigation = scanner.nextLine();

            switch(navigation) {
                case "1":
                    cryptographyFactory = new RailFenceFactory();
                    break;
                case "2":
                    cryptographyFactory = new ColumnarFactory();
                    break;
                case "3":
                    cryptographyFactory = new RailFenceFactory();
                    break;
                case "4":
                    cryptographyFactory = new RailFenceFactory();
                    break;
                case "0":
                    exit = true;
                    break;
                default:
                    System.out.println("Unknown command, try again");
            }

            if (cryptographyFactory != null) {
                CryptographyMethod cryptographyMethod = cryptographyFactory.create();

                printAvailableActions();
                navigation = scanner.nextLine();

                String sourceStr, key;

                switch(navigation) {
                    case "1":
                        System.out.println("Input source string: ");
                        sourceStr = scanner.nextLine().replaceAll("\\s+", "").toUpperCase(Locale.ROOT);
                        System.out.println("Input key: ");
                        key = scanner.nextLine();

                        System.out.println(cryptographyMethod.encrypt(sourceStr, key));
                        break;
                    case "2":
                        System.out.println("Input encrypted string: ");
                        sourceStr = scanner.nextLine();
                        System.out.println("Input key: ");
                        key = scanner.nextLine();

                        System.out.println(cryptographyMethod.decrypt(sourceStr, key));
                        break;
                    case "0":
                        exit = true;
                        break;
                    default:
                        System.out.println("Unknown command, try again");
                        break;
                }

            }

        }
    }

    private void printMethodNavigation() {
        System.out.println("1) Rail fence");
        System.out.println("2) Columnar");
        System.out.println("3) Rail fence");
        System.out.println("4) Rail fence");
        System.out.println("0) Exit");
    }

    private void printAvailableActions() {
        System.out.println("1) Encrypt");
        System.out.println("2) Decrypt");
        System.out.println("0) Exit");
    }

}
