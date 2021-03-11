package Cryptography.Menus;

import Cryptography.Factory.ColumnarFactory;
import Cryptography.Factory.CryptographyFactory;
import Cryptography.Factory.RailFenceFactory;
import Cryptography.Factory.VigenereFactory;

import java.util.Deque;

public class MainMenu extends Menu {

    public MainMenu(Deque<Menu> stack) {
        super(stack);
    }

    @Override
    public void realizeNavigation() {
        CryptographyFactory cryptographyFactory = null;

        navigation = scanner.nextLine();

        switch (navigation) {
            case "1" -> cryptographyFactory = new RailFenceFactory();
            case "2" -> cryptographyFactory = new ColumnarFactory();
            case "3" -> cryptographyFactory = new RailFenceFactory();
            case "4" -> cryptographyFactory = new VigenereFactory();
            case "0" -> exit = true;
            default -> System.out.println("Unknown command, try again");
        }

        if (cryptographyFactory != null) {
            stack.offerLast(new ActionMenu(stack, cryptographyFactory.create()));
        }
    }

    @Override
    public void printNavigation() {
        System.out.println("1) Rail fence");
        System.out.println("2) Columnar");
        System.out.println("3) Rail fence");
        System.out.println("4) Vigenere");
        System.out.println("0) Exit");
    }
}
