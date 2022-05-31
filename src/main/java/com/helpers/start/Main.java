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
//        List<String> collect2 = replaceInUrls(collect);

        collect.stream().sorted().forEach(System.out::println);
    }

    private static List<String> replaceInUrls(List<String> urls) {
        return urls.stream().map(url -> url.replace("http://localhost:8081/incaso_crm_war_exploded", "")).collect(Collectors.toList());
    }

    private static List<String> filterUrls(List<String> urls) {
        return urls.stream().filter(Main::filter).collect(Collectors.toList());
    }

    private static boolean filter(String url) {
        List<String> containing = List.of("//cdn.", "favicon", "/static/js", "manifest.json", "/assets", "/js", "/autocomplete", "/images/", "/schedule");
        List<String> equals = List.of("http://localhost:8081/");
        for (String key : containing) {
            if (url.contains(key)) return false;
        }
        for (String key : equals) {
            if (url.equals(key)) return false;
        }
        return true;
    }
}
