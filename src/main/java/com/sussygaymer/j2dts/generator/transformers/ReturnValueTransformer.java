package com.sussygaymer.j2dts.generator.transformers;

import java.util.ArrayList;
import java.util.Arrays;

import com.sussygaymer.j2dts.generator.TSBuilder;

import lombok.Getter;

public class ReturnValueTransformer extends Transformer {
    @Getter
    private boolean isArray;
    @Getter
    private String value;

    public ReturnValueTransformer(String type) {
        String[] allowed = {"string", "number", "bigint", "boolean", "undefined", "symbol", "null", "function", "object", "void"};
        if (!Arrays.stream(allowed).anyMatch(type::equals))
                throw new IllegalArgumentException("Invalid primitive type: " + type);

        this.value = type;
    }
    
    public ReturnValueTransformer(String type, boolean isArray) {
        this(type);
        this.isArray = isArray;
    }

    public ReturnValueTransformer(EntityTransformer entity) {
        this.value = entity.getFullName();
    }
    
    public ReturnValueTransformer(EntityTransformer entity, boolean isArray) {
        this(entity);
        this.isArray = isArray;
    }

    public ReturnValueTransformer(TypeParameterTransformer parameter) {
        this.value = parameter.getName();
    }

    public ReturnValueTransformer(TypeParameterTransformer parameter, boolean isArray) {
        this(parameter);
        this.isArray = isArray;
    }

    public ReturnValueTransformer(TypeParameterValueTransformer parametrized) {
        TSBuilder builder = new TSBuilder(0);

        builder.add(parametrized.getTarget().getFullName());

        builder.add("<");

        ArrayList<ValueTransformer> parameters = parametrized.getParameters();

        for (int i = 0; i < parameters.size(); i++) {
            ValueTransformer parameter = parameters.get(i);

            builder.add(parameter.serialize());

            if (i < parameters.size() - 1) builder.add(", ");
        }

        builder.add(">");

        this.value = builder.getString();
    }

    public ReturnValueTransformer(TypeParameterValueTransformer parameter, boolean isArray) {
        this(parameter);
        this.isArray = isArray;
    }

    @Override
    public String serialize(int level) {
        TSBuilder builder = new TSBuilder(level);

        builder.add(this.value);

        if (this.isArray)
            builder.add("[]");

        return builder.getString();
    }
}
