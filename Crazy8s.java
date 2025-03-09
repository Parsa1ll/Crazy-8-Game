// File: Crazy8s.java
// Date: May 28, 2023
// Author: Parsa Ahmadnezhad
// Description: An interface that lets the user play the game of Crazy 8's

import java.util.*; // Imports the whole utility package into the code
import java.io.*;   // Imports the Input/Output package into the code

public class Crazy8s { // Defines the main class 
  static Scanner scan = new Scanner(System.in);        // Declares a global console scanner variable and assigns the data type Scanner to it
  final static int MAXDRAW = 3;                        // Initializes the constant variable and assigns the number to it 
  public static void main (String[] args) throws Exception {          // Defines the main method and adds exceptions because file reding/writing will be occuring in the program
    String name, suit, topCard, rulePrint, winner, printResult, temp; // Declares varible data type String and reserves space in memory
    int length, userScore = 0, botScore = 0, rounds = 0, counter = 0; // Declares varible data type integer, reserves space in memory, and Initializes the variables by assigning 0 to them
    final int NUMOFCARDS = 7;                    // Initializes the constant integer variable and assigns number to it
    final int DECKSIZE = 52;                     // Initializes the constant integer variable and assigns number to it
    boolean inputError = false;                  // Initializes the boolean variable and assigns false to it
    String[] deck = new String[DECKSIZE];        // Initializes String array variable by assigning a new string array object to it with the length of the constant DECKSIZE => 52
    String[] drawingPile = new String[DECKSIZE]; // Initializes String array variable by assigning a new string array object to it with the length of the constant DECKSIZE => 52
    String[] userCards = new String[DECKSIZE];   // Initializes String array variable by assigning a new string array object to it with the length of the constant DECKSIZE => 52
    String[] botCards = new String[DECKSIZE];    // Initializes String array variable by assigning a new string array object to it with the length of the constant DECKSIZE => 52
    String[] discardPile = new String[DECKSIZE]; // Initializes String array variable by assigning a new string array object to it with the length of the constant DECKSIZE => 52
    String[] crazy8s = {" ________  ________  ________  _____  __    __     ________", "|   _____||   __   ||   __   ||___  ||  \\  /  |   |   __   |", "|  |      |  |__|  ||  |__|  |   / /  \\  \\/  /    |  |__|  |", "|  |      |   __  _||   __   |  / /    \\    /     |   __   |",  "|  |_____ |  |  \\ \\ |  |  |  | / /__    |  |      |  |__|  |", "|________||__|   \\_\\|__|  |__||_____|   |__|      |________|"}; // Initializes String array variable by assigning a new string array object to it with specified elements
    File cardsFile = new File("card.txt");    // Initializes File variable by assigning file object "card.txt" to it
    File resultFile = new File("result.txt"); // Initializes File variable by assigning file object "result.txt" to it
    Scanner fileScanner =  new Scanner(cardsFile);     // Initializes the local Scanner variable to read from the "card.txt" file object
    PrintWriter output = new PrintWriter(new FileWriter(resultFile, true)); // Initializes PrintWriter variable to append to the "result.txt" file object

    System.out.println("WELCOME TO CRAZY 8's!"); // Prints out the message in the console 
    System.out.println("PLEASE MAXIMIZE THE SIZE OF YOUR CONSOLE FOR THE BEST GAME EXPERIENCE!"); // Prints out the message in the console 
    System.out.println(); // Prints out the message (empty line) in the console 
    // A for loop that iterate through the crazy8s array to print each element on a new line
    for (int i = 0; i < crazy8s.length; i++){  
      System.out.println(crazy8s[i]);
    }
    System.out.println(); // Prints out the message (empty line) in the console 

    System.out.print("Please enter your name: "); // Prints out the prompt in the console 
    name = scan.nextLine(); // Scans the user input and assigns it to variable name
    System.out.println();   // Prints out the message (empty line) in the console 

    // A block of welcoming messages to be printed in the console
    System.out.println("Hello " + name + "! Thank you for choosing our game of Crazy 8's! You will be prompted to answer a few questions and then the game will be started.");
    System.out.println();
    System.out.println("Best of Luck!");
    System.out.println();
    
    // Do-while loop that will act as validation check for 'y' and 'n'
    do {
      System.out.print("Would you like to view the rules and guidlines of the game? (enter y for yes, enter n for no): "); // Prints the prompt in the console
      rulePrint = scan.nextLine();                        // Scans the user input and assigns it to variable
      if (rulePrint.equalsIgnoreCase("y")){ // If user input is 'y', runs the following commands
        printMenu();                                      // Calls the printMenu function
      }
      else if (! rulePrint.equalsIgnoreCase("n")){    // Else, checks if user input is not 'n', and then runs the following commands
          System.out.println("Invalid input. Please try again."); // Prints out the error message in the console
          System.out.println();                                     // Prints out the message (empty line) in the console
      }
    } while (!(rulePrint.equalsIgnoreCase("y") || rulePrint.equalsIgnoreCase("n"))); // Keep iterating until user enters 'y' or 'n'

    System.out.println(); // Prints out the message (empty line) in the console

    // Do-while loop that will act as a validation check for positive integer
    do {
      System.out.print("Enter the number of rounds you would like to play against the computer (positive integer): "); // Prints the prompt in the console 
      temp = scan.nextLine();              // Assigns the user input to the variable 
      try {                                // [1] The opening of the try-catch block that will attempt to run the commands
          rounds = Integer.parseInt(temp); // [2] Assigns the numeric value of the user input to the integer variable
          inputError = false;              // Assigns false to the variable 

          // Checks if user input is not postive and then prints out the error message in the console and assigns true to the variable inputError
          if (rounds <= 0){
              System.out.println("Invalid input. Please ensure you are not entering zero nor a negative number.");
              System.out.println();
              inputError = true;
          }
      } catch (NumberFormatException e){ // [3] If user input is not an integer, it catches the error and prints out the error message in the console and assigns true to the variable
          System.out.println("Invalid input. Please enter a positive integer.");
          System.out.println();
          inputError = true;
      }
    } while (inputError); // Keeps iterating until user enters a positive integer

    System.out.println(); // Prints out the message (empty line) in the console

    // Iterates through the file and assigns each line to an element in the deck array
    while (fileScanner.hasNextLine()){
      deck[counter] = fileScanner.nextLine();
      counter++;
    }
    fileScanner.close(); // Closes the fileScanner scanner object

    output.println("** NEW GAME **"); // Prints out the message in the 'result.txt' file 
    output.println();                   // Prints out the message (empty line) in the 'result.txt' file

    for (int a = 1; a <= rounds; a++){ // For loop that will iterate the same number of times as the user wants to play as rounds

      // Iterates through deck array and assigns the following information to each element of the following arrays
      for (int i = 0; i < deck.length; i++){
        discardPile[i] = null; 
        drawingPile[i] = deck[i];
        userCards[i] = null;
        botCards[i] = null;
      }

      System.out.println("Round " + a + " Begins! " + name + "'s Turn."); // Prints out the message in the console 

      shuffle(drawingPile); // Calls the shuffle function to shuffle the elements in the array

      // Distributes 7 cards to each of the players' hands
      for (int j = 0; j < NUMOFCARDS; j++){
        switchCards(drawingPile, userCards);
        switchCards(drawingPile, botCards); 
      }
      
      // Iterates through the drawing pile and finds the last card that is not 8, and moves it to the discrd pile to be the top card
      length = countCards(drawingPile);
      for (int j = length - 1; j >= 0; j--){
        if (! drawingPile[j].substring(0, drawingPile[j].length() - 1).equals("8")){
          switchCards(drawingPile, drawingPile[j], discardPile);
          break;
        }
      }

      // Calls the getTopCard function and assigns it the suit to the variable
      suit = getTopCard(discardPile).substring(getTopCard(discardPile).length()-1);

      // Prints out the message in the console
      System.out.println("-----------------------------------------------------------------");

      // Infinite while loop that will run until one of the users win the round
      while(true){

        // Checks if there are less than three cards in the drawing pile, and if so, it moves all the cards from discard pile to the drawing pile (except the top card) and it shuffles the drawing pile
        if (countCards(drawingPile) < 3){
          topCard = getTopCard(discardPile);
          for (int i = 0; i < countCards(discardPile) ; i++){
            switchCards(discardPile, drawingPile);
          }
          switchCards(drawingPile, topCard, discardPile);
          shuffle(drawingPile);
        }

        // Calls the userPlay function and gives the parameters to let the user play their turn, and at the end, assigns the returned value (their top card suit) to the variable
        suit = userPlay(userCards, discardPile, drawingPile, suit, name);

        // Prints out the message in the console
        System.out.println("-----------------------------------------------------------------");

        // Checks if bot has any cards left in hand, and if so, it ends the round and assigns the bot to the variable
        if (countCards(userCards) == 0){
          botScore += getScore(botCards);
          winner = name;
          break;
        }

        // Checks if there are less than three cards in the drawing pile, and if so, it moves all the cards from discard pile to the drawing pile (except the top card) and it shuffles the drawing pile
        if (countCards(drawingPile) < 3){
          topCard = getTopCard(discardPile);
          for (int i = 0; i < countCards(discardPile) ; i++){
            switchCards(discardPile, drawingPile);
          }
          switchCards(drawingPile, topCard, discardPile);
          shuffle(drawingPile);
        }

        // Calls the botPlay function and gives the parameters to let the user bot its turn, and at the end, assigns the returned value (its top card suit) to the variable
        suit = botPlay(botCards, discardPile, drawingPile, suit);

        // Prints out the message in the console
        System.out.println("-----------------------------------------------------------------");

        // Checks if bot has any cards left in hand, and if so, it ends the round and assigns the bot to the variable
        if (countCards(botCards) == 0){
          userScore += getScore(userCards);
          winner = "Bot";
          break;
        }
      }

      // Prints a set messages in the console
      System.out.println("ROUND " + a + " OF THE GAME HAS FINISHED!");
      System.out.println("  Winner of this round: " + winner);
      System.out.println("  User Score Up to This Round: " + userScore);
      System.out.println("  Bot Score Up to This Round: " + botScore);
      System.out.println();

      // Prints a set of messages in the 'result.txt' file
      output.println("ROUND " + a + " OF THE GAME HAS FINISHED!");
      output.println("  Winner of this round: " + winner);
      output.println("  User Score Up to This Round: " + userScore);
      output.println("  Bot Score Up to This Round: " + botScore);
      output.println();
    }

    // Prints the message
    System.out.print("End of the game. ");

    // The block checks to find the winner and then assigns it to the variable and prints out the winner in the console
    if (userScore < botScore){
      winner = name;
      System.out.println("Congratulations " + name + "! You won the game!");
    }
    else if (botScore < userScore){
      winner = "Bot";
      System.out.println("Sorry " + name + "! Bot won the game. Better luck next time!");
    }
    else {
      winner = "Tie Game";
      System.out.println("Good game " + name + "! The game is a tie!");
    }

    // Prints a set of messages in the console to inform the user about the end of the game
    System.out.println();
    System.out.println("GAME HAS FINISHED!");
    System.out.println("  User Score: " + userScore);
    System.out.println("  Bot Score: " + botScore);
    System.out.println("  Winner of the game: " + winner);
    System.out.println();

    // Prints the message
    System.out.println("Round scores and results are updated to the file: 'result.txt'");
    System.out.println();

    // Prints a set of messages in the 'result.txt' file to inform the user about the end of the game
    output.println("GAME HAS FINISHED!");
    output.println("  User Score: " + userScore);
    output.println("  Bot Score: " + botScore);
    output.println("  Winner of the game: " + winner);
    output.println("-----------------------------------------------------------------");

    output.close(); // Closes the output PrintWriter object

    // Validation check for 'y' and 'n' to print the game log for the user
    do {
      System.out.print("Would you like to read the game history? (enter y for yes, enter n for no): ");
      printResult = scan.nextLine();
      // If user input is equal to 'y', scans the 'result.txt' file and prints all the information in the file to the console
      if (printResult.equalsIgnoreCase("y")){
        System.out.println("Game History:");
        fileScanner = new Scanner(resultFile);
        while (fileScanner.hasNextLine()){
          System.out.println(fileScanner.nextLine());
        }
        System.out.println();
        fileScanner.close(); // Closes the fileScanner scanner object 
      }
      // Else, checks if the user input is not 'n', then prints the error message
      else if (! printResult.equalsIgnoreCase("n")){
          System.out.println("Invalid input. Please try again.");
          System.out.println();
      }
    } while (!(printResult.equalsIgnoreCase("y") || printResult.equalsIgnoreCase("n"))); // Keeps iterating until user enters 'n' or 'y'

    // Thanks the user for playing
    System.out.println("Thank you for playing the Game of Crazy 8's! Have a fantastic day! ( ^ - ^ ) ");

    scan.close(); // Closes the global console scan scanner object
  }

