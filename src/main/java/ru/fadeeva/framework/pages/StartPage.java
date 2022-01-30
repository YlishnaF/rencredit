package ru.fadeeva.framework.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class StartPage extends BasePage{
    @FindBy(xpath = "//a[@class=\"site-header__content-bottom-nav-link nav__link nav__link_parent\"]")
    private List<WebElement> topMenu;

    @FindBy(xpath = "//a[@class=\"nav__link\"]")
    private List<WebElement> subMenu;

    @FindBy(xpath = "//html")
    private WebElement mainElement;

    @FindBy(xpath = "//h2[contains(text(),\"Рассчитайте доходность\")]")
    private WebElement titleCalculator;

    @Step(value = "В верхнем меню выбираем {s}")
    public StartPage clickTopMenu(String s){
        for (WebElement menu: topMenu) {
            if(menu.getText().contains(s)){
                waitElementToBeClicable(menu);
                menu.click();
                waitAttributeBecomeAvailable(mainElement, "class", "bx-core bx-no-touch bx-no-retina bx-chrome is-subnav-opened");
                return pageManager.getStartPage();
            }
        }
        return pageManager.getStartPage();
    }

    @Step(value = "В открывшемся меню выбираем {s}")
    public CalculatorPage clickSubMenu(String s){
        for (WebElement sMenu: subMenu) {
            if(sMenu.getText().contains(s)){
                waitElementToBeClicable(sMenu);
                sMenu.click();
                waitElementPresent(titleCalculator);
                return pageManager.getCalculatorPage();
            }
        }
        return pageManager.getCalculatorPage();
    }
}
