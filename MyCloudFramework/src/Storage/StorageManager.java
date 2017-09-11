/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Storage;

/**
 *
 * @author krb
 */
public class StorageManager {

    public Boolean store(String command) {

        if (command.isEmpty()) {
            return false;
        }

        return true;
    }
}
