package de.holhar.java_dev_kb.data_structures_and_algorithms._deprecated.tree;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

public class TreeApp {

    private static final Logger LOGGER = LoggerFactory.getLogger(TreeApp.class);

    @SuppressWarnings("resource")
    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);

        /* Creating object of BT */
        BinaryTree bt = new BinaryTree();

        /* Perform tree operations */
        LOGGER.debug("Binary Tree Test\n");
        char ch;

        do {
            LOGGER.debug("\nBinary Tree Operations\n");
            LOGGER.debug("1. insert ");
            LOGGER.debug("2. search");
            LOGGER.debug("3. count nodes");
            LOGGER.debug("4. check empty");

            int choice = scan.nextInt();

            switch (choice) {
                case 1:
                    LOGGER.debug("Enter integer element to insert");
                    bt.add(scan.nextInt());
                    break;
                case 2:
                    LOGGER.debug("Enter integer element to search");
                    LOGGER.debug("Search result : {}", bt.search(scan.nextInt()));
                    break;
                case 3:
                    LOGGER.debug("Nodes = {}", bt.countNodes());
                    break;
                case 4:
                    LOGGER.debug("Empty status = {}", bt.isEmpty());
                    break;
                default:
                    LOGGER.debug("Wrong Entry");
                    break;
            }

            /* Display tree */
            LOGGER.debug("Post order : ");
            bt.postOrder();
            LOGGER.debug("Pre order : ");
            bt.preOrder();
            LOGGER.debug("In order : ");
            bt.inOrder();
            LOGGER.debug("Do you want to continue (Type y or n)");

            ch = scan.next().charAt(0);
        } while (ch == 'Y' || ch == 'y');
    }

}
