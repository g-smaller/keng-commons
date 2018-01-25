package com.keng.common.analyse;

import com.keng.common.Words;
import com.keng.common.dependency.Dependency;
import com.keng.common.maven.DependencyUtil;
import com.keng.common.util.ClassUtil;
import com.keng.common.util.FileUtil;
import com.keng.common.util.ResourceUtils;

import java.net.URL;
import java.util.*;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class DependencyAnalyzer implements KengAnalyzable {
    @Override
    public void analyze() throws Exception {
        Map<Dependency, List<ClassDetail>> map = new HashMap<Dependency, List<ClassDetail>>();
        Enumeration<URL> resources = ClassUtil.getDefaultClassLoader().getResources(Words.META_INF);
        while (resources.hasMoreElements()) {
            String filePath = resources.nextElement().toString();
            int i = filePath.toString().indexOf(ResourceUtils.JAR_URL_SEPARATOR);
            if (i > 0) {
                filePath = filePath.substring(0, i);
            }
            String jarProtocolPath = filePath.substring(0, i);
            String jar = ResourceUtils.clearJarAndFileProtocol(filePath);
            if (jar.endsWith(ResourceUtils.JAR_FILE_EXTENSION)) {
                JarFile jarFile = new JarFile(jar);
                Enumeration<JarEntry> entries = jarFile.entries();
                List<ClassDetail> classDetails = new ArrayList<>();
                Dependency dependency = null;
                while (entries.hasMoreElements()) {
                    JarEntry entry = entries.nextElement();
                    String name = entry.getName();
                    if (name.endsWith("pom.properties")) {
                        String s = jarProtocolPath + ResourceUtils.JAR_URL_SEPARATOR + name.toString();
                        dependency = DependencyUtil.pomPropertiesToDependency(s);
                    }
                    if (Words.Extension.CLASS.eq(FileUtil.getExtension(name, true))) {
                        String s = jarProtocolPath + ResourceUtils.JAR_URL_SEPARATOR + name.toString();
                        classDetails.add(ClassDetail.of(s));
                    }
                }

                if (dependency != null) {
                    List<ClassDetail> oldClassDetails = map.get(dependency);
                    if (oldClassDetails == null) {
                        oldClassDetails = classDetails;
                    }else {
                        oldClassDetails.addAll(classDetails);
                    }
                    map.put(dependency, oldClassDetails);
                }
            }
        }
    }
}
