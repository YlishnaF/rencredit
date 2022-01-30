package ru.fadeeva.framework.managers;


import ru.fadeeva.framework.pages.CalculatorPage;
import ru.fadeeva.framework.pages.StartPage;

public class PageManager {
    private static PageManager INSTANCE =null;
    private StartPage startPage;
    private CalculatorPage calculatorPage;
    private PageManager(){

    }
    public static PageManager getInstance(){
        if(INSTANCE==null){
            INSTANCE= new PageManager();
        }
        return INSTANCE;
    }
    public StartPage getStartPage(){
        if(startPage==null){
            startPage=new StartPage();
        }
        return startPage;
    }

    public CalculatorPage getCalculatorPage(){
        if(calculatorPage==null){
            calculatorPage = new CalculatorPage();
        }
        return calculatorPage;
    }

}
