package com.keng.common.analyse;

import com.keng.common.dependency.Dependency;

public class ClassDetail extends Dependency {

    private String clazz;

    public static ClassDetail of(String clazz) {
        ClassDetail detail = new ClassDetail();
        return detail;
    }

    public void setDependency(Dependency dependency) {
        if (dependency != null) {
            this.setGroupId(dependency.getGroupId());
            this.setArtifactId(dependency.getArtifactId());
            this.setVersion(dependency.getVersion());
        }
    }

    public String getClazz() {
        return clazz;
    }

    public void setClazz(String clazz) {
        this.clazz = clazz;
    }
}
