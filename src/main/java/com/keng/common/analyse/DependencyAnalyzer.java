package com.keng.common.analyse;

import com.keng.common.Words;
import com.keng.common.util.ClassUtil;
import com.keng.common.util.FileUtil;
import com.keng.common.util.ResourceUtils;
import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.function.Consumer;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

@Slf4j
public class DependencyAnalyzer implements KengAnalyzable<Map<String, List<String>>> {


    @Override
    public void analyze(Consumer<Map<String, List<String>>> consumer) throws Exception {
        Map<String, List<String>> map = new HashMap<>(100);
        List<String> classes = ClassUtil.currentProjectSource();
        for (String clazz: classes) {
            if (clazz.endsWith(ResourceUtils.JAR_FILE_EXTENSION)) {
                JarFile jarFile = new JarFile(clazz);
                Enumeration<JarEntry> entries = jarFile.entries();
                while (entries.hasMoreElements()) {
                    JarEntry entry = entries.nextElement();
                    String name = entry.getName();
                    if (name.endsWith("pom.properties")) {
                        //dependency = DependencyUtil.pomPropertiesToDependency(ResourceUtils.joinJarProtocol(clazz + ResourceUtils.JAR_URL_SEPARATOR + name));
                    }
                    if (Words.Extension.CLASS.eq(FileUtil.getExtension(name, true))) {
                        //String s = jarProtocolPath + ResourceUtils.JAR_URL_SEPARATOR + name.toString();
                        List<String> classJar = map.get(name);
                        if (classJar == null) {
                            classJar = new ArrayList<String>(2);
                        }
                        classJar.add(clazz);
                        map.put(name, classJar);
                    }
                }
            }
        }
        consumer.accept(map);
    }
}
