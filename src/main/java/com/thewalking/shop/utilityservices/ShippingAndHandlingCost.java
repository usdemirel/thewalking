package com.thewalking.shop.utilityservices;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class TaxRates {

    private static Properties prop = new Properties();

    static {
        try {
            prop.load(new FileReader("src/main/resources/taxrates.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Double province(String province){
        System.out.print("province: " + province + " ");
        System.out.println(prop.get(province));
        return Double.valueOf((String)prop.get(province));
//        return (Double) prop.get(province);
    }



}
