package com.sussygaymer.j2dts.generator.transformers;

import lombok.Getter;

public class ArgumentTransformer extends Transformer {
    @Getter
    private String name;
    @Getter
    private ValueTransformer value;
    
    public ArgumentTransformer(String name, ValueTransformer value) {
        this.name = name;
        this.value = value;
    }

    @Override
    public String serialize(int level) {
        return this.name + ": " + this.value.serialize();
    }
}
