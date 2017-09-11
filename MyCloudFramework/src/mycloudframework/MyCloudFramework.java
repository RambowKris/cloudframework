/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mycloudframework;

import Confidentiality.AuthenticationManager;
import Confidentiality.AuthorizationManager;
import Integrity.IntegrityManager;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;

/**
 *
 * @author krb
 */
public class MyCloudFramework {

    private Configuration config;

    public MyCloudFramework() {
        this.config = new Configuration();

        this.run();
    }

    public void run() {
        Connector connector = new Connector();
        connector.receive();

        AuthenticationManager authenticationManager = new AuthenticationManager(this.config);
        if (!authenticationManager.authenticate(connector.getAuthenticationData())) {
            connector.setReply("The request could not be authenticated");
            connector.reply();
            return;
        }

        AuthorizationManager authorizationManager = new AuthorizationManager(this.config);
        if (!authorizationManager.authorize(authenticationManager.getUserCredentials(), connector.getCommand())) {
            connector.setReply("The requested command is not allowed");
            connector.reply();
            return;
        }

        IntegrityManager integrityManager = new IntegrityManager(this.config);
        if (!integrityManager.checkIntegrity(connector.getCommand())) {
            connector.setReply("The integrity check failed");
            connector.reply();
            return;
        }

        ExecutionManager executionManager = new ExecutionManager(); 
        if (!executionManager.execute(connector.getCommand())) {
            connector.setReply("The command could not be executed");
            connector.reply();
            return;
        }

        connector.setReply("Request Succeeded");
        connector.reply();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        MyCloudFramework myCloudFramework = new MyCloudFramework();
    }

}
