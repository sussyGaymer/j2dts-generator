package com.sussygaymer.j2dts.generator.transformers.values;

import com.sussygaymer.j2dts.generator.transformers.Transformer;

import lombok.Getter;

@Getter
public class ArgumentTransformer extends Transformer {
    private String name;
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
