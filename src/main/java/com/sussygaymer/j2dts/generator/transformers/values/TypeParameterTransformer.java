package com.sussygaymer.j2dts.generator.transformers.values;

import com.sussygaymer.j2dts.generator.TSBuilder;
import com.sussygaymer.j2dts.generator.transformers.EntityTransformer;
import com.sussygaymer.j2dts.generator.transformers.Transformer;
import com.sussygaymer.j2dts.generator.transformers.classes.ClassMethodTransformer;

import lombok.Getter;
import lombok.Setter;

@Getter
public class TypeParameterTransformer extends Transformer {
    @Setter
    private ValueTransformer extend = null;
    private EntityTransformer entityParent = null;
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