  public static void printMenu (){ // Defines the procedure-type method called printMenu
    // Prints a series of messages to the console, as the guidlines of the game
    System.out.println();
    System.out.println("The Object of the Game:");
    System.out.println("  - In Crazy Eights, every player receives 7 cards in each round (the most common number of cards), and the objective of the game is to discard all of the cards as soon as possible before other players. After each round, each will player receives points based on their hands, and after all the rounds, the player with the least number of points wins.");
    System.out.println();
    System.out.println("Rules:");
    System.out.println("  - To play card a card, the card should match either the number (A, 1, 2, K…) or the suit (Clubs , Diamonds, Hearts , Spades) of the top card in the discard pile");
    System.out.println("  - Cards with the number 8 are considered wild cards and can be played anytime regardless of the number and the suit");
    System.out.println("    - The player can change the suit of the top card by playing an 8 card and calling a suit");
    System.out.println("  - If a player doesn't have a matching card, they will keep drawing a new card 3 times until they receive a playable card");
    System.out.println("  - If 3 draws are reached and no playable card is in hand, the turn will pass to the next player");
    System.out.println();
    System.out.println("After each round, the points will be distributed as follows:");
    System.out.println("  - 8 Cards: 50 Points");
    System.out.println("  - Jack, Queen, King: 10 Points");
    System.out.println("  - 2, ..., 10: Card Value Points");
    System.out.println("  - Ace: 1 Point");
    System.out.println();
    System.out.println("During the game, cards with any words in them will be represented by the first letter of the word:");
    System.out.println("  - Jack => J | Queen => Q | King => K | Ace => A | Spades => S | Hearts => H | Diamonds => D | Clubs => C");
  }

