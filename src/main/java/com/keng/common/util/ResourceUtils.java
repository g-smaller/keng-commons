package com.keng.common.util;

public class ResourceUtils {

    public static final String JAR_URL_PREFIX               = "jar:";

    public static final String FILE_URL_PREFIX              = "file:";

    public static final String WAR_URL_PREFIX               = "war:";

    public static final String JAR_PROTOCOL                 = "jar";

    public static final String FILE_PROTOCOL                = "file";

    public static final String JAR_URL_SEPARATOR            = "!/";

    public static final String JAR_FILE_EXTENSION           = ".jar";

    public static final String CLASS_FILE_EXTENSION         = ".class";

    public static final String JAVA_FILE_ExTENSION          = ".java";

    public static String clearJarProtocol(String url) {
        if (StringUtil.isNotEmpty(url) && url.startsWith(JAR_URL_PREFIX)) {
            return url.substring(JAR_URL_PREFIX.length(), url.length());
        }
        return url;
    }

    public static String clearFileProtocol(String url) {
        if (StringUtil.isNotEmpty(url) && url.startsWith(FILE_URL_PREFIX)) {
            return url.substring(FILE_URL_PREFIX.length(), url.length());
        }
        return url;
    }

    public static String clearJarAndFileProtocol(String url) {
        return clearFileProtocol(clearJarProtocol(url));
    }

    public static String joinJarProtocol(String url) {
        if (StringUtil.isNotEmpty(url)) {
            String first = url.substring(0, 1);
            if (!FileUtil.getFileSeparator().equals(first)) {
                url = FileUtil.getFileSeparator() + url;
            }
            return JAR_URL_PREFIX + FILE_URL_PREFIX + url;
        }
        return url;
    }

}
