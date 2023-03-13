package com.sussygaymer.j2dts.generator.transformers.classes;

import com.sussygaymer.j2dts.generator.TSBuilder;
import com.sussygaymer.j2dts.generator.transformers.Transformer;
import com.sussygaymer.j2dts.generator.transformers.values.ValueTransformer;

import lombok.Getter;
import lombok.Setter;

@Getter
public class ClassPropertyTransformer extends Transformer {
    private ClassTransformer parent;
    private ValueTransformer type;
    @Setter
    private boolean isStatic = false;
    private Visibility visibility = null;

    public ClassPropertyTransformer(String name, ValueTransformer type, ClassTransformer parent) {
        this.name = name;
        this.parent = parent;
        this.type = type;

        parent.addProperty(this);
    }

    @Override
    public String serialize(int level) {
        TSBuilder builder = new TSBuilder(level);

        builder.indent();

        if (this.visibility != null)
            builder.add(this.visibility.toString().toLowerCase() + " ");

        if (this.isStatic)
            builder.add("static ");

        builder.add(this.name);

        builder.add(": ");

        builder.add(this.type.serialize());

        builder.add(";\n");

        return builder.getString();
    }
}