  public static void shuffle (String[] deck){ // Defines the procedure-type method called shuffle that has a String array formal parameter
    Random random = new Random();             // Declares a 'random' variable and assigns the data type Random to it 
    String temp;                              // Declares a String data type variable
    // A for loop that will run the same number of times as the number of cards on the array to shuffle the array using Fisher–Yates shuffle algorithm
    for (int i = countCards(deck) - 1; i > 0; i--){ // A loop that will start from the number of cards in the array and go back down until one
        int j = random.nextInt(i); // Picks a random number between 0 and i 
        temp = deck[i];    // Assigns the index i of the array to a temporary variable
        deck[i] = deck[j]; // Assigns the randomly picked index of the array to the index i of the array
        deck[j] = temp;    // Assigns the index i of the array to the randomly picked index of the array
    }
}

  public static void switchCards (String[] source, String[] outcome){ // Defines the procedure-type method called switchCards that has two String array formal parameters
    String temp = source[countCards(source) - 1]; // Initializes the String variable and assigns the last non-null element of the source array to a temporary variable
    outcome[countCards(outcome)] = temp;          // Assigns the temporary variable one next to the last element of the outcome array
    source[countCards(source) - 1] = null;        // Assigns null value to the last element of the source array
  }

  public static void switchCards (String[] source, String card, String[] outcome){ // Defines the procedure-type method called switchCards (second version) that has two String array and one String formal parameters
    String temp = null;   // Initializes the temporary String varible by assigning null to it 
    boolean move = false; // Initializes the boolean varible by assigning false to it
    int length = countCards(source); // Initializes the integer varible by assigning the number of cards in the source array to it
    // A for loop that will iterate through all the nun-null elements of source array to find the card (parameter) and assign null to it
    // And when cards is found an null is assigned to the element, move all the other non-null elements one index closer to the beginning
    for (int i = 0; i < length; i++){
      if (move == true){                // Checks if move is true, and if so, it switches the value of the element at index i with one element before it
        temp = source[i];
        source[i] = source[i - 1];
        source[i - 1] = temp;
      }
      else if (source[i].equals(card)){ // ELse, checks if the elements at index i is equal to the card (parameter), and if so, makes that element a null value, and assigns true to boolean variable move
        source[i] = null;
        move = true;
      }
    }
    outcome[countCards(outcome)] = card; // Assigns the card value one next to the last element of the outcome array
  }
  
