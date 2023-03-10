package com.sussygaymer.j2dts.generator.transformers.classes;

import com.sussygaymer.j2dts.generator.TSBuilder;
import com.sussygaymer.j2dts.generator.transformers.Transformer;
import com.sussygaymer.j2dts.generator.transformers.ValueTransformer;
import com.sussygaymer.j2dts.generator.transformers.Visibility;

import lombok.Getter;
import lombok.Setter;

public class ClassPropertyTransformer extends Transformer {
    @Getter
    private ClassTransformer parent;
    @Getter
    private ValueTransformer type;
    @Getter @Setter
    private boolean isStatic = false;
    @Getter @Setter
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
