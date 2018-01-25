package com.keng.common.dependency;

import com.keng.common.maven.DependencyUtil;
import com.keng.common.util.ResourceUtils;
import com.keng.common.util.StringUtil;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

@Slf4j
public class MavenResolver extends DependencyResolver {

    public static final MavenResolver RESOLVER = new MavenResolver();

    @Override
    public String metaInfFile() {
        return "pom.properties";
    }

    @Override
    protected Dependency resolve(String filePath) throws IOException {
        if (StringUtil.isNotEmpty(filePath)) {
            int i = filePath.indexOf(ResourceUtils.JAR_URL_SEPARATOR);
            if (i > 0) {
                filePath = filePath.substring(0, i);
            }
            String jar = ResourceUtils.clearJarAndFileProtocol(filePath);
            String jarProtocolPath = filePath.substring(0, i);

            if (jar.endsWith(ResourceUtils.JAR_FILE_EXTENSION)) {
                JarFile jarFile = new JarFile(jar);
                Enumeration<JarEntry> entries = jarFile.entries();
                while (entries.hasMoreElements()) {
                    JarEntry entry = entries.nextElement();
                    String name = entry.getName();
                    if (name.endsWith(metaInfFile())) {
                        String s = jarProtocolPath + ResourceUtils.JAR_URL_SEPARATOR + name.toString();
                        return DependencyUtil.pomPropertiesToDependency(s);
                    }
                }
            }
        }
        return null;
    }

}