  public static String getTopCard (String[] deck){ // Defines the function-type method called getTopCard that has a String return data type and has a String array formal parameter
    String topCard = deck[countCards(deck) - 1];   // Initializes the String variable and assigns the last non-null element of the String array, found by calling the countCards function and minus 1, to the variable
    return topCard;                                // Returns the information stored in the variable
  }

  public static void printCards (String[] cards){  // Defines the procedure-type method called printCards that has a String array formal parameter
    String[] graphic = {"", "", "", "", "  "};     // Initializes the String array variable and specifies each element of the array
    String num, suit; // Declares varible data type String and reserves space in memory
    // A for loop that will iterate through the cards array (parameter) and will print out the information in card format
    for (int i = 0; i < countCards(cards); i++){
      num = cards[i].substring(0, cards[i].length()-1); // Assigns the number part of the index i at the cards array to the variable
      suit = cards[i].substring(cards[i].length()-1);              // Assigns the letter part of the index i at the cards array to the variable
      // The block will use if & else statements to define the first 9 cards of the list and each line will be an index in the graphic array
      graphic[0] = graphic[0] + " _____ ";
      if (num.length() == 1){
        graphic[1] = graphic[1] + "|" + num + "    |";
      }
      else{
        graphic[1] = graphic[1] + "|" + num + "   |";
      }
      graphic[2] = graphic[2] + "|  " + suit + "  |";
      if (num.length() == 1){
        graphic[3] = graphic[3] + "|____" + num + "|";
      }
      else{
        graphic[3] = graphic[3] + "|___" + num + "|";
      }
      // The block will be in charge of adding the index numbers of the card based on the number of digits of the index number
      // Either one digit => 1-9  | two digit => 10-52 
      if (i+1 <= 9){
        graphic[4] = graphic[4] + "(" + Integer.toString(i+1) + ")    ";
      }
      else {
        graphic[4] = graphic[4] + "(" + Integer.toString(i+1) + ")   ";
      }
      // The block will be in charge of printing the cards 9 by 9 and after printing 9 cards, it resets the array to get ready for the next 9 cards
      // And it also checks if it's the last iteration of the loop, in which it will print the cards and end the proccess
      if ((i+1) % 9 == 0 || i == countCards(cards) - 1){
        // Goes through the array to print each element in a new line
        for (int j = 0; j < graphic.length; j++){
          System.out.println(graphic[j]);
        }
        // Resets the elements of the graphic array
        graphic[0] = "";
        graphic[1] = "";
        graphic[2] = "";
        graphic[3] = "";
        graphic[4] = " ";

        System.out.println(); // Prints the empty line in the console
      }
    }
  }

