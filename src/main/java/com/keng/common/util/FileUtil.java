package com.keng.common.util;

import com.keng.common.Words;

public final class FileUtil {

    public static String getFileSeparator() {
        return System.getProperty(Words.Propeties.FILE_SEPARATOR);
    }

}
