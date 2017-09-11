/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mycloudframework;

import Storage.StorageManager;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 *
 * @author krb
 */
public class ExecutionManager {

    private StorageManager storageManager;

    public ExecutionManager() {
        this.storageManager = new StorageManager();
    }

    public Boolean execute(String command) {
        Method method;
        try {
            method = this.storageManager.getClass().getMethod(command, String.class);
//    Method method = obj.getClass().getMethod(methodName, param1.class, param2.class, ..);
        } catch (SecurityException e) {
            return false;
        } catch (NoSuchMethodException e) {
            return false;
        }

        Boolean success = false;
        try {
            success = (Boolean) method.invoke(this.storageManager, command);
        } catch (IllegalArgumentException e) {
            return false;
        } catch (IllegalAccessException e) {
            return false;
        } catch (InvocationTargetException e) {
            return false;
        }

        return success;
    }
}
