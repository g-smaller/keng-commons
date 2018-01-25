package com.keng.common.dependency;

import com.keng.common.util.ClassUtil;
import com.keng.common.util.FileUtil;
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
        Enumeration<URL> resources = ClassLoader.getSystemClassLoader().getResources("");
        while (resources.hasMoreElements()) {
            System.out.println(resources.nextElement().toString());
        }

        System.out.println(FileUtil.getExtension("eff.jj.img", true));
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