package com.thewalking.shop.utilityservices;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ShippingAndHandlingCost {

    private static Properties prop = new Properties();

    static {
        try {
            prop.load(new FileReader("src/main/resources/shippingandhandlingcosts.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Double province(String province){
        return Double.valueOf((String)prop.get(province));
    }



}
