package com.ontraport.app.tools;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import com.google.gson.Gson;

/**
 * Make jason update this comment block.
 *
 * @author jason
 * @since 5/24/16
 */
public class LoginFactory
{
    static String loginDir = "etc/login/";

    public static LoginInfo getAccount (String account) throws IOException
    {
        if ( account == null || account.equals("any") )
        {
            //TODO make some fancy code for load-balancing account usage
            account = "personal";
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
