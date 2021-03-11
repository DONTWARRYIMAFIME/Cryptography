package Cryptography.Menus;

import Cryptography.Methods.CryptographyMethod;

import java.util.Deque;
import java.util.Locale;

public class ActionMenu extends Menu {

    private final CryptographyMethod cryptographyMethod;

    public ActionMenu(Deque<Menu> stack, CryptographyMethod cryptographyMethod) {
        super(stack);
        this.cryptographyMethod = cryptographyMethod;
    }

    @Override
    public void realizeNavigation() {
        navigation = scanner.nextLine();

        String sourceStr, key;

        switch (navigation) {
            case "1" -> {
                System.out.println("Input source string: ");
                sourceStr = scanner.nextLine().replaceAll("\\s+", "").toUpperCase(Locale.ROOT);
                System.out.println("Input key: ");
                key = scanner.nextLine();
                System.out.println(cryptographyMethod.encrypt(sourceStr, key));
            }
            case "2" -> {
                System.out.println("Input encrypted string: ");
                sourceStr = scanner.nextLine();
                System.out.println("Input key: ");
                key = scanner.nextLine();
                System.out.println(cryptographyMethod.decrypt(sourceStr, key));
            }
            case "0" -> exit = true;
            default -> System.out.println("Unknown command, try again");
        }
    }

    @Override
    public void printNavigation() {
        System.out.println("1) Encrypt");
        System.out.println("2) Decrypt");
        System.out.println("0) Back");
    }
}
