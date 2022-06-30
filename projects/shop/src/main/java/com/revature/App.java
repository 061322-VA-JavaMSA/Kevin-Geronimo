package com.revature;

import com.revature.dao.ItemPostgres;
import com.revature.dao.OfferPostgres;
import com.revature.dao.UserPostgres;
import com.revature.model.Item;
import com.revature.model.Offer;
import com.revature.model.User;
import com.revature.service.AuthService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class App {
    private static final Logger logger = LogManager.getLogger(App.class);

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        label:
        while (true) {
            logger.info("""
                    Welcome to Random Shop!
                    1) Login
                    2) Register
                    3) Exit
                    """);
            String input = scanner.nextLine();

            switch (input) {
                case "1": {
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

                    if (user.getRole().equals(User.Role.CUSTOMER)) {
                        printCustomerMenu(scanner, user);
                    } else if (user.getRole().equals(User.Role.EMPLOYEE)) {
                        printEmployeeMenu(scanner, user);
                    } else if (user.getRole().equals(User.Role.MANAGER)) {
                        printManagerMenu(scanner, user);
                    }


                    break;
                }
                case "2": {
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
                    break;
                }
                case "3":
                    break label;
                default:
                    logger.info("\nPlease choose 1 or 2\n");
                    break;
            }
        }


    }

    public static void printCustomerMenu(Scanner scanner, User user) {
        label:
        while (true) {
            logger.info(String.format("""
                    -------------MENU---------------
                    Customer: %s        0) Logout
                                    
                    1) My items
                    2) My Pending offers
                    3) Available items
                    """, user.getUsername()));

            String menuInput = scanner.nextLine();

            switch (menuInput) {
                case "1": {
                    List<Offer> offers = new OfferPostgres().getAll();
                    List<Offer> filtered_offers = offers.stream().filter((offer) -> {
                        return offer.getUser().equals(user) && offer.getItem().getStock().equals(Item.Stock.OWNED);
                    }).toList();

                    logger.info("----------MY ITEMS----------");
                    logger.info(String.format("%-30s", "ITEM"));
                    for (Offer offer : filtered_offers) {
                        logger.info(String.format("%-30s", offer.getItem().getItemName()));
                    }
                    logger.info("");

                    break;
                }
                case "2": {
                    List<Offer> offers = new OfferPostgres().getAll();
                    List<Offer> filtered_offers = offers.stream().filter((offer) -> {
                        return offer.getUser().equals(user) && offer.getItem().getStock().equals(Item.Stock.AVAILABLE);
                    }).toList();

                    logger.info("---------------------------MY PENDING OFFERS-------------------------------");
                    logger.info(String.format("%-30s %-30s %-30s", "ITEM", "OFFER AMOUNT", "DATE"));
                    for (Offer offer : filtered_offers) {
                        logger.info(String.format("%-30s $%-30.2f %-30s", offer.getItem().getItemName(), offer.getAmount(), offer.getDate()));
                    }
                    logger.info("");
                    break;
                }
                case "3":
                    List<Item> items = new ItemPostgres().getAll();
                    logger.info("---------------INVENTORY-----------------");
                    logger.info(String.format("%-30s %-30s", "ITEM", "STOCK"));
                    for (Item item : items) {
                        if (item.getStock().equals(Item.Stock.AVAILABLE)) {
                            logger.info(String.format("%-30s %-30s", item.getItemName(), item.getStock()));
                        }
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

                        List<Item> filteredItem = items.stream().filter((i) -> i.getItemName().equals(itemInput)).toList();
                        if (filteredItem.isEmpty()) {
                            logger.info("\nNo item with that name. Returning to Menu\n");
                            continue;
                        }
                        Offer offer = new Offer(user, filteredItem.get(0), LocalDate.now(), Double.parseDouble(offerInput));
                        Offer checkExistingOffer = new OfferPostgres().get(user.getId(), filteredItem.get(0).getId());
                        if (checkExistingOffer == null) {
                            new OfferPostgres().insert(offer);
                        } else {
                            checkExistingOffer.setAmount(Double.parseDouble(offerInput));
                            checkExistingOffer.setDate(LocalDate.now());
                            new OfferPostgres().update(checkExistingOffer);
                            logger.info("Thanks for updating your offer!\n");
                        }
                    } else {
                        logger.info("Please choose 1 or 2\n");
                    }
                    break;
                case "0":
                    logger.info("Thank you for shopping!\n");
                    break label;
                default:
                    logger.info("Please choose 1, 2, 3 or 4");
                    break;
            }
        }
    }

    public static void printEmployeeMenu(Scanner scanner, User user) {
        label:
        while (true) {
            logger.info(String.format("""
                    -------------MENU---------------
                    Employee: %s        0) Logout
                                    
                    1) View all Offers
                    2) View all Items
                    """, user.getUsername()));

            String menuInput = scanner.nextLine();

            switch (menuInput) {
                case "1": {
                    List<Offer> offers = new OfferPostgres().getAll();
                    logger.info("-".repeat(62) + "ALL OFFERS" + "-".repeat(62)); //java 11+ only for string repeat :(
                    logger.info(String.format("%-30s %-30s %-30s %-30s", "CUSTOMER", "ITEM", "OFFER AMOUNT", "DATE"));
                    for (Offer offer : offers) {
                        logger.info(String.format("%-30s %-30s $%-30s %-30s %-30s",
                                offer.getUser().getUsername(),
                                offer.getItem().getItemName(),
                                offer.getAmount(),
                                offer.getDate(),
                                offer.getItem().getStock()));
                    }
                    logger.info("""
                            --------------------------------
                            1) Accept Offer          3) Menu
                            2) Reject Offer
                            """);

                    String allOffersInput = scanner.nextLine();
                    switch (allOffersInput) {
                        case "1": {
                            logger.info("---------ACCEPT OFFER----------");
                            logger.info("Customer name: ");
                            String customerNameInput = scanner.nextLine();
                            logger.info("Item name: ");
                            String itemNameInput = scanner.nextLine();

                            List<Offer> filtered_offers = offers.stream().filter((offer) -> {
                                return offer.getUser().getUsername().equals(customerNameInput)
                                        && offer.getItem().getItemName().equals(itemNameInput);
                            }).toList();

                            if (filtered_offers.isEmpty()) {
                                logger.info("\nNo item or user with that name. Returning to Menu\n");
                                continue;
                            }

                            Item item = filtered_offers.get(0).getItem();
                            if (!item.getStock().equals(Item.Stock.OWNED)) {
                                item.setStock(Item.Stock.OWNED);
                                new ItemPostgres().update(item);
                            } else {
                                logger.info("That offer has already been accepted.");
                            }

                            //System: remove pending offers for the same item
                            for (Offer offer : offers) {
                                if (!offer.getUser().getUsername().equals(customerNameInput) && offer.getItem().getItemName().equals(itemNameInput)) {
                                    new OfferPostgres().delete(offer.getUser().getId(), offer.getItem().getId());
                                }
                            }

                            break;
                        }
                        case "2": {
                            logger.info("---------REJECT OFFER----------");
                            logger.info("Customer name: ");
                            String customerNameInput = scanner.nextLine();
                            logger.info("Item name: ");
                            String itemNameInput = scanner.nextLine();

                            List<Offer> filtered_offers = offers.stream().filter((offer) -> {
                                return offer.getUser().getUsername().equals(customerNameInput)
                                        && offer.getItem().getItemName().equals(itemNameInput);
                            }).toList();

                            if (filtered_offers.isEmpty()) {
                                logger.info("\nNo item or user with that name. Returning to Menu\n");
                                continue;
                            }
                            if (filtered_offers.get(0).getItem().getStock().equals(Item.Stock.AVAILABLE)) {

                                new OfferPostgres().delete(filtered_offers.get(0).getUser().getId(), filtered_offers.get(0).getItem().getId());
                            } else {
                                logger.info("\nCan't reject accepted offer");
                            }
                            break;
                        }
                        case "3":
                            continue;
                        default:
                            logger.info("Please choose 1 or 2 or 3\n");
                            break;
                    }
                    logger.info("");
                    break;
                }
                case "2": {
                    List<Item> items = new ItemPostgres().getAll();
                    logger.info("---------------INVENTORY-----------------");
                    logger.info(String.format("%-30s %-30s", "ITEM", "STOCK"));
                    for (Item item : items) {
                        logger.info(String.format("%-30s %-30s", item.getItemName(), item.getStock()));
                    }

                    logger.info("""
                            ---------------------------
                            1) Add Item         3) Menu
                            2) Remove Item
                            """);

                    String inventoryInput = scanner.nextLine();
                    switch (inventoryInput) {
                        case "1": {
                            logger.info("---------ADD ITEM----------");
                            logger.info("Item name: ");
                            String itemNameInput = scanner.nextLine();

                            Item item = new Item();
                            item.setItemName(itemNameInput);
                            item.setStock(Item.Stock.AVAILABLE);

                            List<Item> existingItems = items.stream().filter(item1 -> item1.getItemName().equals(itemNameInput)).toList();
                            if (existingItems.isEmpty()) {
                                new ItemPostgres().insert(item);
                            } else {
                                logger.info("That item is already available.\n");
                            }
                            break;
                        }
                        case "2": {
                            logger.info("---------REMOVE ITEM----------");
                            logger.info("Item name: ");
                            String itemNameInput = scanner.nextLine();

                            Item item = new ItemPostgres().get(itemNameInput);

                            if (item != null && item.getStock().equals(Item.Stock.AVAILABLE)) {
                                List<Offer> checkExistingOffers = new OfferPostgres().getAll()
                                        .stream().filter(offer -> offer.getItem().getId() == item.getId()).toList();
                                if (checkExistingOffers.isEmpty()) {
                                    new ItemPostgres().delete(item.getId());
                                } else {
                                    logger.info("Please reject existing offers before removing item\n");
                                }
                            } else {
                                logger.info("\nNo item found with that name or is owned.\n");
                            }
                            break;
                        }
                        case "3": {
                            continue;
                        }
                        default:
                            logger.info("Please choose 1, 2 or 3.\n");
                            break;
                    }
                    break;
                }
                case "0":
                    logger.info("Thank you for being our employee!\n");
                    break label;
                default:
                    logger.info("Please choose 1, 2, 3 or 4");
                    break;
            }
        }
    }

    public static void printManagerMenu(Scanner scanner, User user) {
        label:
        while (true) {
            logger.info(String.format("""
                    -----------------MENU-------------------
                    Manager: %s                0) Logout
                                                    
                    1) View all employees
                    2) View all accepted offers
                    """, user.getUsername()));

            String menuInput = scanner.nextLine();

            switch (menuInput) {
                case "1": {
                    List<User> users = new UserPostgres().getAll();
                    logger.info("---------------EMPLOYEES-----------------");
                    logger.info(String.format("%-30s %-30s", "USERNAME", "ROLE"));
                    for (User u : users) {
                        logger.info(String.format("%-30s %-30s", u.getUsername(), u.getRole()));
                    }

                    logger.info("""
                            -------------------------------
                            1) Add Employee         3) Menu
                            2) Remove Employee
                            """);

                    String employeesInput = scanner.nextLine();
                    switch (employeesInput) {
                        case "1": {
                            logger.info("---------ADD EMPLOYEE----------");
                            logger.info("Employee name: ");
                            String employeeNameInput = scanner.nextLine();

                            User u = new User();
                            u.setUsername(employeeNameInput);
                            u.setPassword((String.valueOf((int) ((Math.random() * (1010 - 1000)) + 1000))));
                            u.setRole(User.Role.EMPLOYEE);

                            List<User> existingUsers = users.stream().filter(u1 -> u1.getUsername().equals(employeeNameInput)).toList();
                            if (existingUsers.isEmpty()) {
                                new UserPostgres().insert(u);
                            } else {
                                logger.info("Employee already exists.\n");
                            }
                            break;
                        }
                        case "2": {
                            logger.info("---------REMOVE EMPLOYEE----------");
                            logger.info("Employee name: ");
                            String employeeNameInput = scanner.nextLine();

                            User u = new UserPostgres().get(employeeNameInput);

                            if (u != null && u.getRole().equals(User.Role.EMPLOYEE)) {
                                new UserPostgres().delete(u.getId());
                            } else {
                                logger.info("\nNo Employee found with that name.\n");
                            }
                            break;
                        }
                        case "3": {
                            continue;
                        }
                        default: {
                            logger.info("Please choose 1, 2 or 3.\n");
                            break;
                        }
                    }
                    break;
                }
                case "2": {
                    // TODO: repeated code. create a service
                    List<Offer> offers = new OfferPostgres().getAll();
                    logger.info("-".repeat(62) + "ALL OFFERS" + "-".repeat(62)); //java 11+ only for string repeat :(
                    logger.info(String.format("%-30s %-30s %-30s %-30s", "CUSTOMER", "ITEM", "OFFER AMOUNT", "DATE"));
                    for (Offer offer : offers) {
                        logger.info(String.format("%-30s %-30s $%-30s %-30s %-30s",
                                offer.getUser().getUsername(),
                                offer.getItem().getItemName(),
                                offer.getAmount(),
                                offer.getDate(),
                                offer.getItem().getStock()));
                    }
                    break;
                }
                case "0": {
                    break label;
                }
                default: {
                    logger.info("Please choose 1, 2 or 3.\n");
                    break;
                }
            }
        }
    }
}
