package com.twu.biblioteca;
import java.awt.print.Book;
import java.lang.reflect.Type;
import java.util.*;
public class OptionList {
    private String[] OptList = new String[]{"1. List of books", "2. Check out the book", "3. Return the book",
            "4. List of Movies", "5. Check out the movie", "6. Return the movie", "7. View my credentials","8. Quit"};
    private HashMap<String, String[]> BooksList = new HashMap<String, String[]>();
    private HashMap<String, String[]> UserBooked = new HashMap<String, String[]>();
    private HashMap<String, String[]> MoviesList = new HashMap<String, String[]>();
    private HashMap<String, String[]> MovieBooked = new HashMap<String, String[]>();
    private HashMap<String, String[]> UserCredential = new HashMap<String, String[]>();
    Movies movies = new Movies();
    public OptionList(HashMap<String, String[]> UserCredential){
        this.UserCredential = UserCredential;
    }

    public void InitOptionList(){
        System.out.println("Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore!");
        DisplayOptionList();
        InitBooksList();
        this.MoviesList = movies.InitMovieList(MoviesList);
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
            System.out.println("Sorry, there are no any items available right now.\n");
        }
    }

    public void checkOut(Scanner sc, boolean InvalidFlag, HashMap<String, String[]> BooksList,
                         HashMap<String, String[]> UserBooked) {
        try{
            String WelcomeMsg = "Please input the item name you want to order: \n";
            String ErrorMsg = "Please input a valid item name!\n";
            boolean successBooked = false;
            System.out.println(InvalidFlag ? ErrorMsg : WelcomeMsg);
            String orderName = sc.next();
            Set<String> BooksTitle = BooksList.keySet();
            for (String title : BooksTitle) {
                if(orderName.toLowerCase().equalsIgnoreCase(title)){
                    UserBooked.put(title, BooksList.get(title));
                    BooksList.remove(title);
                    System.out.println("Thank you, Enjoy the item!\n");
                    successBooked = true;
                    InitOptionList();
                }
            }
            if(!successBooked){
                System.out.println("Sorry, that item is not available!\n");
                checkOut(sc, false, BooksList, UserBooked);
            }
        }catch (Exception e) {
                checkOut(sc, true, BooksList, UserBooked);
        }
    }

    public void ReturnItem(Scanner sc, boolean InvalidFlag, String TypeOfReturn, HashMap<String, String[]> ItemList,
                           HashMap<String, String[]> BorrowedList){
        try {
            int bookedNum = BorrowedList.keySet().size();
            boolean successReturned = false;
            System.out.println(bookedNum>0 ? "Here is the items you have ordered: \n" :
                    "You currently did not order any item yet. \n");
            if(bookedNum>0) {

                if(TypeOfReturn.equalsIgnoreCase("book")) {
                    DisplayBooksList(BorrowedList);
                }else if(TypeOfReturn.equalsIgnoreCase("movie")){
                    movies.DisplayMoviesList(BorrowedList);
                }

                String WelcomeMsg = "Please input the item name which you want to return: \n";
                String ErrorMsg = "Please input a valid item name!\n";
                System.out.println(InvalidFlag ? ErrorMsg : WelcomeMsg);
                String returnBook = sc.next();
                Set<String> BooksTitle = BorrowedList.keySet();
                for (String title : BooksTitle) {
                    if(returnBook.toLowerCase().equalsIgnoreCase(title)){
                        ItemList.put(title, BorrowedList.get(title));
                        BorrowedList.remove(title);
                        System.out.println("Thank you for returning the items! \n");
                        successReturned = true;
                        InitOptionList();
                    }
                }
                if (!successReturned) {
                    System.out.println("That is not a valid item to return! \n");
                    ReturnItem(sc, false, TypeOfReturn, ItemList, BorrowedList);
                }
            }else{
                InitOptionList();
            }
        }catch (Exception e){
            ReturnItem(sc, true, TypeOfReturn, ItemList, BorrowedList);
        }
    }

    public void ViewUserCredentials(){
        String[] CredentialTitles = new String[]{"UserName: ", "password", "Email: ", "Phone Number: "};
        String[] CredentialDetails = new String[4];
        int count = 1;
        for(String str : UserCredential.keySet()){
                CredentialDetails[0] = str;
        }
        for(String str : UserCredential.get(CredentialDetails[0])){
            CredentialDetails[count] = str;
            count++;
        }
        for(int i=0; i<4; i++){
            System.out.println(i == 1 ? "" : (CredentialTitles[i]+CredentialDetails[i]+"\n"));
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
                    checkOut(sc, false, BooksList, UserBooked);
                    break;
                case 3:
                    ReturnItem(sc, false, "book", BooksList, UserBooked);
                    break;
                case 4:
                    movies.DisplayMoviesList(MoviesList);
                    break;
                case 5:
                    checkOut(sc, false, MoviesList, MovieBooked);
                    break;
                case 6:
                    ReturnItem(sc, false, "movie", MoviesList, MovieBooked);
                    break;
                case 7:
                    ViewUserCredentials();
                    break;
                case 8:
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
