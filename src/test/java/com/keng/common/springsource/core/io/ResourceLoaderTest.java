package com.keng.common.springsource.core.io;

import org.junit.Test;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.util.AntPathMatcher;

import java.io.IOException;
import java.util.Map;

public class ResourceLoaderTest {

    @Test
    public void resourceTest() throws IOException {
        PathMatchingResourcePatternResolver loader = new PathMatchingResourcePatternResolver();
        Resource resource = loader.getResource(ResourceLoader.CLASSPATH_URL_PREFIX + "config-test.properties");
        System.out.println(resource.getURL().toString());

        Resource[] resources = loader.getResources(ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX + "config*.properties");
        for (Resource resource1: resources) {
            System.out.println(resource1.getURL().toString());
        }
    }

    @Test
    public void matcherTest() {
        AntPathMatcher matcher = new AntPathMatcher();

        Map<String, String> variables = matcher.extractUriTemplateVariables("{username}", "guoguo");
        variables.forEach((k, v) -> {
            System.out.println(k + " - " + v);
        });

    }

}
