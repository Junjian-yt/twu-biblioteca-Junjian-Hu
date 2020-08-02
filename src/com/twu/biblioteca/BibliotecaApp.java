package com.twu.biblioteca;

import java.util.HashMap;

public class BibliotecaApp {
    public static void main(String[] args) {
        UserCredential User = new UserCredential();
        while(!User.UserAuthentication()){ }
    }
}