  public static void printTopCard (String card, String suitCalled){ // Defines the procedure-type method called printCard that has two String formal parameters
    String[] graphic = {"", "", "", ""};                 // Initializes the String array variable and specifies each element of the array
    String num, suit;                                    // Declares varible data type String and reserves space in memory
    num = card.substring(0, card.length()-1); // Assigns the number part of the String card to the variable
    suit = card.substring(card.length()-1);              // Assigns the letter part of the String card to the variable
    // The block will use if & else statements to define the card and each line will be an index in the graphic array
    graphic[0] = graphic[0] + " _____ ";
    if (num.length() == 1){
      graphic[1] = "|" + num + "    |";
    }
    else{
        graphic[1] = "|" + num + "   |";
    }
    graphic[2] = "|  " + suit + "  | Base Suit: " + suitCalled;
    if (num.length() == 1){
      graphic[3] = "|____" + num + "|";
    }
    else{
      graphic[3] = "|___" + num + "|";
    }
    // For loop that will iterate through graphic array and print each element on a new line
    for (int i = 0; i < graphic.length; i++){
      System.out.println(graphic[i]);
    }
  }
  
  public static int countCards (String[] cards){ // Defines function-type method called countCards that has an integer return data type and a String array formal parameter
    int counter = 0;                             // Initializes the integer variable and assigns 0 to it
    // For loop that will iterate through the cards array to count the number of non-null elements
    for (int i = 0; i < cards.length; i++){
     if(cards[i] == null){
        break;
      }
      counter++;
    }
    return counter; // Return the information stored in the counter variable 
  }
  
