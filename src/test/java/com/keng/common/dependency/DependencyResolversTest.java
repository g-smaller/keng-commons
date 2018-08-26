package com.keng.common.dependency;

import com.keng.common.analyse.DependencyAnalyzer;
import com.keng.common.util.ClassUtil;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.slf4j.impl.StaticLoggerBinder;
import org.slf4j.impl.StaticMDCBinder;

import java.io.IOException;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Method;
import java.net.URISyntaxException;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class DependencyResolversTest {

    @Test
    public void resolveTest() {
        DependencyResolvers dependencyResolvers = new DependencyResolvers();
        List<Dependency> resolve = dependencyResolvers.resolve();
        resolve.forEach(r -> {
            System.out.println(r);
        });
        System.out.println("-----------------------");
    }

    @Test
    public void urlTest() throws IOException, URISyntaxException {
        List<String> strings = ClassUtil.currentProjectSource();
        strings.forEach(System.out::println);

        String s = strings.get(3);
        JarFile jarFile = new JarFile(s);
        Enumeration<JarEntry> entries = jarFile.entries();
        while (entries.hasMoreElements()) {
            System.out.println(entries.nextElement().toString());
        }
    }

    @Test
    public void methodTest() throws NoSuchMethodException {
        Class<StaticLoggerBinder> staticLoggerBinderClass = StaticLoggerBinder.class;
        Method getMDCA = staticLoggerBinderClass.getMethod("getLoggerFactoryClassStr");
        System.out.println(getMDCA.getReturnType());
    }

    @Test
    public void analyzerTest() throws Exception {
        DependencyAnalyzer analyzable = new DependencyAnalyzer();

        analyzable.analyze(map -> {
            map.forEach((k, v) -> {
                if (v.size() > 1) {

                }
                System.out.println(k + " - " + StringUtils.join(v));
            });
        });

    }

    @Test
    public void envTest() {
        Properties properties = System.getProperties();
        printMap(properties);
        System.out.println("-----------------------");
        Map<String, String> getenv = System.getenv();
        printMap(getenv);
    }

    private void printMap(Properties properties) {
        properties.forEach((k, v) -> {
            System.out.println(k + " - " + v);
        });
    }

    public void printMap(Map<String, String> map) {
        map.forEach((k, v) -> {
            System.out.println(k + " - " + v);
        });
    }

    @Test
    public void annotionTest() {

    }
}