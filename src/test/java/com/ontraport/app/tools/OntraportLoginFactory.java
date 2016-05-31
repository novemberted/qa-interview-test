package com.ontraport.app.tools;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import com.google.gson.Gson;

/**
 * Gives out logins to use in such a way that we dont have 50 tests all in the same account, and so that tests
 * that need a special account can get the correct login info in the same build as other tests.
 *
 * @author jason
 * @modified Dec 8, 2015
 * @changelog {date} by {name}
 * {changes}
 * @since Dec 8, 2015
 */
public class OntraportLoginFactory
{
    /*
     * store logins in etc/login
     *      better to take this location from a config file
     * tests call OntraportLoginFactory.getLogin()
     */
    static String loginDir;
    static
    {
        loginDir = "etc/login";
        if ( loginDir.charAt(loginDir.length() - 1) != '/' )
        {
            loginDir = loginDir + "/";
        }
    }

    /**
     * Get a login to an Ontraport account
     *
     * @return LoginInfo containing the username and password
     *
     * @throws IOException
     */
    public static LoginInfo getLogin () throws IOException
    {
        // this method will look in the login dir for json files to load
        return getLogin("production");
    }

    /**
     * get the login info for a special account.
     *
     * @param account string name of the account. Pass in "any" if you don't care what you get.
     *
     * @return LoginInfo loaded from <code>account</code>.json
     *
     * @throws IOException
     */
    public static LoginInfo getLogin (String account) throws IOException
    {
        if ( account == null || account.equals("any") )
        {
            //TODO make some fancy code for load-balancing account usage
            account = "jason";
        }
        FileReader input = new FileReader(loginDir + account + ".json");
        BufferedReader inputReader = new BufferedReader(input);

        String json = inputReader.readLine();
        inputReader.close();

        System.out.println("You got account: " + account);

        Gson gson = new Gson();
        return gson.fromJson(json, LoginInfo.class);
    }
}