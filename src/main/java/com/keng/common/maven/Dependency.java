package com.keng.common.maven;

public class Dependency {

    private String groupId;
    private String artifactId;
    private String version;

    public Dependency() {
    }

    public static Dependency of(String groupId, String artifactId, String version) {
        return new Dependency(groupId, artifactId, version);
    }

    public Dependency(String groupId, String artifactId, String version) {
        this.groupId = groupId;
        this.artifactId = artifactId;
        this.version = version;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getArtifactId() {
        return artifactId;
    }

    public void setArtifactId(String artifactId) {
        this.artifactId = artifactId;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return String.format("{groupId: %s, artifactId: %s, version: %s}", groupId, artifactId, version);
    }
}
