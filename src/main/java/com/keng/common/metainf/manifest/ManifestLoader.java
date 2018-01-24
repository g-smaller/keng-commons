package com.keng.common.metainf.manifest;

import com.keng.common.maven.Dependency;
import com.keng.common.maven.MavenUtil;
import com.keng.common.util.ClassUtil;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.Manifest;

public class ManifestLoader {

    public static final String MANIFEST_RESOURCE_LOCATION = "META-INF/MANIFEST.MF";

    public static List<ManifestProperties> loadManifest() {
        return loadManifest(null);
    }

    public static List<ManifestProperties> loadManifest(ClassLoader defaultClassLoader) {

        ClassLoader classLoader = defaultClassLoader == null ? ClassUtil.getDefaultClassLoader() : defaultClassLoader;
        try {
            Enumeration<URL> urls = classLoader.getResources(MANIFEST_RESOURCE_LOCATION);
            List<ManifestProperties> manifests = new ArrayList<ManifestProperties>();
            while (urls.hasMoreElements()) {
                URL url = urls.nextElement();
                Dependency dependency = MavenUtil.resolve(url);
                if (dependency == null) {
                    continue;
                }
                String path = url.toString();
                Manifest manifest = new Manifest(url.openStream());
                manifests.add(new ManifestProperties(path, dependency, manifest));
            }
            return manifests;
        } catch (IOException e) {
            throw new IllegalArgumentException( "MANIFEST from location [" + MANIFEST_RESOURCE_LOCATION + "]", e);
        }
    }

    public static void main(String[] args) {
        List<ManifestProperties> manifestProperties = ManifestLoader.loadManifest();
        manifestProperties.forEach(manifestPropertie -> {
            System.out.println(manifestPropertie);
        });

    }

}
