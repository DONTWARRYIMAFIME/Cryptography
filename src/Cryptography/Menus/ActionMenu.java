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

        switch (navigation) {
            case "1" -> System.out.println(cryptographyMethod.encrypt(getSourceStr(), getKey()));
            case "2" -> System.out.println(cryptographyMethod.decrypt(getSourceStr(), getKey()));
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

    private String getSourceStr() {
        System.out.println("Input encrypted string: ");
        return scanner.nextLine().replaceAll("\\s+", "").toUpperCase(Locale.ROOT);
    }

    private String getKey() {
        String TERMINATOR_STRING = "";

        System.out.println("Input key: ");

        StringBuilder key = new StringBuilder();

        String strLine;
        while (!(strLine = scanner.nextLine()).equals(TERMINATOR_STRING)) {
            key.append(strLine);
        }

        return key.toString().replaceAll("\\s+", "").toUpperCase(Locale.ROOT);
    }

}
