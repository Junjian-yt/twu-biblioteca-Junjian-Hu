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

    public boolean UserAuthentication(){
        Scanner sc = new Scanner(System.in);
        String UserName, Password;
        Set<String> NameList;
        HashMap<String, String[]> UserDetails = new HashMap<String, String[]>();
        HashMap<String, String[]> SignInedUser = new HashMap<String, String[]>();
        boolean DetailsMatched = false;


        UserDetails = InitUserDetails();
        System.out.println("Before you start to use this app, you have to login first! \nUserName: ");
        UserName = sc.next();
        System.out.println("\nPassword: ");
        Password = sc.next();
        NameList = UserDetails.keySet();

        for(String Name : NameList){
            if(Name.equalsIgnoreCase(UserName)){
                if(Password.equalsIgnoreCase(UserDetails.get(Name)[0])){
                    System.out.println("\nHi, "+Name+"\n");
                    DetailsMatched = true;

                    SignInedUser.put(Name, Arrays.copyOf(CredentialMap.get(Name), CredentialMap.get(Name).length));
                    OptionList Opts = new OptionList(SignInedUser);
                    try {
                        Opts.InitOptionList();
                    }
                    catch(Exception e) {
                        System.out.println("Error: "+e.getMessage());
                    }

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
