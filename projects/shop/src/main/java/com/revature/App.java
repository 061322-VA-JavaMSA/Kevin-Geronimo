package com.revature;

import com.revature.controller.UserController;
import com.revature.dao.ItemPostgres;
import com.revature.dao.OfferPostgres;
import com.revature.dao.UserPostgres;
import com.revature.model.Item;
import com.revature.model.Offer;
import com.revature.model.User;
import com.revature.service.AuthService;
import com.revature.view.UserView;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class App {
    private static final Logger logger = LogManager.getLogger(App.class);

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            UserView.welcomeScreen();
            String input = scanner.nextLine();

            if (input.equals("1")) {
                logger.info("------LOGIN------");
                logger.info("Username: ");
                String username = scanner.nextLine();
                logger.info("Password: ");
                String password = scanner.nextLine();
                logger.info("");
                User user = null;
                try {
                    user = new AuthService().login(username, password);
                } catch (Exception e) {
                    logger.info("Wrong credentials. Please try again");
                    logger.info("");
                    continue;
                }

                while (true) {
                    UserView.userMenuScreen(user.getUsername());
                    String menuInput = scanner.nextLine();

                    if (menuInput.equals("1")) {
                        List<Offer> offers = new OfferPostgres().getAll();
                        logger.info(offers);
                    } else if (menuInput.equals("3")) {
                        List<Item> items = new ItemPostgres().getAll();
                        logger.info("--------INVENTORY--------");
                        for (Item item : items) {
                            logger.info(String.format("Item: %s | Stock: %s", item.getItemName(), item.getStock()));
                        }

                        logger.info("""
                                ------------------------
                                1) Menu
                                2) Make an offer
                                """);

                        String inventoryInput = scanner.nextLine();

                        if (inventoryInput.equals("2")) {
                            logger.info("---------MAKE AN OFFER----------");
                            logger.info("Item: ");
                            String itemInput = scanner.nextLine();
                            logger.info("Offer amount: ");
                            String offerInput = scanner.nextLine();

                            List<Item> i2 = items.stream().filter((i) -> i.getItemName().equals(itemInput)).toList();
                            Offer offer = new Offer(user, i2.get(0), LocalDate.now(), Double.parseDouble(offerInput));
                        }
                    }
                }

            } else if (input.equals("2")) {
                logger.info("-------REGISTER------");
                logger.info("Username: ");
                String username = scanner.nextLine();
                logger.info("Password: ");
                String password = scanner.nextLine();
                logger.info("");
                try {
                    new AuthService().register(username, password);
                } catch (Exception e) {
                    logger.info(e.getMessage());
                    logger.info("");
                }
            } else {
                logger.info("\nPlease choose 1 or 2\n");
            }


            UserController userController = new UserController(new UserPostgres(), new UserView());
            userController.updateView();
        }


    }
}