  public static String userPlay (String[] hand, String[] discardPile, String[] drawingPile, String topCardSuit, String name){ // Defines the function-type method called botPlay that has a String return type, and String and String array parameters
    String drawPLay, choiceSuit, choiceNum = "", topCard = getTopCard(discardPile), topCardNum = topCard.substring(0, topCard.length()-1); // Initializes the String variables and assigns information to them
    boolean inputError, crazy8 = false, cardCheck = false; // Initializes the boolean variables and assigns false to two of them
    String[] choice;  // Declares a new String array variable and reserves space in memory
    int[] choicePlay; // Declares a new integer array variable and reserves space in memory
    
    // For loop that will iterate four times to let the player draw cards and play cards
    for (int a = MAXDRAW; a >= 0; a--){
      
      // Calls the print card functions and prints the following messages in the console
      System.out.println(name + "'s Cards:");
      printCards(hand);
      System.out.println("Top Card:");
      printTopCard(topCard, topCardSuit);
      System.out.println();

      for (int i = 0; i < countCards(hand); i++){ // Defines the function-type method called userPlay that has a String return type, and String and String array parameters
        // Checks if there is any playable card in hand (either number is same as top card, or suit is same as top card, or number is 8)
        // If so, assigns true to the variable and breaks the loop
        if ("8".equals(hand[i].substring(0, hand[i].length()-1)) || (topCardNum.equals(hand[i].substring(0, hand[i].length()-1)) || topCardSuit.equals(hand[i].substring(hand[i].length()-1)))){
          cardCheck = true;
          break;
        }
      }

      // If variable is false and and there is no playable card, it asks the user to approve they card drawing
      if (cardCheck == false){
        // If it is the last iteration, prints the message in the console and breaks the loop
        if (a == 0){
          System.out.println("Drawing cards reached maximum 3 times. Turn will pass to Bot!");
          System.out.println();
          break;
        }

        // Prints out the message in the console
        System.out.println("Playable card not in hand! You must draw card.");
        System.out.println();

        // Validation check for "y" to approve their card drawing
        do {
          System.out.print("Would you like to draw a card? (" + a + " tries left) (enter y for yes): ");
          drawPLay = scan.nextLine();
          // Prints out the error message if user inputs anything other than 'y'
          if (!(drawPLay.equalsIgnoreCase("y"))){
              System.out.println("Invalid input. Please try again.");
              System.out.println();
          }
        } while (!(drawPLay.equalsIgnoreCase("y"))); // Keep iterating until user enters 'y'
        System.out.println(); 
      }
      // If playble card in hand, runs the commands
      else {
        // If it is the last iteration, the user will be forced to play a card
        // Therefore prints the message to the console and assigns 'n' to the variable
        if (a == 0){
          System.out.println("Playable card in hand and maximum draws reached! You must play a card.");
          System.out.println();
          drawPLay = "n";
        }
        // If it is not the last iteration, it gives the user option to draw a card
        // Therefore prints the message to the console
        else {
          System.out.println("Playable card in hand! You can draw a card or play a card.");
          System.out.println();

          // Validation check for 'y' and 'n'
          do {
            System.out.print("Would you like to draw a card? (" + a + " tries left) (enter y for yes, enter n for no): ");
            drawPLay = scan.nextLine();
            // Checks if user input is neither 'y' nor 'n' and prints the error message to the console
            if (!(drawPLay.equalsIgnoreCase("y") || drawPLay.equalsIgnoreCase("n"))){
                System.out.println("Invalid input. Please try again.");
                System.out.println();
            }
          } while (!(drawPLay.equalsIgnoreCase("y") || drawPLay.equalsIgnoreCase("n"))); // Keeps iterating until user enters 'y' or 'n'
          
          System.out.println();
        }
      }
      // Checks if user input is 'y', and if so, it draws a card for the user
      if (drawPLay.equalsIgnoreCase("y")){
        switchCards(drawingPile, hand);
        System.out.println("New card has been drawn!");
        System.out.println();
      }
      // Checks if user input is 'n', and if so, it lets the user play a card, and then breaks out of the loop
      else if (drawPLay.equalsIgnoreCase("n")){
        
        // Validation check to ensure user enters valid input and the card(s) input by user is playable
        do{
          inputError = false;
          System.out.print("Enter the index(es) of the card(s) you would like to play seperated by a space (found under each card):  ");
          choice = scan.nextLine().split(" "); // [4] Splits the the user input by a space, and assigns it to the variable
          choicePlay = new int[choice.length];       // Assigns the integer array object to the varaible with same length as the user input array
          
          // Iterates through each element of the user input array to make sure user only has integer inputs
          for (int i = 0; i < choice.length; i++){
            // Attempts to cast the user String inputs to integers 
            // And catches the error and prints an error if user input is not and integer
            try {
              choicePlay[i] = Integer.parseInt(choice[i]);
            } catch (NumberFormatException e){
              System.out.println("Invalid input. Please ensure your input includes only integers and integers are separated by a space.");
              System.out.println();
              inputError = true;
              break; // Breaks out of the for loop
            }

            // Checks to ensure user input is inside the range of the card indexes
            // If not, prints an error in the console
            if (!(0 <= choicePlay[i] && choicePlay[i] <= countCards(hand))){
              System.out.println("Invalid input. Please ensure your input only includes the provided indexes.");
              System.out.println();
              inputError = true;
              break; // Breaks out of the for loop
            }

            // Assigns the number and the suit of the card to the variables 
            choiceNum = hand[choicePlay[i] - 1].substring(0, hand[choicePlay[i] - 1].length()-1);
            choiceSuit = hand[choicePlay[i] - 1].substring(hand[choicePlay[i] - 1].length()-1);

            // Checks the properties of the first card to be played
            if (i == 0){
              // If it is 8, assigns true to the variable
              if (choiceNum.equals("8")){
                crazy8 = true;
              }
              // Else, if neither suit not number of the card is the same as the top card
              // Prints out error to the console and assigns true to the error variable and breaks out of the for loop
              else if (!(choiceNum.equals(topCardNum) || choiceSuit.equals(topCardSuit))){
                System.out.println("Invalid input. PLease ensure you pick the cards that are playble.");
                System.out.println();
                inputError = true;
                break;
              }
            }
            // If the first card is valid, checks to ensure all the other cards share the same number as the first card
            else {
              // If neither suit not number of the card is the same as the first card
              // Prints out error to the console and assigns true to the error variable and breaks out of the for loop
              if (! (choiceNum.equals(hand[choicePlay[i - 1] - 1].substring(0, hand[choicePlay[i - 1] - 1].length()-1)) && choicePlay[i] != choicePlay[i-1])){
                System.out.println("Invalid input. Please ensure you do not pick a card twice, and if playing multiple, please pick diffrent cards and cards must share the same card number.");
                System.out.println();
                inputError = true;
                break;
              }
            }
          }
        } while(inputError); // Keeps iterating until inputError is false

        System.out.println();

        // For loop that will iterate throught the choicePlay array and plays the cards at the given indexes
        for (int i = 0; i < choicePlay.length; i++){
          switchCards(hand, hand[choicePlay[i] - 1], discardPile);
          // Goes through all the other indexes that are larger than and reduces them by 1, since one card is getting removed from the hand
          for (int j = i + 1; j < choicePlay.length; j++){
            if (choicePlay[j] > choicePlay[i]){
              choicePlay[j]--;
            }
          }
        }

        // If user played an 8 card, asks the user to call a suit
        if (crazy8 == true){
          System.out.println("CRAZY 8!");
          System.out.println();

          // Validation check that will iterate until user enters a valid suit
          do {
            System.out.print("Enter the first letter of the suit you would like to call (i.e H, D, S, C): ");
            topCardSuit = scan.nextLine().toUpperCase(); // [5] Turns the user input into upper case letters
            // If user input is a suit, breaks out of the infinite loop
            if (topCardSuit.equals("H") || topCardSuit.equals("D") || topCardSuit.equals("S") || topCardSuit.equals("C")){
                break;
            }
            // Prints the error message in the console
            System.out.println("Invalid input. Please try again.");
            System.out.println();
          } while (true); // Keeps iterating until break is used

          System.out.println();
        }
        // If the user didn't play an 8 card, picks the suit of the top card as the suit and assigns it to variable
        else {
          topCard = getTopCard(discardPile);
          topCardSuit = topCard.substring(topCard.length()-1);
        }

        break; // After playing the cards successfully, breaks out of the loop
      }
    }
    return topCardSuit; // Returns the information stored in the variable
  }
  
