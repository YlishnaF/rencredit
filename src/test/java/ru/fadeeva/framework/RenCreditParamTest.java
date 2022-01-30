package ru.fadeeva.framework;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import ru.fadeeva.framework.base.BaseClass;
import ru.fadeeva.framework.data.VariablesForSearch;

public class RenCreditParamTest extends BaseClass {
    @ParameterizedTest
    @CsvFileSource(resources = "/data.csv")
    public void test(String currency, String value1, String value2, String value3) {
        pageManager.getStartPage().clickTopMenu(VariablesForSearch.CONTRIBUTIONS)
                .clickSubMenu(VariablesForSearch.YIELD_CALCULATOR)
                .chooseCurrency(currency)
                .fillData(VariablesForSearch.SUM_CONTRIBUTION, value1)
                .fillData(VariablesForSearch.DURATION_LINE, value2)
                .fillData(VariablesForSearch.MONTHLY_ADD_MONEY_LINE, value3)
                .clickCapitalizationMonthly()
                .checkCalculationResult();
    }
}
