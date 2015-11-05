package com.n2cj.util;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class TagListUtil {
    public static final List<Integer> toList(final String tagList) {
        if (tagList.length() <= 1) {
            return Collections.emptyList();
        } else {
            String tl = tagList.substring(1, tagList.length() - 1);
            String[] tla = StringUtils.split(tl, '#');
            return toList(tla);
        }
    }

    public static final List<Integer> toList(final String[] tagList) {
        final List<Integer> result = new ArrayList<>(tagList.length);

        for (final String s : tagList) {
            result.add(Integer.parseInt(s));
        }

        return result;
    }

    public static final boolean isFiltered(final List<Integer> filters, final List<Integer> tags) {
        if (filters.isEmpty()) {
            return false;
        } else if (tags.isEmpty()) {
            return true;
        } else {
            for (final int v : tags) {
                if (filters.contains(v)) {
                    return false;
                }
            }

            return true;
        }
    }
}
