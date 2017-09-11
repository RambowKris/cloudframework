/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mycloudframework;

import com.google.gson.Gson;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import org.json.simple.JSONObject;

/**
 *
 * @author krb
 */
public class Configuration {

    private String configurationFileName = "config.properties";
    private Gson gson;

    public Configuration() {
        this.gson = new Gson();
    }

    public Object getProperty(String parameterName, Class className) {
        String json = this.loadProperty(parameterName);
        return this.gson.fromJson(json, className);
    }

    public void addProperty(String parameterName, Object object) {
        String json = this.gson.toJson(object);
        
        Properties prop = new Properties();
        InputStream input = null;
        OutputStream output = null;

        try {

            input = new FileInputStream(this.configurationFileName);

            // load a properties file
            prop.load(input);

//            Enumeration<?> e = prop.propertyNames();
//            while (e.hasMoreElements()) {
//                String key = (String) e.nextElement();
//                String value = prop.getProperty(key);
//            }
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

         try {

            output = new FileOutputStream(this.configurationFileName);

            prop.setProperty(parameterName, json);
            prop.store(output, null);

        } catch (IOException io) {
            io.printStackTrace();
        } finally {
            if (output != null) {
                try {
                    output.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }       
    }

    public void setProperty(String parameterName, Object object) {
        String json = this.gson.toJson(object);
        this.saveProperty(parameterName, json);
    }

    private void toJSON() {
        JSONObject obj = new JSONObject();

        String[] strings = {"string", ""};

        obj.put("name", "foo");
        obj.put("num", new Integer(100));
        obj.put("balance", new Double(1000.21));
        obj.put("is_vip", new Boolean(true));
        obj.put("go", strings);

        System.out.println(obj);
    }

    private void saveProperty(String parameterName, String value) {
        Properties prop = new Properties();
        OutputStream output = null;

        try {

            output = new FileOutputStream(this.configurationFileName);

            prop.setProperty(parameterName, value);
            prop.store(output, null);

        } catch (IOException io) {
            io.printStackTrace();
        } finally {
            if (output != null) {
                try {
                    output.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    private String loadProperty(String propertyName) {
        Properties prop = new Properties();
        InputStream input = null;

        String property = null;
        try {

            input = new FileInputStream(this.configurationFileName);

            // load a properties file
            prop.load(input);

            property = prop.getProperty(propertyName);
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return property;
    }

}
