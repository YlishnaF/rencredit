package ru.fadeeva.framework.pages;

import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.fadeeva.framework.data.VariablesForSearch;

import java.util.List;

public class CalculatorPage extends BasePage {
    private boolean isCurrencyRub = true;
    @FindBy(xpath = "//span[@class=\"calculator__currency-field-btn\"]")
    private List<WebElement> currency;

    @FindBy(xpath = "//div[@class=\"calculator__content\"]")
    private WebElement calculatorBlock;

    @FindBy(xpath = "//input[@name=\"amount\"]")
    private WebElement amountOfMoney;

    @FindBy(xpath = "//div[@class=\"jq-selectbox__select-text\"]")
    private WebElement duration;

    @FindBy(xpath = "//li[@style]")
    private List<WebElement> durationsVariant;

    @FindBy(xpath = "//input[@name=\"replenish\"]")
    private WebElement addMoneyMonthly;

    @FindBy(xpath = "//span[contains(text(), \"Ежемесячная капитализация\")]/../..//div[contains (@class, \"jq-checkbox calculator__check\")]")
    private WebElement monthlyCapitalization;

    @FindBy(xpath = "//div[@class=\"calculator__result-block\"]")
    private WebElement calculationResultBlock;

    @FindBy(xpath = "//td[contains(text(), \"Начислено %\")]/..//span[@class=\"js-calc-earned\"]")
    private WebElement percentAmount;

    @FindBy(xpath = "//span[@class=\"js-calc-result\"]")
    private WebElement moneyAfterSixMonth;

    @FindBy(xpath = "//td[contains(text(), \"Пополнение за\")]/..//span[@class= \"js-calc-replenish\"]")
    private WebElement addedMoneyForSixMonth;

    @Step(value = "Выбираем вылюту {s}")
    public CalculatorPage chooseCurrency(String s) {
        if (s.equals("Рубли")) {
            isCurrencyRub = true;
        } else isCurrencyRub = false;

        for (WebElement myCurrency : currency) {
            if (myCurrency.getText().contains(s)) {
                waitElementToBeClicable(myCurrency);
                myCurrency.click();
            }
        }
        return pageManager.getCalculatorPage();
    }

    @Step(value = "Заполняем {line} значением {value}")
    public CalculatorPage fillData(String line, String value) {
        switch (line) {
            case (VariablesForSearch.SUM_CONTRIBUTION):
                scrollToElement(calculatorBlock);
                amountOfMoney.click();
                amountOfMoney.sendKeys(value);
                break;
            case (VariablesForSearch.DURATION_LINE):
                scrollToElement(amountOfMoney);
                int i = 0;
                try{
                    setDuration(value);
                } catch (StaleElementReferenceException e){
                    while (i<2){
                        i++;
                        fillData(line,value);
                    }
                }
                break;
            case (VariablesForSearch.MONTHLY_ADD_MONEY_LINE):
                scrollToElement(duration);
                waitElementToBeClicable(addMoneyMonthly);
                addMoneyMonthly.click();
                addMoneyMonthly.sendKeys(value);
                break;
        }
        return pageManager.getCalculatorPage();
    }

    @Step(value = "Добавляем ежемесячную капитализацию")
    public CalculatorPage clickCapitalizationMonthly() {
        waitElementToBeClicable(monthlyCapitalization);
        monthlyCapitalization.click();
        waitAttributeBecomeAvailable(monthlyCapitalization, "class", "jq-checkbox calculator__check checked");
        return pageManager.getCalculatorPage();
    }

    @Step(value = "Проверяем результаты расчета")
    public void checkCalculationResult() {
        scrollToElement(calculationResultBlock);
        if (isCurrencyRub) {
            waitText(moneyAfterSixMonth, "565 478,55");
            Assertions.assertEquals("15478,55", getPrice(percentAmount), "Начисленные проценты должны быть 15 478,55 руб");
            Assertions.assertEquals("250000", getPrice(addedMoneyForSixMonth), "Дополнительно внесено 250000 руб");
            Assertions.assertEquals("565478,55", getPrice(moneyAfterSixMonth), "Ожидаем к снятию через пол года 565 478,55 руб");

        } else {
            waitText(moneyAfterSixMonth, "720 920,60");
            Assertions.assertEquals("920,60", getPrice(percentAmount), "Начисленные проценты должны быть 15 478,55 $");
            Assertions.assertEquals("220000", getPrice(addedMoneyForSixMonth), "Дополнительно внесено 220000 руб $");
            Assertions.assertEquals("720920,60", getPrice(moneyAfterSixMonth), "Ожидаем к снятию через пол года 565 478,55 $");

        }

    }
    public String getPrice(WebElement element) {
        if (isCurrencyRub) {
            return element.getText().split("₽")[0].replaceAll(" ", "");
        } else return element.getText().split("\\$")[0].replaceAll(" ", "");

    }
    private void setDuration(String value){
        waitElementToBeClicable(duration);
        duration.click();
        for (WebElement durationChose : durationsVariant) {
            if (durationChose.getText().contains(value)) {
                durationChose.click();
                waitText(duration, value);
                break;
            }
        }
    }
}
