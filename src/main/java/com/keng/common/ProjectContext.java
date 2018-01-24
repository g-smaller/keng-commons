package com.keng.common;

public class ProjectContext {

    public static String getProjectRoot(){
        return System.getProperty(Words.Propeties.USER_HOME);
    }

    public static String getCurrentDir() {
        return System.getenv(Words.Environment.PWD);
    }

    public static String getUserHome(){
        return System.getProperty(Words.Propeties.USER_HOME);
    }

    public static String getHome() {
        return System.getenv(Words.Environment.HOME);
    }
}
