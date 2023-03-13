package com.sussygaymer.j2dts.generator.transformers.interfaces;

import com.sussygaymer.j2dts.generator.TSBuilder;
import com.sussygaymer.j2dts.generator.transformers.Transformer;
import com.sussygaymer.j2dts.generator.transformers.values.ValueTransformer;

import lombok.Getter;

@Getter
public class InterfacePropertyTransformer extends Transformer {
    private InterfaceTransformer parent;
    private ValueTransformer type;

    public InterfacePropertyTransformer(String name, ValueTransformer type, InterfaceTransformer parent) {
        this.name = name;
        this.parent = parent;
        this.type = type;
        
        parent.addProperty(this);
    }

    @Override
    public String serialize(int level) {
        TSBuilder builder = new TSBuilder(level);

        builder.indent();

        builder.add(this.name);

        builder.add(": ");

        builder.add(this.type.serialize());

        builder.add(";\n");

        return builder.getString();
    }
}