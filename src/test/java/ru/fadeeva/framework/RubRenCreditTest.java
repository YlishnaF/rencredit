package ru.fadeeva.framework;

import org.junit.jupiter.api.Test;
import ru.fadeeva.framework.base.BaseClass;
import ru.fadeeva.framework.data.VariablesForSearch;

public class RubRenCreditTest extends BaseClass {
    @Test
    public void test() {
        pageManager.getStartPage().clickTopMenu(VariablesForSearch.CONTRIBUTIONS)
                .clickSubMenu(VariablesForSearch.YIELD_CALCULATOR)
                .chooseCurrency(VariablesForSearch.CURRENCY_RUB)
                .fillData(VariablesForSearch.SUM_CONTRIBUTION, VariablesForSearch.SUM_CONTRIBUTION_FOR_RUB_NUMBER)
                .fillData(VariablesForSearch.DURATION_LINE, VariablesForSearch.DURATION_NUMBER_RUB)
                .fillData(VariablesForSearch.MONTHLY_ADD_MONEY_LINE, VariablesForSearch.MONTHLY_ADD_MONEY_NUMBER_RUB)
                .clickCapitalizationMonthly()
                .checkCalculationResult();
    }
}


