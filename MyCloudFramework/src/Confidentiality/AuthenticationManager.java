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
public class AuthenticationManager {

    private Configuration config;
    private Map<String, String> userCredentialsList;

    private String userCredentials;

    public AuthenticationManager(Configuration config) {
        this.config = config;

//        this.init();
        
        this.userCredentialsList = (Map<String, String>) config.getProperty("userCredentials", Map.class);
    }

    private void init() {
        this.userCredentialsList = new HashMap<String, String>();
        this.userCredentialsList.put("ThisIsaTestAuthenticationData", "MyUserCredentials");
        config.addProperty("userCredentials", this.userCredentialsList);
    }

    public Boolean authenticate(String authenticationData) {
        this.userCredentials = this.userCredentialsList.get(authenticationData);

        if (this.userCredentials == null) {
            return false;
        }

        return true;
    }

    public String getUserCredentials() {
        return this.userCredentials;
    }
}
