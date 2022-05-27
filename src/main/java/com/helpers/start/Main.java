package com.helpers.start;

import com.helpers.files.FileReader;
import com.helpers.url.Extractor;
import com.helpers.url.UrlExtractor;

import java.util.List;
import java.util.stream.Collectors;

public class Main {
    private static final String URL = "(?:(?:https?|ftp|file):\\/\\/|www\\.|ftp\\.)(?:\\([-A-Z0-9+&@#\\/%=~_|$?!:,.]*\\)|[-A-Z0-9+&@#\\/%=~_|$?!:,.])*(?:\\([-A-Z0-9+&@#\\/%=~_|$?!:,.]*\\)|[A-Z0-9+&@#\\/%=~_|$])";

    public static void main(String[] args) {
        FileReader fileReader = new FileReader();
        Extractor extractor = new UrlExtractor();

        String file = fileReader.read("test1.txt");

//        List<String> urls = extractor.extract(FETCH, file);
        List<String> urls = extractor.extractDistinct(URL, file);

        List<String> collect = filterUrls(urls);

        collect.forEach(System.out::println);
    }

    private static List<String> filterUrls(List<String> urls) {
        return urls.stream().filter(Main::contains).collect(Collectors.toList());
    }

    private static boolean notContains(String url) {
        return !url.contains("//cdn.") &&
                !url.contains("favicon") &&
                !url.contains("/static/js") &&
                !url.contains("manifest.json") &&
                !url.equals("http://localhost:3000/");
    }

    private static boolean contains(String url) {
        return url.contains("cdn") ||
                url.contains("favicon") ||
                url.contains("/static/js") ||
                url.contains("manifest.json") ||
                url.equals("http://localhost:3000/");
    }
}
