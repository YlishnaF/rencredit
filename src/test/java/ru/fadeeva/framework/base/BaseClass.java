package ru.fadeeva.framework.base;

import ru.fadeeva.framework.managers.DriverManager;
import ru.fadeeva.framework.managers.InitManager;
import ru.fadeeva.framework.managers.PageManager;
import ru.fadeeva.framework.managers.TestPropManager;
import ru.fadeeva.framework.utils.MyAllureListener;
import ru.fadeeva.framework.utils.PropsConst;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(MyAllureListener.class)
public class BaseClass {
    private final DriverManager driverManager = DriverManager.getInstance();
    protected PageManager pageManager = PageManager.getInstance();
    private final TestPropManager propManager = TestPropManager.getInstance();

    @BeforeAll
    public static void beforeClass(){
        InitManager.initFramework();
    }

    @BeforeEach
    void before() {
        driverManager.getDriver().get(propManager.getProperty(PropsConst.BASE_URL));
    }

    @AfterAll
    public static void afterAll() {
        InitManager.quitFramework();
    }

}