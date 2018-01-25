package com.keng.common.util;

import com.keng.common.Words;

public final class FileUtil {

    public static String getFileSeparator() {
        return System.getProperty(Words.Propeties.FILE_SEPARATOR);
    }

    public static String getExtension(String file){
        return getExtension(file, false);
    }

    public static String getExtension(String file, boolean isDots){
        if (StringUtil.isNotEmpty(file)) {
            int i = file.lastIndexOf(".");
            if (i > 0) {
                i = isDots ? i : i + 1;
                return file.substring(i, file.length());
            }
        }
        return Words.EMPTY;
    }

}
