package com.keng.common.dependency;

import com.keng.common.metainf.manifest.ManifestAttr;
import com.keng.common.util.StringUtil;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.URL;
import java.util.jar.Manifest;

@Slf4j
public class ManifestResolver extends DependencyResolver {

    public static final ManifestResolver RESOLVER = new ManifestResolver();

    @Override
    public Dependency resolve(String filePath) throws IOException{
        if (StringUtil.isNotEmpty(filePath)) {
            URL url = new URL(filePath);
            if (url != null) {
                Manifest manifest = new Manifest(url.openStream());
                if (manifest != null) {
                    String groupId = manifest.getMainAttributes().getValue(ManifestAttr.IMPLEMENTATION_VENDOR_ID);
                    String artifactId = manifest.getMainAttributes().getValue(ManifestAttr.IMPLEMENTATION_TITLE);
                    String version = manifest.getMainAttributes().getValue(ManifestAttr.IMPLEMENTATION_VERSION);
                    if (isNotEmpty(groupId, artifactId, version)) {
                        return Dependency.of(groupId.toLowerCase(), artifactId.toLowerCase(), version);
                    }
                }
            }
        }
        return null;
    }

    @Override
    public String metaInfFile() {
        return "MANIFEST.MF";
    }
}
