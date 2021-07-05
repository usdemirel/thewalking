package com.thewalking.shop.utilityservices;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class TaxRates {

    private static Properties prop;

    static {
        try {
            prop.load(new FileReader("taxrates.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Double province(String province){
        return (Double) prop.get(province);
    }



}
