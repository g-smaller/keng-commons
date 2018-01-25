package com.keng.common.dependency;

import com.keng.common.util.ClassUtil;
import com.keng.common.util.ResourceUtils;
import org.junit.Test;

import java.io.File;
import java.io.FileFilter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.*;
import java.util.jar.Attributes;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.jar.Manifest;

import static org.junit.Assert.*;

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
        JarFile file = new JarFile("/Users/guoguo/.m2/repository/ch/qos/logback/logback-core/1.1.11/logback-core-1.1.11.jar");
        Enumeration<JarEntry> entries = file.entries();
        while (entries.hasMoreElements()) {
            JarEntry entry = entries.nextElement();
            String name = entry.getName();
            if (name.endsWith("pom.properties")) {
                String s = "jar:file:/Users/guoguo/.m2/repository/ch/qos/logback/logback-core/1.1.11/logback-core-1.1.11.jar" + ResourceUtils.JAR_URL_SEPARATOR + entry.toString();
                URL url = new URL(s);
                Properties properties = new Properties();
                properties.load(url.openStream());
                printMap(properties);
                break;
            }
        }

        System.out.println();
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
}