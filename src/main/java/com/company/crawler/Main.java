package com.company.crawler;

import java.io.IOException;

public class Main {

    private static String URL = "http://wiprodigital.com";
    public static void main(String[] args) {
        Crawler.print("Fetching %s...", URL);
        try {
            Crawler.crawl(URL);
        } catch (IOException e) {
            Crawler.print(e.getMessage());
        }
    }
}
