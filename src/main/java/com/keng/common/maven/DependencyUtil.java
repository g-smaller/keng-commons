package com.keng.common.maven;

import com.keng.common.Words;
import com.keng.common.dependency.Dependency;
import com.keng.common.dependency.DependencyAttr;
import com.keng.common.util.StringUtil;

import java.io.IOException;
import java.net.URL;
import java.util.Properties;

public class DependencyUtil {

    public static Dependency pomPropertiesToDependency(String file) throws IOException {
        if (StringUtil.isNotEmpty(file)) {
            URL url = new URL(file);
            Properties properties = new Properties();
            properties.load(url.openStream());
            String groupId = properties.getProperty(DependencyAttr.GROUP_ID, Words.EMPTY);
            String artifactId = properties.getProperty(DependencyAttr.ARTIFACT_ID, Words.EMPTY);
            String version = properties.getProperty(DependencyAttr.VERSION, Words.EMPTY);
            if (StringUtil.isNotEmpty(groupId, artifactId, version)) {
                return Dependency.of(groupId, artifactId, version);
            }
        }
        return null;
    }

}
