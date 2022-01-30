package ru.fadeeva.framework.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.fadeeva.framework.managers.DriverManager;
import ru.fadeeva.framework.managers.PageManager;

public class BasePage {
    protected DriverManager driverManager = DriverManager.getInstance();
    protected WebDriverWait wait = new WebDriverWait(driverManager.getDriver(), 15, 1000);
    protected PageManager pageManager = PageManager.getInstance();
    public BasePage() {
        PageFactory.initElements(driverManager.getDriver(), this);
    }

    protected WebElement waitElementToBeClicable(WebElement element) {
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    protected void waitAttributeBecomeAvailable(WebElement webElement, String attribute, String value){
        wait.until(ExpectedConditions.attributeContains(webElement,attribute, value));
    }

    protected void waitElementPresent(WebElement webElement){
        wait.until(ExpectedConditions.visibilityOf(webElement));
    }

    protected void waitText(WebElement webElement, String text){
        wait.until(ExpectedConditions.textToBePresentInElement(webElement,text));
    }

    protected void scrollToElement(WebElement element) {
        ((JavascriptExecutor) driverManager.getDriver()).executeScript(
                "arguments[0].scrollIntoView();", element);
    }

    protected void changeTextElement(WebElement webElement){
        ((JavascriptExecutor) driverManager.getDriver()).executeScript("document.querySelector('div.jq-selectbox__select-text').textContent = \"9 месяцев\"");
    }
}
