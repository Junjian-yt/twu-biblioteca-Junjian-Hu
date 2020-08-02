package com.twu.biblioteca;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;

public class UserCredential {
    private HashMap<String, String[]> CredentialMap = new HashMap<String, String[]>();

    public UserCredential(){

    }

    public HashMap<String, String[]> InitUserDetails(){
        String[] Credentials = {"password", "default@email.com", "123456789"};
        CredentialMap.put("123-4567", Arrays.copyOf(Credentials, Credentials.length));
        return CredentialMap;
    }

    public boolean UserAuthentication(HashMap<String, String[]> UserDetails){
        Scanner sc = new Scanner(System.in);
        String UserName, Password;
        Set<String> NameList;
        boolean DetailsMatched = false;

        System.out.println("Before you start to use this app, you have to login first! \nUserName: ");
        UserName = sc.next();
        System.out.println("\nPassword: ");
        Password = sc.next();
        NameList = UserDetails.keySet();

        for(String Name : NameList){
            if(Name.equalsIgnoreCase(UserName)){
                if(Password.equalsIgnoreCase(UserDetails.get(Name)[0])){
                    System.out.println("Hi, "+Name+"\n");
                    DetailsMatched = true;
                    return true;
                }else{
                    System.out.println("Invalid Password!\n");
                    return false;
                }
            }
        }

        System.out.println("Invalid username or password!\n");
        return DetailsMatched;
    }

}
