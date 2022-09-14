import java.util.ArrayList;
import java.util.Scanner;

/**
 * -----Description-----
 * Running a cinema theatre is no easy business. To help our friends, let's add statistics to your program.
 * The stats will show the current income, total income, the number of available seats, and the percentage of occupancy.
 *
 * In addition, our friends asked you to take care of a small inconvenience: it's not good when a user can buy a
 * ticket that has already been purchased by another user. Let's fix this!
 *
 *
 * -----Objectives-----
 * Now your menu should look like this:
 *
 * 1. Show the seats
 * 2. Buy a ticket
 * 3. Statistics
 * 0. Exit
 *
 * When the item Statistics is chosen, your program should print the following information:
 *
 *     The number of purchased tickets;
 *     The number of purchased tickets represented as a percentage. Percentages should be rounded to 2 decimal places;
 *     Current income;
 *     The total income that shows how much money the theatre will get if all the tickets are sold.
 *
 * The rest of the menu items should work the same way as before, except the item Buy a ticket shouldn't allow a user
 * to buy a ticket that has already been purchased.
 *
 * If a user chooses an already taken seat, print That ticket has already been purchased! and ask them to enter
 * different seat coordinates until they pick an available seat. Of course, you shouldn't allow coordinates that are
 * out of bounds. If this happens, print Wrong input! and ask to enter different seat coordinates until the user picks
 * an available seat.
 *
 * -----Examples-----
 * The greater-than symbol followed by a space (> ) represents the user input. Note that it's not part of the input.
 *
 * Enter the number of rows:
 * > 6
 * Enter the number of seats in each row:
 * > 6
 *
 * 1. Show the seats
 * 2. Buy a ticket
 * 3. Statistics
 * 0. Exit
 * > 3
 *
 * Number of purchased tickets: 0
 * Percentage: 0.00%
 * Current income: $0
 * Total income: $360
 *
 * 1. Show the seats
 * 2. Buy a ticket
 * 3. Statistics
 * 0. Exit
 * > 2
 *
 * Enter a row number:
 * > 1
 * Enter a seat number in that row:
 * > 1
 *
 * Ticket price: $10
 *
 * 1. Show the seats
 * 2. Buy a ticket
 * 3. Statistics
 * 0. Exit
 * > 3
 *
 * Number of purchased tickets: 1
 * Percentage: 2.78%
 * Current income: $10
 * Total income: $360
 *
 * 1. Show the seats
 * 2. Buy a ticket
 * 3. Statistics
 * 0. Exit
 * > 2
 *
 * Enter a row number:
 * > 1
 * Enter a seat number in that row:
 * > 1
 *
 * That ticket has already been purchased!
 *
 * Enter a row number:
 * > 10
 * Enter a seat number in that row:
 * > 20
 *
 * Wrong input!
 *
 * Enter a row number:
 * > 4
 * Enter a seat number in that row:
 * > 4
 *
 * Ticket price: $10
 *
 * 1. Show the seats
 * 2. Buy a ticket
 * 3. Statistics
 * 0. Exit
 * > 1
 *
 * Cinema:
 *   1 2 3 4 5 6
 * 1 B S S S S S
 * 2 S S S S S S
 * 3 S S S S S S
 * 4 S S S B S S
 * 5 S S S S S S
 * 6 S S S S S S
 *
 * 1. Show the seats
 * 2. Buy a ticket
 * 3. Statistics
 * 0. Exit
 * > 3
 *
 * Number of purchased tickets: 2
 * Percentage: 5.56%
 * Current income: $20
 * Total income: $360
 *
 * 1. Show the seats
 * 2. Buy a ticket
 * 3. Statistics
 * 0. Exit
 * > 0
 */
public class Cinema_Room_Manager {

