package com.company;

import java.util.Scanner;
import java.util.Arrays;

import static java.lang.System.out;

public class Main {

    public static void main(String[] args) {
        Scanner userInput = new Scanner(System.in);
        Machine coffeeMachine = new Machine();

        while (true) {
            out.println("Write action (buy, fill, take, remaining, exit): ");
            String action = userInput.next();
            if (action.equals("exit")) {
                break;
            } else {
                doAction(action, coffeeMachine);
            }
        }


    }

    public static void doAction(String action, Machine coffeeMachine) {
        switch (action) {
            case "buy":
                coffeeMachine.buy();
                break;
            case "fill":
                coffeeMachine.fillMachine();
                break;
            case "take":
                coffeeMachine.take();
                break;
            case "remaining":
                coffeeMachine.printState();
                break;
            default:
                out.println("Not a valid action");
                break;
        }
    }


    public static int maxCupsPossible(int totalMlWater, int totalMlMilk, int totalGramsBeans) {
        int[] ingredients = new int[3];
        int neededMlOfWater = 200;
        int neededMlOfMilk = 50;
        int neededGramsOfCoffee = 15;
        ingredients[0] = totalMlWater / neededMlOfWater;
        ingredients[1] = totalMlMilk / neededMlOfMilk;
        ingredients[2] = totalGramsBeans / neededGramsOfCoffee;
        Arrays.sort(ingredients);

        return ingredients[0];
    }

    public static void handleCoffeeMaking(int numberOfCups, int maxCups) {
        String successMessage = "Yes, I can make that amount of coffee %s";
        String failureMessage = "No, I can make only %s cup(s) of coffee";
        if (numberOfCups == maxCups) {
            out.printf(successMessage, "");
        } else if (numberOfCups < maxCups) {
            String extraCups = " (and even " + (maxCups - numberOfCups) + " more than that)";
            out.printf(successMessage, extraCups);
        } else {
            out.printf(failureMessage, maxCups);
        }

    }

}

class Machine {
    int totalWater = 400;
    int totalMilk = 540;
    int totalBeans = 120;
    int totalDisposableCups = 9;
    int totalMoney = 550;

    public void fillMachine() {
        Scanner userInput = new Scanner(System.in);
        out.println("Write how many ml of water do you want to add:");
        totalWater += userInput.nextInt();
        out.println("Write how many ml of milk do you want to add:");
        totalMilk += userInput.nextInt();
        out.println("Write how many grams of coffee beans do you want to add:");
        totalBeans += userInput.nextInt();
        out.println("Write how many disposable cups of coffee do you want to add:");
        totalDisposableCups += userInput.nextInt();
    }

    public void printState() {
        out.printf("The coffee machine has:\n" +
                "%s of water\n" +
                "%s of milk\n" +
                "%s of coffee beans\n" +
                "%s of disposable cups\n" +
                "$%s of money\n", totalWater, totalMilk, totalBeans, totalDisposableCups, totalMoney
        );
    }

    public void buy() {
        out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu");
        Scanner userInput = new Scanner(System.in);
        String coffeeType = userInput.nextLine();
        switch (coffeeType) {
            case "1":
                makeDrink(250, 0, 16, 4);
                break;
            case "2":
                makeDrink(350, 75, 20, 7);
                break;
            case "3":
                makeDrink(200, 100, 12, 6);
                break;
            case "back":
                break;
        }
    }

    public void makeDrink(int waterNeeded, int milkNeeded, int beansNeeded, int drinkCost) {
        String failureMessage = "Sorry, not enough %s!\n";
        if (totalWater - waterNeeded < 0) {
            out.printf(failureMessage, "water");
        } else if (totalMilk - milkNeeded < 0) {
            out.printf(failureMessage, "milk");
        } else if (totalBeans - beansNeeded < 0) {
            out.printf(failureMessage, "beans");
        } else if (totalDisposableCups - 1 < 0) {
            out.printf(failureMessage, "disposable cups");

        } else {
            out.println("I have enough resources, making you a coffee!\n");
            totalWater -= waterNeeded;
            totalMilk -= milkNeeded;
            totalBeans -= beansNeeded;
            totalDisposableCups -= 1;
            totalMoney += drinkCost;
        }

    }

    public void take() {
        out.printf("I gave you $%s\n", totalMoney);
        totalMoney = 0;

    }
}
