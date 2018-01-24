package com.keng.common.util;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class CharsetUtil {

    public static final String UTF_8            = "UTF-8";
    public static final String UTF_16           = "UTF-16";
    public static final String UTF_16BE         = "UTF-16BE";
    public static final String UTF_16LE         = "UTF-16LE";
    public static final String ISO_8859_1       = "ISO-8859-1";
    public static final String US_ASCII         = "US-ASCII";

    public static final String GBK              = "gbk";
    public static final String GB2312              = "GB2312";



    private static final Map<String, Charset> CHARSET_MAP;
    static {
        Map<String, Charset> maps = new HashMap<String, Charset>(8);
        maps.put(UTF_8, StandardCharsets.UTF_8);
        maps.put(UTF_16, StandardCharsets.UTF_16);
        maps.put(UTF_16BE, StandardCharsets.UTF_16BE);
        maps.put(UTF_16LE, StandardCharsets.UTF_16LE);
        maps.put(ISO_8859_1, StandardCharsets.ISO_8859_1);
        maps.put(US_ASCII, StandardCharsets.US_ASCII);
        maps.put(GBK, Charset.forName(GBK));
        maps.put(GB2312, Charset.forName(GB2312));
        CHARSET_MAP = Collections.unmodifiableMap(maps);
    }

    public static Charset getCharset(String encoding) {
        return CHARSET_MAP.getOrDefault(encoding, Charset.defaultCharset());
    }

}
