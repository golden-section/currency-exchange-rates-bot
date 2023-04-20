package org.teamthree.banks.monobank;

import lombok.Data;
import org.teamthree.banks.Currency;
import java.math.BigDecimal;

@Data
public class CurrencyItem {

    private Integer currencyCodeA;
    private Integer currencyCodeB;
    private BigDecimal rateBuy;
    private BigDecimal rateSell;
    private Currency original;
    private Currency toConvert;
}
