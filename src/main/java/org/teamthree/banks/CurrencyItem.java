package org.teamthree.banks;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class CurrencyItem {

    private Integer currencyCodeA;
    private Integer currencyCodeB;
    private BigDecimal rateBuy;
    private BigDecimal rateSell;
    private Currency original;
    private Currency toConvert;
    private Currency ccy;
    private Currency base_ccy;
    private BigDecimal buy;
    private BigDecimal sale;
    private BigDecimal rate;
    private String cc;
}
