package com.company.crawler;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class Crawler {

    /**
     * list all links, media, et imports on a giving url
     * @param url to crawl
     * @throws IOException if something goes wrong
     */
    public static void crawl(String url) throws IOException {
        Document doc = Jsoup.connect(url).get();
        Elements links = doc.select("a[href]");
        Elements media = doc.select("[src]");
        Elements imports = doc.select("link[href]");

        // list all media
        print("\nMedia: (%d)", media.size());
        for (Element src : media) {
            if (src.normalName().equals("img")) {
                print(" * %s: <%s> %sx%s (%s)",
                        src.tagName(), src.attr("abs:src"), src.attr("width"), src.attr("height"),
                        src.attr("alt"));
            }else {
                print(" * %s: <%s>", src.tagName(), src.attr("abs:src"));
            }
        }

        // list all imports
        print("\nImports: (%d)", imports.size());
        for (Element link : imports) {
            print(" * %s <%s> (%s)", link.tagName(),link.attr("abs:href"), link.attr("rel"));
        }

        // list all links and pages
        print("\nLinks: (%d)", links.size());
        for (Element link : links) {
            print(" * a: <%s>  (%s)", link.attr("abs:href"), link.text());
        }
    }

    public static void print(String msg, Object... args) {
        System.out.println(String.format(msg, args));
    }
}
