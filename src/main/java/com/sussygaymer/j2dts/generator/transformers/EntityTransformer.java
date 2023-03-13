package com.sussygaymer.j2dts.generator.transformers;

import java.util.ArrayList;

import com.sussygaymer.j2dts.generator.transformers.values.TypeParameterTransformer;

import lombok.Getter;

@Getter
public abstract class EntityTransformer extends Transformer {
    protected NamespaceTransformer namespace;
    protected ArrayList<TypeParameterTransformer> typeParameters = new ArrayList<TypeParameterTransformer>();

    public String getFullName() {
        return this.namespace.name + "." + this.name;
    }
}
