package com.ontraport.app.tools;

/**
 * A Java representation of the json files we use to store account information in.
 * This file will be encoded by GSON to make the 'created' account json file
 *
 * @author jason
 * @since grid happened
 */
public class LoginInfo
{
    private String username;
    private String password;
    private String apiAppId = "";
    private String apiKey = "";

    public LoginInfo (String u, String p)
    {
        username = u;
        password = p;
    }

    public LoginInfo (String u, String p, String id, String key)
    {
        username = u;
        password = p;
        apiAppId = id;
        apiKey = key;
    }

    public String getUsername ()
    {
        return username;
    }

    public String getPassword ()
    {
        return password;
    }

    public String getApiAppId ()
    {
        return apiAppId;
    }

    public void setApiAppId (String apiAppId)
    {
        this.apiAppId = apiAppId;
    }

    public String getApiKey ()
    {
        return apiKey;
    }

    public void setApiKey (String apiKey)
    {
        this.apiKey = apiKey;
    }
}