package com.twu.biblioteca;
import java.awt.print.Book;
import java.util.*;
public class OptionList {
    private String[] OptList = new String[]{"1. List of books", "2. Check out the book", "3. Return the book","4. Quit"};
    private HashMap<String, String[]> BooksList = new HashMap<String, String[]>();
    private HashMap<String, String[]> UserBooked = new HashMap<String, String[]>();
    public OptionList(){

    }

    public void InitOptionList(){
        System.out.println("Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore!");
        DisplayOptionList();
        InitBooksList();
        ReadUserInput(false);
    }

    public void InitBooksList() {
        if (BooksList.keySet().size()==0&&UserBooked.keySet().size()==0){
            String[] bookDetailsOfArts = {"jackie", "2000-1-1"};
            String[] bookDetailsOfMath = {"Chen", "2011-2-12"};
            String[] bookDetailsOfEnglish = {"David", "2015-12-11"};
            String[] bookDetailsOfPhysical = {"Edward", "1999-10-12"};
            BooksList.put("Arts", Arrays.copyOf(bookDetailsOfArts, bookDetailsOfArts.length));
            BooksList.put("Math", Arrays.copyOf(bookDetailsOfMath, bookDetailsOfMath.length));
            BooksList.put("English", Arrays.copyOf(bookDetailsOfEnglish, bookDetailsOfEnglish.length));
            BooksList.put("physical", Arrays.copyOf(bookDetailsOfPhysical, bookDetailsOfPhysical.length));
        }
    }

    public void DisplayBooksList(HashMap<String, String[]> BooksList){
        Set<String> BooksTitle = BooksList.keySet();
        if(BooksTitle.size()!=0) {
            int NumOfOrder = 1;
            for (String title : BooksTitle) {
                System.out.println(NumOfOrder + ". Title:" + title + " | Author: "
                        + BooksList.get(title)[0] + " | Public Year: " + BooksList.get(title)[1]);
                NumOfOrder++;
            }
        }else{
            System.out.println("Sorry, there are no any books available right now.\n");
        }
    }

    public void checkOutBook(Scanner sc, boolean InvalidFlag) {
        try{
            String WelcomeMsg = "Please input the book name you want to order: \n";
            String ErrorMsg = "Please input a valid book name!\n";
            boolean successBooked = false;
            System.out.println(InvalidFlag ? ErrorMsg : WelcomeMsg);
            String orderName = sc.next();
            Set<String> BooksTitle = BooksList.keySet();
            for (String title : BooksTitle) {
                if(orderName.toLowerCase().equalsIgnoreCase(title)){
                    UserBooked.put(title, BooksList.get(title));
                    BooksList.remove(title);
                    System.out.println("Thank you, Enjoy the book!\n");
                    successBooked = true;
                    InitOptionList();
                }
            }
            if(!successBooked){
                System.out.println("Sorry, that book is not available!\n");
                checkOutBook(sc, false);
            }
        }catch (Exception e) {
                checkOutBook(sc, true);
        }
    }

    public void returnBook(Scanner sc, boolean InvalidFlag){
        try {
            int bookNum = UserBooked.keySet().size();
            boolean successReturned = false;
            System.out.println(bookNum>0 ? "Here is the books you have ordered: \n" :
                    "You currently did not order any books yet. \n");
            if(bookNum>0) {
                DisplayBooksList(UserBooked);
                String WelcomeMsg = "Please input the book name which you want to return: \n";
                String ErrorMsg = "Please input a valid book name!\n";
                System.out.println(InvalidFlag ? ErrorMsg : WelcomeMsg);
                String returnBook = sc.next();
                Set<String> BooksTitle = UserBooked.keySet();
                for (String title : BooksTitle) {
                    if(returnBook.toLowerCase().equalsIgnoreCase(title)){
                        BooksList.put(title, UserBooked.get(title));
                        UserBooked.remove(title);
                        System.out.println("Thank you for returning the books! \n");
                        successReturned = true;
                        InitOptionList();
                    }
                }
                if (!successReturned) {
                    System.out.println("That is not a valid book to return! \n");
                    returnBook(sc, false);
                }
            }else{
                InitOptionList();
            }
        }catch (Exception e){
            returnBook(sc, true);
        }
    }

    public void ReadUserInput(boolean InvalidFlag){
        Scanner sc = new Scanner(System.in);
        String WelcomeMsg = "Please press option number:\n";
        String ErrorMsg = "Please select a valid option!\n";
        System.out.println(InvalidFlag ? ErrorMsg : WelcomeMsg);
        while(sc.hasNext()) {
            try{
            String Input = sc.nextLine();
            int NumOfInput = Integer.valueOf(Input);
            switch (NumOfInput) {
                case 1:
                    DisplayBooksList(BooksList);
                    break;
                case 2:
                    checkOutBook(sc, false);
                    break;
                case 3:
                    returnBook(sc, false);
                    break;
                case 4:
                    System.out.println("Thanks for using this app, we are looking forward to see you again :) ");
                    System.exit(0);
                default:
                    ReadUserInput(true);
            }
        }catch(Exception e){
                ReadUserInput(true);
            }
        }
        sc.close();
    }

    public void BooksList(){

    }

    public void DisplayOptionList(){
        for(String Opt : OptList){
            System.out.println(Opt);
        }
    }

}
