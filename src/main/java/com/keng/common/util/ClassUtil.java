package com.keng.common.util;

import com.keng.common.ProjectContext;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.util.ResourceUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ClassUtil {

    private static final String[] DEFAULT_IGNORE_DIR = {"/System/Library/Java/Extensions", ProjectContext.getJaveHome()};

    public static ClassLoader getDefaultClassLoader() {
        ClassLoader cl = null;

        try {
            cl = Thread.currentThread().getContextClassLoader();
        } catch (Throwable var3) {
            ;
        }

        if (cl == null) {
            cl = ClassUtil.class.getClassLoader();
            if (cl == null) {
                try {
                    cl = ClassLoader.getSystemClassLoader();
                } catch (Throwable var2) {
                    ;
                }
            }
        }

        return cl;
    }

    public static List<String> currentProjectSource() {
        return currentProjectSource("lib/");
    }

    public static List<String> currentProjectSource(String... ignores) {
        String javaClassPath = ProjectContext.getJavaClassPath();
        if (StringUtil.isNotEmpty(javaClassPath)) {
            List<String> results = new ArrayList<String>(50);
            String[] classes = javaClassPath.split(":");
            for (String clazz: classes) {
                if (isIgnore(clazz, ignores)) {
                    continue;
                }
                if (!clazz.endsWith(ResourceUtils.JAR_FILE_EXTENSION)){
                    continue;
                }
                results.add(clazz);
            }
            return results;
        }
        return Collections.EMPTY_LIST;
    }

    private static boolean isIgnore(String clazz, String... ignores) {
        if (StringUtil.isNotEmpty(clazz)) {
            for (String ignore : DEFAULT_IGNORE_DIR) {
                if (clazz.startsWith(ignore)) {
                    return true;
                }
            }

            if (ignores != null && ignores.length > 0) {
                for (String ignore: ignores) {
                    if (clazz.contains(ignore)) {
                        return true;
                    }
                }
            }
            return false;
        }
        return true;
    }

}
