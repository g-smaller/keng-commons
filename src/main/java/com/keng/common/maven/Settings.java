package com.keng.common.maven;

import com.keng.common.ProjectContext;

public class Settings {

    public static final String M2_DIR           = "/.m2";
    public static final String M2_SETTINGS      = M2_DIR + "/settings.xml";
    public static final String M2_REPOSITORY    = M2_DIR + "/repository";

    public static final String LOCAL_REPOSITORY = ProjectContext.getUserHome() + M2_REPOSITORY;

    public static String getLocalRepository() {
        return LOCAL_REPOSITORY;
    }
}
