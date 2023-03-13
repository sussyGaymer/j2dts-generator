package com.sussygaymer.j2dts.generator.transformers.enums;

import com.sussygaymer.j2dts.generator.TSBuilder;
import com.sussygaymer.j2dts.generator.transformers.Transformer;

import lombok.Getter;

@Getter
public class EnumValueTransformer extends Transformer {
    private ConstantExpressionTransformer value;

    public EnumValueTransformer(String name, ConstantExpressionTransformer value) {
        this.name = name;
        this.value = value;
    }

    public String serialize(int level) {
        TSBuilder builder = new TSBuilder(level);

        builder.indent();

        builder.add(this.name);
        builder.add(" = ");
        builder.add(this.value.serialize());
        builder.add(",\n");

        return builder.getString();
    }
}
