/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Confidentiality;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import mycloudframework.Configuration;

/**
 *
 * @author krb
 */
public class AuthorizationManager {

    private Configuration config;
    private Map<String, ArrayList<String>> accessControlList = new HashMap<>();

    public AuthorizationManager(Configuration config) {
        this.config = config;

//        this.init();
        this.accessControlList = (Map<String, ArrayList<String>>) this.config.getProperty("allowedCommands", Map.class);

    }

    private void init() {
        ArrayList<String> allowedCommands = new ArrayList<>();
        allowedCommands.add("store");

        Map<String, ArrayList<String>> accessControlList = new HashMap<>();
        accessControlList.put("My=User Credentials", allowedCommands);
        accessControlList.put("MyUserCredentials", allowedCommands);
        this.config.addProperty("allowedCommands", accessControlList);
    }

    public Boolean authorize(String userCredentials, String command) {
        if (!this.accessControlList.containsKey(userCredentials)) {
            return false;
        }

        ArrayList<String> allowedCommands = this.accessControlList.get(userCredentials);

        if (!allowedCommands.contains(command)) {
            return false;
        }

        return true;
    }

}
