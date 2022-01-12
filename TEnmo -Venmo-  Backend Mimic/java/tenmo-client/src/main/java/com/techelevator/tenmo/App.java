package com.techelevator.tenmo;

import com.techelevator.tenmo.model.*;
import com.techelevator.tenmo.services.AuthenticationService;
import com.techelevator.tenmo.services.AuthenticationServiceException;
import com.techelevator.tenmo.services.model.AccountService;
import com.techelevator.tenmo.services.model.TransferService;
import com.techelevator.tenmo.services.model.UserService;
import com.techelevator.view.ConsoleService;
import io.cucumber.java.en_old.Ac;
import org.apiguardian.api.API;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.List;

public class App {

    private static final String API_BASE_URL = "http://localhost:8080/";

    private static final String MENU_OPTION_EXIT = "Exit";
    private static final String LOGIN_MENU_OPTION_REGISTER = "Register";
    private static final String LOGIN_MENU_OPTION_LOGIN = "Login";
    private static final String[] LOGIN_MENU_OPTIONS = {LOGIN_MENU_OPTION_REGISTER, LOGIN_MENU_OPTION_LOGIN, MENU_OPTION_EXIT};
    private static final String MAIN_MENU_OPTION_VIEW_BALANCE = "View your current balance";
    private static final String MAIN_MENU_OPTION_SEND_BUCKS = "Send TE bucks";
    private static final String MAIN_MENU_OPTION_VIEW_PAST_TRANSFERS = "View your past transfers";
    private static final String MAIN_MENU_OPTION_REQUEST_BUCKS = "Request TE bucks";
    private static final String MAIN_MENU_OPTION_VIEW_PENDING_REQUESTS = "View your pending requests";
    private static final String MAIN_MENU_OPTION_LOGIN = "Login as different user";
    private static final String[] MAIN_MENU_OPTIONS = {MAIN_MENU_OPTION_VIEW_BALANCE, MAIN_MENU_OPTION_SEND_BUCKS, MAIN_MENU_OPTION_VIEW_PAST_TRANSFERS, MAIN_MENU_OPTION_REQUEST_BUCKS, MAIN_MENU_OPTION_VIEW_PENDING_REQUESTS, MAIN_MENU_OPTION_LOGIN, MENU_OPTION_EXIT};

    private AuthenticatedUser currentUser;
    private ConsoleService console;
    private AuthenticationService authenticationService;
    private AccountService accountService;
    private UserService userService;
    private TransferService transferService;

    public static void main(String[] args) {
        App app = new App(new ConsoleService(System.in, System.out), new AuthenticationService(API_BASE_URL),
                new AccountService(API_BASE_URL), new UserService(API_BASE_URL), new TransferService(API_BASE_URL));
        app.run();
    }

    public App(ConsoleService console, AuthenticationService authenticationService, AccountService accountService,
               UserService userService, TransferService transferService) {
        this.console = console;
        this.authenticationService = authenticationService;
        this.accountService = accountService;
        this.userService = userService;
        this.transferService = transferService;
    }

    public void run() {
        System.out.println("*********************");
        System.out.println("* Welcome to TEnmo! *");
        System.out.println("*********************");

        registerAndLogin();
        mainMenu();
    }

    private void mainMenu() {
        while (true) {
            String choice = (String) console.getChoiceFromOptions(MAIN_MENU_OPTIONS);
            if (MAIN_MENU_OPTION_VIEW_BALANCE.equals(choice)) {
                viewCurrentBalance();
            } else if (MAIN_MENU_OPTION_VIEW_PAST_TRANSFERS.equals(choice)) {
                viewTransferHistory();
            } else if (MAIN_MENU_OPTION_VIEW_PENDING_REQUESTS.equals(choice)) {
                viewPendingRequests();
            } else if (MAIN_MENU_OPTION_SEND_BUCKS.equals(choice)) {
                TransferDTO transfers = sendBucks();
                TransferDTO completedTransfer = transfers;
                if (transfers != null) {
                    completedTransfer = transferService.createTransfer(transfers);
                    if (completedTransfer == null) {
                        System.out.println("\n****** You have cancelled this transaction or have insufficient funds! ******");
                    }
                }
            } else if (MAIN_MENU_OPTION_REQUEST_BUCKS.equals(choice)) {
                requestBucks();
            } else if (MAIN_MENU_OPTION_LOGIN.equals(choice)) {
                login();
            } else {
                // the only other option on the main menu is to exit
                exitProgram();
            }
        }
    }

    private void viewCurrentBalance() {
        // TODO Auto-generated method stub
        BigDecimal balance = accountService.getBalance();
        System.out.println("Your current account balance is: $" + balance);
    }

