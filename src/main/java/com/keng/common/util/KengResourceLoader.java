package com.keng.common.util;

import com.keng.common.resolver.ResourceResolver;
import com.keng.common.dependency.Dependency;

import java.util.ArrayList;
import java.util.List;

public class KengResourceLoader {

    private List<ResourceResolver> resolvers = new ArrayList<ResourceResolver>();

    public List<Dependency> load() {
        return null;
    }

    public void register(ResourceResolver resolver) {
        if (resolver != null) {
            resolvers.add(resolver);
        }
    }

}
