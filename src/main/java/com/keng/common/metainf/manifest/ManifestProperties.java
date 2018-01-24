package com.keng.common.metainf.manifest;

import com.keng.common.maven.Dependency;

import java.util.jar.Manifest;

public class ManifestProperties {

    private String url;
    private Dependency dependency;
    private Manifest manifest;

    public ManifestProperties() {
    }

    public ManifestProperties(String url, Dependency dependency, Manifest manifest) {
        this.url = url;
        this.dependency = dependency;
        this.manifest = manifest;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Dependency getDependency() {
        return dependency;
    }

    public void setDependency(Dependency dependency) {
        this.dependency = dependency;
    }

    public Manifest getManifest() {
        return manifest;
    }

    public void setManifest(Manifest manifest) {
        this.manifest = manifest;
    }

    @Override
    public String toString() {
        return String.format("{url: %s, dependency: %s}", url, dependency);
    }
}
