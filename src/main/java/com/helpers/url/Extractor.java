package com.helpers.url;

import java.util.List;

public interface Extractor {
    List<String> extract(String what, String where);

    List<String> extractDistinct(String what, String where);
}
