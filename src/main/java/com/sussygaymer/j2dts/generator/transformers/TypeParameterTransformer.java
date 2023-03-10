package com.sussygaymer.j2dts.generator.transformers;

import com.sussygaymer.j2dts.generator.TSBuilder;
import com.sussygaymer.j2dts.generator.transformers.classes.ClassMethodTransformer;

import lombok.Getter;
import lombok.Setter;

public class TypeParameterTransformer extends Transformer {
    @Getter @Setter
    private ValueTransformer extend = null;
    @Getter
    private EntityTransformer entityParent = null;
    @Getter
    private ClassMethodTransformer methodParent = null;
    
    public TypeParameterTransformer(String name, EntityTransformer parent) {
        this.name = name;
        this.entityParent = parent;

        parent.getTypeParameters().add(this);
    }

    public TypeParameterTransformer(String name, ClassMethodTransformer parent) {
        this.name = name;
        this.methodParent = parent;

        parent.getTypeParameters().add(this);
    }

    @Override
    public String serialize(int level) {
        TSBuilder builder = new TSBuilder(level);

        builder.add(this.name);

        if (this.extend != null) {
            builder.add(" extends ");
            builder.add(this.extend.serialize());
        }

        return builder.getString();
    }
    
}