    public static Scanner scanner = new Scanner(System.in);
    public static int rows;
    public static int row;
    public static int option;
    public static int seats;
    public static int seat;
    public static int purchasedTickets = 0;
    public static double percent = 0.00;
    public static int currentIncome = 0;
    public static int totalIncome = 0;
    public static boolean duplicate = true;
    public static ArrayList<ArrayList<String>> blueprint = new ArrayList<>();


    public static void main(String[] args) {

        System.out.println("Enter the number of rows:");
        rows = scanner.nextInt();

        System.out.println("Enter the number of seats in each row:");
        seats = scanner.nextInt();
        calTotalIncome();
        prepareBlueprint();
        menu();
    }

    public static void menu() {

        System.out.println("\n1. Show the seats\n" +
                "2. Buy a ticket\n" +
                "3. Statistics\n" +
                "0. Exit");
        option = scanner.nextInt();

        switch (option) {
            case 1:
                print();
                menu();
            case 2:
                while (duplicate) {
                    System.out.println("\nEnter a row number:");
                    row = scanner.nextInt();

                    System.out.println("Enter a seat number in that row:");
                    seat = scanner.nextInt();

                    updateBlueprint();
                }
                duplicate = true;
                System.out.println("\nTicket price: $" + price());
                menu();
            case 3:
                printStatistics();
                menu();
            case 0:
                return;
        }
    }

    public static void updateBlueprint() {
        if (row > rows || row < 0 || seat > seats || seat < 0) {
            System.out.println("Wrong input!");
        }
        else if (blueprint.get(row).get(seat) != "B") {
            blueprint.get(row).set(seat, "B");
            purchasedTickets++;
            duplicate = false;
        } else if(blueprint.get(row).get(seat) == "B") {
            System.out.println("That ticket has already been purchased!");
        }
    }

    public static void prepareBlueprint() {
        ArrayList<String> rowZero = new ArrayList<>();
        rowZero.add(0, " ");
        for (int i = 1; i <= seats; i++) {
            rowZero.add(i, String.valueOf(i));
        }

        blueprint.add(0, rowZero);
        for (int i = 1; i <= rows; i++) {
            blueprint.add(i, prepareRow(i));
        }
    }

    public static ArrayList<String> prepareRow(int rowNumber) {
        ArrayList<String> rowAsArrayList = new ArrayList<>();
        rowAsArrayList.add(0, String.valueOf(rowNumber));
        for (int i = 1; i <= seats; i++) {
            rowAsArrayList.add(i, "S");
        }
        return rowAsArrayList;
    }

    public static void print() {
        StringBuilder stringBuilder = new StringBuilder();
        for (ArrayList<String> row : blueprint) {
            for (int i = 0; i < row.size(); i++) {
                stringBuilder.append(row.get(i));
                if (i < row.size() - 1) {
                    stringBuilder.append(" ");
                }
            }
            stringBuilder.append("\n");
        }
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        System.out.println("\nCinema:\n" + stringBuilder);
    }

    public static int price() {
        if (rows * seats <= 60 || row <= rows / 2) {
            currentIncome = currentIncome + 10;
            return 10;
        }
        currentIncome = currentIncome + 8;
        return 8;
    }

    public static void calTotalIncome() {
        if (rows * seats <= 60) {
            totalIncome = rows * seats * 10;
        } else {
            int backHalf = (rows / 2) + (rows % 2);
            int frontHalf = rows - backHalf;
            totalIncome = (frontHalf * seats * 10) + (backHalf * seats * 8);
        }
    }

    public static void printStatistics() {
        if (rows * seats == 0) {
            percent = percent;
        } else  {
            percent = (purchasedTickets * 1.0) / (rows * seats);
        }
        String percentString = String.format("%.2f", percent * 100);

        System.out.println("\nNumber of purchased tickets: " + purchasedTickets
                + "\nPercentage: " + percentString + "%"
                + "\nCurrent income: $" + currentIncome
                + "\nTotal income: $" + totalIncome
        );
    }
}