    private void viewTransferHistory() {
        TransferDetailDTO transferDetailDTO = new TransferDetailDTO();
        int tranferId = 0;
        int userId = currentUser.getUser().getId();
        List<ViewTransfersDTO> myTransfers = transferService.getMyTransfers(userId);
        System.out.println("**************************************************");
        System.out.printf("%-15s %-13s %-13s %-5s\n", "Transfer ID", "From", "To", "Amount");
        System.out.println("**************************************************");
        for (int i = 0; i < myTransfers.size(); i++) {
            System.out.printf("%-15s %-13s %-13s %-5s\n", myTransfers.get(i).getTransferId() ,myTransfers.get(i).getUserTo(),
                    myTransfers.get(i).getUserFrom(), "$"+myTransfers.get(i).getAmount());        }
        int choice = console.getUserInputInteger("Please enter TransferId to view details: (-0- to cancel) \n");
        for (int i = 0; i < myTransfers.size(); i++) {
            if (choice == myTransfers.get(i).getTransferId()) {
                tranferId = choice;
                transferDetailDTO = transferService.getTransferDetails(tranferId);
                System.out.println("***********************");
                System.out.println("    Transfer Details");
                System.out.println("***********************");
                System.out.println("Transfer ID:    " + transferDetailDTO.getTransferId() + "\n" +
                        "From:           " + transferDetailDTO.getUserTo() + "\n" + "To:             " + transferDetailDTO.getUserFrom() +
                        "\n" + "Type:           " + transferDetailDTO.getTransferTypeDesc() + "\n" + "Status:         " +
                        transferDetailDTO.getTransferStatusDesc() + "\n" + "Amount:         " + "$" + transferDetailDTO.getAmount());
            }
        }
        if (tranferId == 0) {
            System.out.println("Invaild Transfer Id. \n");
        }

        // TODO Auto-generated method stub

    }

    private void viewPendingRequests() {
        // TODO Auto-generated method stub

    }

    private TransferDTO sendBucks() {
        boolean isDone = true;
        TransferDTO transfers = new TransferDTO();
        // TODO Auto-generated method stub
        List<User> userList = userService.getAllUsers();
        int toUserId = 1;
        System.out.println("************************");
        System.out.printf("%-15s %-10s\n", "User ID", "Name");
        System.out.println("************************");
        for (int i = 0; i < userList.size(); i++) {
            System.out.printf(" %-15s %-10s\n", userList.get(i).getId(), userList.get(i).getUsername());
        }
        while (isDone) {
            int choice = console.getUserInputInteger("Enter ID of user you are sending to: (-0- to cancel) \n");
            for (int i = 0; i < userList.size(); i++) {
                if (choice == userList.get(i).getId() && choice != currentUser.getUser().getId()) {
                    toUserId = choice;
                    int moneyToSend = console.getUserInputInteger("Please enter amount to transfer: \n");
                    transfers.setUserFrom(currentUser.getUser().getId());
                    transfers.setUserTo(choice);
                    transfers.setAmount(new BigDecimal(Integer.parseInt(String.valueOf(moneyToSend))));
                    isDone = false;
                }
            }
            if (choice == currentUser.getUser().getId()) {
                System.out.println("\n****** Please choose a user other than yourself! ******\n");
                isDone = true;
            }
            if (choice == 0) {
                isDone = false;
            }
            if (toUserId == 1 && choice != 0 && choice != currentUser.getUser().getId()) {
                System.out.println("\n****** Invalid ID, please enter valid ID. ****** \n");
                isDone = true;
            }
        }
        return transfers;
    }

    private void requestBucks() {
        // TODO Auto-generated method stub

    }

    private void exitProgram() {
        System.exit(0);
    }

    private void registerAndLogin() {
        while (!isAuthenticated()) {
            String choice = (String) console.getChoiceFromOptions(LOGIN_MENU_OPTIONS);
            if (LOGIN_MENU_OPTION_LOGIN.equals(choice)) {
                login();
            } else if (LOGIN_MENU_OPTION_REGISTER.equals(choice)) {
                register();
            } else {
                // the only other option on the login menu is to exit
                exitProgram();
            }
        }
    }

    private boolean isAuthenticated() {
        return currentUser != null;
    }

    private void register() {
        System.out.println("Please register a new user account");
        boolean isRegistered = false;
        while (!isRegistered) //will keep looping until user is registered
        {
            UserCredentials credentials = collectUserCredentials();
            try {
                authenticationService.register(credentials);
                isRegistered = true;
                System.out.println("Registration successful. You can now login.");
            } catch (AuthenticationServiceException e) {
                System.out.println("REGISTRATION ERROR: " + e.getMessage());
                System.out.println("Please attempt to register again.");
            }
        }
    }

    private void login() {
        System.out.println("Please log in");
        currentUser = null;
        while (currentUser == null) //will keep looping until user is logged in
        {
            UserCredentials credentials = collectUserCredentials();
            try {
                currentUser = authenticationService.login(credentials);
                accountService.setAuthToken(currentUser.getToken());
                userService.setAuthToken(currentUser.getToken());
                transferService.setAuthToken(currentUser.getToken());
            } catch (AuthenticationServiceException e) {
                System.out.println("LOGIN ERROR: " + e.getMessage());
                System.out.println("Please attempt to login again.");
            }
        }
    }

    private UserCredentials collectUserCredentials() {
        String username = console.getUserInput("Username");
        String password = console.getUserInput("Password");
        return new UserCredentials(username, password);
    }
}
