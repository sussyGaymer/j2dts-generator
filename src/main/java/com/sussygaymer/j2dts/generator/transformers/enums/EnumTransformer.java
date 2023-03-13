package com.sussygaymer.j2dts.generator.transformers.enums;

import java.util.ArrayList;

import com.sussygaymer.j2dts.generator.TSBuilder;
import com.sussygaymer.j2dts.generator.transformers.EntityTransformer;
import com.sussygaymer.j2dts.generator.transformers.NamespaceTransformer;

import lombok.Getter;

@Getter
public class EnumTransformer extends EntityTransformer {
    private ArrayList<EnumValueTransformer> values = new ArrayList<EnumValueTransformer>();

    public EnumTransformer(String name, NamespaceTransformer namespace) {
        this.name = name;
        this.namespace = namespace;

        namespace.addEnum(this);
    }

    public void addValue(EnumValueTransformer value) {
        this.values.add(value);
    }

    @Override
    public String serialize(int level) {
        TSBuilder builder = new TSBuilder(level);

        builder.indent();

        builder.add("enum ");

        builder.add(this.name);

        builder.add(" {\n");

        this.values.forEach((value) -> {
            builder.add(value.serialize(level + 1));
        });

        builder.indent();

        builder.add("}\n");

        return builder.getString();
    }
}
