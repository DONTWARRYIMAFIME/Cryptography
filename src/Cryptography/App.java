package Cryptography;

import Cryptography.Menus.MainMenu;
import Cryptography.Menus.Menu;

import java.util.*;

public class App {

    private final Deque<Menu> stack = new ArrayDeque<>();

    public void launch() {

        stack.offerLast(new MainMenu(stack));

        while (!stack.isEmpty()) {
            stack.peekLast().launch();
            if (stack.peekLast().isExit()) {
                stack.pollLast();
            }
        }

    }
}
