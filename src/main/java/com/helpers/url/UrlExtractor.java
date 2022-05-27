package com.helpers.url;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class UrlExtractor implements Extractor {
    @Override
    public List<String> extract(String what, String where) {
        Pattern pattern = Pattern.compile(what, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(where);
        List<String> urls = new ArrayList<>();
        while (matcher.find()) {
            urls.add(matcher.group());
        }
        return urls;
    }

    @Override
    public List<String> extractDistinct(String what, String where) {
        return extract(what, where).stream().distinct().collect(Collectors.toList());
    }
}

