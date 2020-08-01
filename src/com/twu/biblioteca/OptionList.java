package com.twu.biblioteca;
import java.util.*;
public class OptionList {
    private String[] OptList = new String[]{"1. List of books", "2. quit"};
    private HashMap<String, Integer> BooksList = new HashMap<String, Integer>();
    public OptionList(){

    }

    public void InitOptionList(){
        System.out.println("Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore!");
        DisplayOptionList();
        InitBooksList();
        ReadUserInput();
    }

    public void InitBooksList(){
        BooksList.put("Arts", 99);
        BooksList.put("Math", 99);
        BooksList.put("English", 99);
        BooksList.put("physical", 99);
    }

    public void DisplayBooksList(){
        Set<String> BooksTitle = BooksList.keySet();
        int NumOfOrder = 1;
        for(String title : BooksTitle){
            System.out.println(NumOfOrder+". Title:"+title+" | "+" Num: "+BooksList.get(title));
            NumOfOrder++;
        }
    }

    public void ReadUserInput(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Please press a valid option number:\n");
        while(sc.hasNext()) {
            try{
            String Input = sc.nextLine();
            int NumOfInput = Integer.valueOf(Input);
            switch (NumOfInput) {
                case 1:
                    DisplayBooksList();
                    break;
                case 2:
                    System.out.println("Thanks for using this app, we are looking forward to see you again :) ");
                    System.exit(0);
                default:
                    System.out.println("Invalid Input.");
                    ReadUserInput();
            }
        }catch(Exception e){
                System.out.println("Invalid Input.");
                ReadUserInput();
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
