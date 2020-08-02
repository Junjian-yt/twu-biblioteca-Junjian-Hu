package com.twu.biblioteca;

import java.util.HashMap;

public class BibliotecaApp {
    public static void main(String[] args) {
        UserCredential User = new UserCredential();
        HashMap<String, String[]> UserDetails = new HashMap<String, String[]>();
        UserDetails = User.InitUserDetails();

        while(!User.UserAuthentication(UserDetails)){

        }

        OptionList Opts = new OptionList(UserDetails);
        try {
            Opts.InitOptionList();
        }
        catch(Exception e) {
            System.out.println("Error: "+e.getMessage());
        }
    }
}
