/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Integrity;

import java.util.HashMap;
import java.util.Map;
import mycloudframework.Configuration;

/**
 *
 * @author krb
 */
public class IntegrityManager {

    private Configuration config;
    private Map<String, String> integrityResults;

    public IntegrityManager(Configuration config) {
        this.config = config;

        this.init();

        this.integrityResults = (Map<String, String>) this.config.getProperty("integrityResults", Map.class);
    }

    private void init() {
        Map<String, String> integrityResults = new HashMap<String, String>();
        integrityResults.put("store", "success");
        this.config.addProperty("integrityResults", integrityResults);
    }

    public Boolean checkIntegrity(String command) {

        if (command.isEmpty()) {
            return false;
        }

        if (this.integrityResults.get(command).compareTo(this.store()) != 0) {
            return false;
        }

        return true;
    }

    public String store() {
        return "success";
    }
}
