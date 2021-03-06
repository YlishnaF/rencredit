package ru.fadeeva.framework.managers;


import java.util.concurrent.TimeUnit;

import static ru.fadeeva.framework.utils.PropsConst.IMPLICITLY_WAIT;
import static ru.fadeeva.framework.utils.PropsConst.PAGE_LOAD_TIMEOUT;


public class InitManager {
    private static final TestPropManager props = TestPropManager.getInstance();
    private  static final DriverManager driverManager = DriverManager.getInstance();

    public static void initFramework(){
        System.out.println("InitFrame");
        driverManager.getDriver().manage().window().maximize();
        driverManager.getDriver().manage().timeouts().pageLoadTimeout(Integer.parseInt(props.getProperty(PAGE_LOAD_TIMEOUT)), TimeUnit.SECONDS);
        driverManager.getDriver().manage().timeouts().implicitlyWait(Integer.parseInt(props.getProperty(IMPLICITLY_WAIT)), TimeUnit.SECONDS);
    }

    public static void quitFramework(){
        System.out.println("quitFrame");
        driverManager.quitDriver();
    }
}