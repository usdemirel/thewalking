package com.thewalking.shop.security.config;

public class Constants {

    public static final long ACCESS_TOKEN_VALIDITY_SECONDS = 5*60*60*1000;
    public static final String SIGNING_KEY = "SJ5K@0$DGj7dj#G";
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final String AUTHORITIES_KEY = "scopes";
}