  public static String botPlay (String[] hand, String[] discardPile, String[] drawingPile, String topCardSuit){ // Defines the function-type method called botPlay that has a String return type, and String and String array parameters  
    String temp, topCard = getTopCard(discardPile), topCardNum = topCard.substring(0, topCard.length()-1), cardPick = ""; // Initilizes the String variables and assigns the information to them
    int firstCard = 0, lastCard = 0, suitCount = 0, mode = 0, cardCounter = 0, length; // Initilizes the integer variables and assigns the information to them
    String[] cardPickArray = new String[0]; // Initilizes the String array variable and assigns the String array object with length 0 to it

    // For loop that will iterate four times to let the player draw cards and play cards
    for (int a = MAXDRAW; a >= 0; a--){ 
      // The block will go through the hand and find the card that will allow for the maximum number of cards being played
      length = countCards(hand);
      for (int i = 0; i < length; i++){
        // If card is playble, then checks for the number of cards that can be played with it as well (cards that share the same card number)
        if ("8".equals(hand[i].substring(0, hand[i].length()-1)) || (topCardNum.equals(hand[i].substring(0, hand[i].length()-1)) || topCardSuit.equals(hand[i].substring(hand[i].length()-1)))){
          for (int j = 0; j < length; j++){
              if (hand[j].substring(0, hand[j].length()-1).equals(hand[i].substring(0, hand[i].length()-1))){
                cardPick += hand[j] + " ";
                mode++;
              }
          }
          // If the number of cards are higher than the previous one, assign the new information to the variables
          if (mode > cardCounter){
            cardCounter = mode;
            cardPickArray = cardPick.split(" ");
          }
          // Resets the following variables
          mode = 0;
          cardPick = "";
        }
      }
      // If any card was playble, then runs the commands and plays the cards
      if (cardPickArray.length > 0){ 
        // Iterates through the card picks to pick that card that shares the suit with top card, as the first to play
        // And if more than 2 cards, the suit that has more numbers in hand, as the last card
        for (int i = 0; i < cardPickArray.length; i++){
          if (cardPickArray[i].substring(cardPickArray[i].length() - 1).equals(topCardSuit)){
            firstCard = i;
          }
          else if (suitCount < countSuit(hand, cardPickArray[i])){
            lastCard = i;
            suitCount = countSuit(hand, cardPickArray[i]);
          }
        }
        // If more than 1 cards, switches one time
        // If more than 2 cards, seitches two times
        if (cardPickArray.length > 2){
          temp = cardPickArray[firstCard];
          cardPickArray[firstCard] = cardPickArray[0];
          cardPickArray[0] = temp;
        }
        if (cardPickArray.length > 1){
          temp = cardPickArray[lastCard];
          cardPickArray[lastCard] = cardPickArray[cardPickArray.length - 1];
          cardPickArray[cardPickArray.length - 1] = temp;
        }
        // Iterates through the array to play the cards
        for (int i = 0; i < cardPickArray.length; i++){
          switchCards(hand, cardPickArray[i], discardPile);
        }

        //  Checks if any 8 cards have been played
        if (cardPickArray[0].substring(0, cardPickArray[0].length() - 1).equals("8")){
          // Counts the total number of each suit in the hand and calls the one with highest number
          String[] suits = {"D", "H", "C", "S"};
          int[] suitCounter = {countSuit(hand, " D"), countSuit(hand, " H"), countSuit(hand, " C"), countSuit(hand, " S")};
          mode = 0;
          for (int i = 0; i < 4; i++){
            // If the number of suits is higher than the mode, assigns the information to the variables
            if (suitCounter[i] > mode){
              topCardSuit = suits[i];
              mode = suitCounter[i];
            }
          }
        }
        // Else, if no 8 cards are played, pick the suit of the top card as the suit and assign it to the variable
        else {
          topCard = getTopCard(discardPile);
          topCardSuit = topCard.substring(topCard.length() - 1);
        }
        System.out.println("Status: Bot played " + cardPickArray.length + " card(s)"); // Prints the message in the console
        break;
      }
      // If the previous check failed, it means there are no playable cards
      // If the iteration is not the last iteration, it draws a card for bot, and prints the message in the console
      else if (a > 0){
        switchCards(drawingPile, hand);
        System.out.println("Status: Bot has drawn a new card");
      }
    }
    System.out.println("Number of cards in Bot's hand: " + countCards(hand) + " card(s)"); // Prints the message in the console 
    return topCardSuit; // Returns the information stored in the variable
  }
  
