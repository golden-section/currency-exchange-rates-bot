package org.teamthree.banks.nbu;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CurrencyRate {
    private BigDecimal rate;
    private String cc;
}