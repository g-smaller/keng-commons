package com.keng.common.dependency;

import com.keng.common.maven.Settings;
import com.keng.common.util.FileUtil;
import com.keng.common.util.ResourceUtils;
import com.keng.common.util.StringUtil;

import java.io.IOException;

public class URLResolver extends DependencyResolver {

    public static final URLResolver RESOLVER = new URLResolver();

    @Override
    protected Dependency resolve(String filePath) throws IOException {
        if (StringUtil.isNotEmpty(filePath)) {
            String fullPath = clearProtocol(filePath);
            if (fullPath.startsWith(Settings.getLocalRepository())) {
                /**
                 * 例如url： /Users/xx/.m2/repository/org/springframework/spring-beans/4.2.3.RELEASE/spring-beans-4.2.3.RELEASE.jar!/MENIFEST.MF
                 *
                 * jarPath = /org/springframework/spring-beans/4.2.3.RELEASE/spring-beans-4.2.3.RELEASE.jar!/MENIFEST.MF
                 */
                String jarPath = fullPath.substring(Settings.getLocalRepository().length(), fullPath.length());
                /**
                 * 截取成： /org/springframework/spring-beans/4.2.3.RELEASE/spring-beans-4.2.3.RELEASE.jar
                 */
                int i = jarPath.indexOf(ResourceUtils.JAR_URL_SEPARATOR);
                if (i > 0) {
                    jarPath = jarPath.substring(0, i);
                }


                String[] strings = StringUtil.splitFolder(jarPath);

                /**
                 * 截取成: /spring-beans-4.2.3.RELEASE.jar
                 */
                String artifactIdAndVersion = strings[strings.length - 1];
                i = artifactIdAndVersion.lastIndexOf("-");

                String artifactId = artifactIdAndVersion;
                String version = "";
                /**
                 * 如果有版本： 截取成 spring-beans、4.2.3.RELEASE
                 */
                if (i > 0) {
                    artifactId = artifactIdAndVersion.substring(0, i);
                    version = artifactIdAndVersion.substring(i + 1);
                    version = version.substring(0, version.length() - ResourceUtils.JAR_FILE_EXTENSION.length());
                }

                /**
                 * 截取：groupId
                 */
                String groupId = groupId(jarPath, artifactId, version);
                return Dependency.of(StringUtil.replaceFolderToPackage(groupId), artifactId, version);

            }
        }
        return null;
    }

    private static String clearProtocol(String path) {
        if (StringUtil.isNotEmpty(path)) {
            return ResourceUtils.clearJarAndFileProtocol(path);
        }
        return path;
    }

    private static String groupId(String jarPath, String artifactId, String version) {
        String fileSeparator = FileUtil.getFileSeparator();
        String artifactIdAndVersion = String.format("%s%s%s%s%s%s-%s%s", fileSeparator, artifactId, fileSeparator,
                version, fileSeparator, artifactId, version, ResourceUtils.JAR_FILE_EXTENSION);
        return jarPath.replace(artifactIdAndVersion, "");
    }
}
