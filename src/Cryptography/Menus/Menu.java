package Cryptography.Menus;

import java.util.Deque;
import java.util.Scanner;

public class Menu implements Navigable {

    protected final Scanner scanner = new Scanner(System.in);
    protected String navigation;
    protected boolean exit = false;
    protected Deque<Menu> stack;

    public Menu(Deque<Menu> stack) {
        this.stack = stack;
    }

    public void launch() {
        printNavigation();
        realizeNavigation();
    }

    public boolean isExit() {
        return exit;
    }


    @Override
    public void printNavigation() {
        System.out.println("Override this method");
    }

    @Override
    public void realizeNavigation() {
        System.out.println("Override this method");
    }
}
