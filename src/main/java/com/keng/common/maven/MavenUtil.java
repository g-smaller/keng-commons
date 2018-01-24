package com.keng.common.maven;

import com.keng.common.ProjectContext;
import com.keng.common.util.FileUtil;
import com.keng.common.util.ResourceUtils;
import com.keng.common.util.StringUtil;

import java.net.URL;

public class MavenUtil {
    private static final Settings settings;
    public static final String M2_DIR           = "/.m2";
    public static final String M2_SETTINGS      = M2_DIR + "/settings.xml";
    public static final String M2_REPOSITORY    = M2_DIR + "/repository";


    static {
        settings = loadSettings();
    }

    public static Settings loadSettings() {
        Settings settings = new Settings();
        settings.setLocalRepository(ProjectContext.getUserHome() + M2_REPOSITORY);
        return settings;
    }

    public static String getLocalRepository() {
        return settings.getLocalRepository();
    }

    /**
     * 根据Jar 文件路径解析成
     *      groupId
     *      artifactId
     *      version
     *
     * @param url
     * @return
     */
    public static Dependency resolve(URL url) {
        if (url != null) {
            String s = url.toString();
            String fullPath = clearProtocol(s);
            if (fullPath.startsWith(getLocalRepository())) {
                /**
                 * 例如url： /Users/xx/.m2/repository/org/springframework/spring-beans/4.2.3.RELEASE/spring-beans-4.2.3.RELEASE.jar!/MENIFEST.MF
                 *
                 * jarPath = /org/springframework/spring-beans/4.2.3.RELEASE/spring-beans-4.2.3.RELEASE.jar!/MENIFEST.MF
                 */
                String jarPath = fullPath.substring(getLocalRepository().length(), fullPath.length());
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

    public static String clearProtocol(String path) {
        if (StringUtil.isNotEmpty(path)) {
            String s = ResourceUtils.clearJarProtocol(path);
            return ResourceUtils.clearFileProtocol(s);
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
