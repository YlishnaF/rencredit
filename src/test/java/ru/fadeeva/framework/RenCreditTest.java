package ru.fadeeva.framework;

import org.junit.jupiter.api.Test;
import ru.fadeeva.framework.base.BaseClass;

public class RenCreditTest extends BaseClass {
    @Test
    public void test() {
        pageManager.getStartPage().clickTopMenu("Вклады")
                .clickSubMenu("Калькулятор доходности")
                .chooseCurrency("Рубли")
                .fillData("Сумма вклада", "300000")
                .fillData("На срок", "6 месяцев")
                .fillData("Ежемесячное пополнение", "50000")
                .clickCapitalizationMonthly()
                .checkCalculationResult();


    }
}


