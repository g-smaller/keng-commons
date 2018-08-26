package com.keng.common;

public class ProjectContext {

    public static String getProjectRoot(){
        return System.getProperty(Words.Propeties.USER_HOME);
    }

    /**
     * {@link ProjectContext#getCurrentDir()}
     * @return
     */
    public static String getUserDir() {
        return System.getProperty(Words.Propeties.USER_DIR);
    }

    /**
     * {@link ProjectContext#getUserDir()}
     * @return
     */
    public static String getCurrentDir() {
        return System.getenv(Words.Environment.PWD);
    }

    public static String getUserHome(){
        return System.getProperty(Words.Propeties.USER_HOME);
    }

    public static String getHome() {
        return System.getenv(Words.Environment.HOME);
    }

    public static String getJreHome() {
        return System.getProperty(Words.Propeties.JAVA_HOME);
    }

    public static String getJaveHome() {
        return System.getenv(Words.Environment.JAVA_HOME);
    }

    public static String getJavaClassPath() {
        return System.getProperty(Words.Propeties.JAVA_CLASS_PATH);
    }
}
