package com.keng.common.dependency;

import com.keng.common.Words;
import com.keng.common.util.ResourceUtils;
import com.keng.common.util.StringUtil;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;
import java.util.Properties;
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
                        URL url = new URL(s);
                        Properties properties = new Properties();
                        properties.load(url.openStream());
                        String groupId = properties.getProperty(DependencyAttr.GROUP_ID, Words.EMPTY);
                        String artifactId = properties.getProperty(DependencyAttr.ARTIFACT_ID, Words.EMPTY);
                        String version = properties.getProperty(DependencyAttr.VERSION, Words.EMPTY);
                        if (isNotEmpty(groupId, artifactId, version)) {
                            return Dependency.of(groupId, artifactId, version);
                        }
                        break;
                    }
                }
            }
        }
        return null;
    }

}