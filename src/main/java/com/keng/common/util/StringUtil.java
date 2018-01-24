package com.keng.common.util;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;

public class StringUtil {

    private static final String FOLDER_SEPARATOR = FileUtil.getFileSeparator();

    private static final String WINDOWS_FOLDER_SEPARATOR = "\\\\";

    private static final String CURRENT_PATH = ".";

    private static final char EXTENSION_SEPARATOR = CURRENT_PATH.charAt(0);

    private static final String PACKAGE_SEPARATOR = CURRENT_PATH;

    public static boolean isEmpty(String str) {
        return str == null || str.equals("") || str.length() == 0;
    }

    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }

    public static String replaceFolderToPackage(String str) {
        if (isNotEmpty(str)) {
            String first = str.substring(0, 1);
            if (FileUtil.getFileSeparator().equals(first)) {
                str = str.substring(1, str.length());
            }
            return str.replaceAll(FOLDER_SEPARATOR, PACKAGE_SEPARATOR).replaceAll(WINDOWS_FOLDER_SEPARATOR, PACKAGE_SEPARATOR);
        }
        return str;
    }

    public static String[] splitFolder(String str) {
        if (isNotEmpty(str)) {
            return str.split(FileUtil.getFileSeparator());
        }
        return null;
    }

    public static String toString(byte[] value, int offset, int length, String encoding) {
        Charset charset = CharsetUtil.getCharset(encoding);
        return charset.decode(ByteBuffer.wrap(value, offset, length)).toString();
    }
}
