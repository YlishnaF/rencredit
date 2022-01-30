package ru.fadeeva.framework;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import ru.fadeeva.framework.base.BaseClass;

public class RenCreditParamTest extends BaseClass {
    @ParameterizedTest
    @CsvFileSource(resources = "/data.csv")
    public void test(String currency, String value1, String value2, String value3) {
        pageManager.getStartPage().clickTopMenu("Вклады")
                .clickSubMenu("Калькулятор доходности")
                .chooseCurrency(currency)
                .fillData("Сумма вклада", value1)
                .fillData("На срок", value2)
                .fillData("Ежемесячное пополнение", value3)
                .clickCapitalizationMonthly()
                .checkCalculationResult();
    }
}
