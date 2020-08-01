package com.twu.biblioteca;

public class BibliotecaApp {
    public static void main(String[] args) {
        OptionList Opts = new OptionList();
        try {
            Opts.InitOptionList();
        }
        catch(Exception e) {
            System.out.println("Error: "+e.getMessage());
        }
    }
}
