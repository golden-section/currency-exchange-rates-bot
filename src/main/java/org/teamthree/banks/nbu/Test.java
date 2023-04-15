package org.teamthree.banks.nbu;


public class Test {
    public static void main(String[] args) {
        System.out.println("EUR: " + NbuApiIntegration.getCurrentRate(Currency.EUR));
        System.out.println("USD: " + NbuApiIntegration.getCurrentRate(Currency.USD));
        System.out.println("PLZ: " + NbuApiIntegration.getCurrentRate(Currency.PLN));
    }
}
