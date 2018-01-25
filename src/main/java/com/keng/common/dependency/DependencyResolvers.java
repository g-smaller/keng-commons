package com.keng.common.dependency;

import com.keng.common.ProjectContext;
import com.keng.common.Words;
import com.keng.common.resolver.MetaInfResolver;
import com.keng.common.util.ClassUtil;
import com.keng.common.util.StringUtil;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

@Slf4j
public class DependencyResolvers {

    private static final String[] DEFAULT_IFNORE_DIR = {"jar:file:/System/Library/Java/Extensions", "IntelliJ%20IDEA.app", "jar:file:" + ProjectContext.getJaveHome()};

    private List<MetaInfResolver<Dependency>> resolvers = new ArrayList<MetaInfResolver<Dependency>>();
    private List<String> ignoreDir = new ArrayList<String>();

    private ClassLoader classLoader;

    public DependencyResolvers() {
        this(null);
    }

    public DependencyResolvers(ClassLoader classLoader) {
        this.classLoader = classLoader;
        resolvers.add(MavenResolver.RESOLVER);
        resolvers.add(ManifestResolver.RESOLVER);
        resolvers.add(URLResolver.RESOLVER);

        for (String dir: DEFAULT_IFNORE_DIR) {
            ignoreDir.add(dir);
        }
    }

    public void setClassLoader(ClassLoader classLoader) {
        if (classLoader != null) {
            this.classLoader = classLoader;
        }
    }

    public List<Dependency> resolve() {
        ClassLoader loader = this.classLoader == null ? ClassUtil.getDefaultClassLoader() : classLoader;

        if (loader != null) {

            try {
                List<Dependency> dependencies = new ArrayList<Dependency>();
                Enumeration<URL> resources = loader.getResources(Words.META_INF);
                if (resources != null) {
                    while (resources.hasMoreElements()) {
                        URL url = resources.nextElement();
                        if (ignoreDir(url)) {
                            continue;
                        }

                        for (MetaInfResolver<Dependency> resolver: resolvers) {
                            Dependency dependency = resolver.resolve(url);
                            if (dependency == null) {
                                continue;
                            }
                            dependencies.add(dependency);
                            break;
                        }
                    }
                }
                return dependencies;
            } catch (IOException e) {
                log.error("加载资源文件中的{}目录失败", Words.META_INF);
            }
        }
        return Collections.EMPTY_LIST;
    }

    public void register(MetaInfResolver<Dependency> resolver) {
        if (resolvers == null) {
            resolvers = new ArrayList<MetaInfResolver<Dependency>>();
        }
        resolvers.add(resolver);
    }

    public void registerIgnoreDir(String str) {
        if (StringUtil.isNotEmpty(str)) {

        }
    }

    private boolean ignoreDir(URL url) {
        if (url != null) {
            String s = url.toString();
            for (String dir: ignoreDir) {
                if (s.startsWith(dir)) {
                    return true;
                }
                if (s.contains(dir)) {
                    return true;
                }
            }
            return false;
        }
        return true;
    }

}
