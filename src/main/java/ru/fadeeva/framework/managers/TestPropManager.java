package ru.fadeeva.framework.managers;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class TestPropManager {
    private static TestPropManager INSTANCE=null;

    private final Properties properties =new Properties();

    private TestPropManager(){
        loadApplicationProperties();
        loadCustomProperties();

    }

    public static TestPropManager getInstance(){
        if(INSTANCE==null){
            INSTANCE = new TestPropManager();
        }
        return INSTANCE;
    }
    private void loadApplicationProperties(){
        String nameFile = System.getProperty("propFile", "application");

        try{
            properties.load(new FileInputStream(
                    new File("src/main/resources/"+
                            nameFile+".properties")));
        } catch (IOException e){
            e.printStackTrace();
        }

    }

    private void loadCustomProperties(){
        properties.forEach((key, value)->System.getProperties()
                .forEach((customKey,customValue)->{
                    if(key.toString().equals(customKey.toString())&&
                            !value.toString().equals(customValue.toString())){
                        properties.setProperty(key.toString(),customValue.toString());
                    }
                }));
    }

    public String getProperty(String key, String defaultValue){
        return properties.getProperty(key, defaultValue);
    }

    public String getProperty(String key){
        return properties.getProperty(key);
    }

}