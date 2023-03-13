package com.sussygaymer.j2dts.generator.transformers.classes;

import java.util.ArrayList;

import com.sussygaymer.j2dts.generator.TSBuilder;
import com.sussygaymer.j2dts.generator.transformers.Transformer;
import com.sussygaymer.j2dts.generator.transformers.values.ArgumentTransformer;

import lombok.Getter;

@Getter
public class ConstructorTransformer extends Transformer {
    private ArrayList<ArgumentTransformer> args = new ArrayList<ArgumentTransformer>();

    public void addArgument(ArgumentTransformer arg) {
        this.args.add(arg);
    }

    @Override
    public String serialize(int level) {
        TSBuilder builder = new TSBuilder(level);
        
        builder.indent();
        
        builder.add("constructor(");
        
        for (int i = 0; i < this.args.size(); i++) {
            ArgumentTransformer arg = this.args.get(i);

            builder.add(arg.serialize());

            if (i < this.args.size() - 1) builder.add(", ");
        }
        
        builder.add(");\n");
        
        return builder.getString();
    }
}
