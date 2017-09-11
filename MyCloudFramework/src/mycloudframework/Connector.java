/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mycloudframework;

/**
 *
 * @author krb
 */
public class Connector {

    private String reply;
    private String command;
    private String authenticationData;
    private String data;

    public Connector() {
        this.init();
    }

    public void init(){
        this.authenticationData = "ThisIsaTestAuthenticationData";        
        this.command = "store";
        this.data = "";
    }
    
    public String getAuthenticationData() {
        return this.authenticationData;
    }

    public String getCommand() {
        return this.command;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }

    public void reply() {
        System.out.println(this.reply);
    }
    
    public void receive(){
    }
}
