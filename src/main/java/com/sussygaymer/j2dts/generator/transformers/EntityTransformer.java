package com.sussygaymer.j2dts.generator.transformers;

import java.util.ArrayList;

import lombok.Getter;

public abstract class EntityTransformer extends Transformer {
    @Getter
    protected NamespaceTransformer namespace;

    @Getter
    protected ArrayList<TypeParameterTransformer> typeParameters = new ArrayList<TypeParameterTransformer>();

    public String getFullName() {
        return this.namespace.name + "." + this.name;
    }
}