  public static int countSuit (String[] deck, String card){ // Defines function-type method called countSuit that has an integer return data type, as well as a String and a String array formal parameters
    int counter = 0, length = countCards(deck) - 1;         // Initializes the integer variables and assigns the information to them
    // For loop that will iterate through the deck array and count the elements with the same suits as String card
    for (int i = 0; i < length; i++){
      if (deck[i].substring(deck[i].length() - 1).equals(card.substring(card.length() - 1))){
        counter++;
      }
    }
    return counter; // Return the information stored in the counter variable
  }
  
  public static int getScore (String[] hand){ // Defines function-type method called getScore that has an integer return data type and a String array formal parameter
    int score = 0; // Initializes the integer variable by assigning 0 to it
    String num;    // Declares varible data type String and reserves space in memory

    for (int i = 0; i < countCards(hand); i++){ // For loop that will iterate through hand array to count the score based on the cards in hand
      num = hand[i].substring(0, hand[i].length() - 1 ); // Assigns the number of the element at index i to the variable
      // The block of code will compare the number of the card to different letters and count the score based on that
      if (num.equals("A")){
        score += 1;
      }
      else if (num.equals("J") || num.equals("Q") || num.equals("K")){
        score += 10;
      }
      else if (num.equals("8")){
        score += 50;
      }
      else{
        score += Integer.parseInt(num);
      }
    }
    return score; // Return the information stored in the score variable
  }
}

// Resourses:
//    [1] try{} catch{} = https://www.javatpoint.com/try-catch-block
//    [2] Integer.parseInt(String) = https://www.javatpoint.com/java-string-to-int
//    [3] NumberFormatException e = https://www.javatpoint.com/numberformatexception-in-java
//    [4] String.split(seperator) = https://www.javatpoint.com/java-string-split
//    [5] String.toUpperCase() = https://www.javatpoint.com/java-string-touppercase

// End of the Program
