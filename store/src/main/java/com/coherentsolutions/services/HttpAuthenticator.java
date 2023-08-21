package com.coherentsolutions.services;

import com.coherentsolutions.Configuration;
import com.coherentsolutions.WebServerConnectionOptions;
import com.sun.net.httpserver.BasicAuthenticator;

public class HttpAuthenticator extends BasicAuthenticator {
    private String user;
    private String password;

    public HttpAuthenticator() {
        super("Java training shop authenticator");
        user = Configuration.getInstance().webserverConnection.get(WebServerConnectionOptions.USER);
        password = Configuration.getInstance().webserverConnection.get(WebServerConnectionOptions.PASSWORD);
    }
    @Override
    public boolean checkCredentials(String username, String password) {
        return username.equals(this.user) && password.equals(this.password);
    }
}
