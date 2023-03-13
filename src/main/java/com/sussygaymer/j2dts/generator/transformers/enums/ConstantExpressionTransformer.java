package com.sussygaymer.j2dts.generator.transformers.enums;

import com.sussygaymer.j2dts.generator.transformers.Transformer;

import lombok.Getter;

@Getter
public class ConstantExpressionTransformer extends Transformer {
    private String stringValue = null;
    private int numberValue = 0; // cant be null, optional wouldn't work either

    public ConstantExpressionTransformer(String value) {
        this.stringValue = value;
    }

    public ConstantExpressionTransformer(int value) {
        this.numberValue = value;
    }

    @Override
    public String serialize(int level) {
        if (this.stringValue != null)
            return "\"" + this.stringValue + "\"";
        else
            return Integer.toString(this.numberValue);
    }
}
