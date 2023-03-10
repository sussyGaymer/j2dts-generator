package com.sussygaymer.j2dts.generator.transformers;

import java.util.Arrays;

import com.sussygaymer.j2dts.generator.TSBuilder;

import lombok.Getter;

public class ValueTransformer extends Transformer {
    @Getter
    private boolean isArray;
    @Getter
    private String value;

    public ValueTransformer(String type) {
        String[] allowed = {"string", "number", "bigint", "boolean", "undefined", "symbol", "null", "function", "object"};
        if (!Arrays.stream(allowed).anyMatch(type::equals))
                throw new IllegalArgumentException("Invalid primitive type: " + type);

        this.value = type;
    }
    
    public ValueTransformer(String type, boolean isArray) {
        this(type);
        this.isArray = isArray;
    }
    
    public ValueTransformer(EntityTransformer entity) {
        this.value = entity.getFullName();
    }
    
    public ValueTransformer(EntityTransformer entity, boolean isArray) {
        this(entity);
        this.isArray = isArray;
    }

    public ValueTransformer(TypeParameterTransformer parameter) {
        this.value = parameter.getName();
    }

    public ValueTransformer(TypeParameterTransformer parameter, boolean isArray) {
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
