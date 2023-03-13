package com.sussygaymer.j2dts.generator.transformers.interfaces;

import java.util.ArrayList;

import com.sussygaymer.j2dts.generator.TSBuilder;
import com.sussygaymer.j2dts.generator.transformers.Transformer;
import com.sussygaymer.j2dts.generator.transformers.values.ArgumentTransformer;
import com.sussygaymer.j2dts.generator.transformers.values.ReturnValueTransformer;
import com.sussygaymer.j2dts.generator.transformers.values.TypeParameterTransformer;

import lombok.Getter;

@Getter
public class InterfaceMethodTransformer extends Transformer {
    private InterfaceTransformer parent;
    private ReturnValueTransformer returnValue;
    private ArrayList<ArgumentTransformer> args = new ArrayList<ArgumentTransformer>();
    protected ArrayList<TypeParameterTransformer> typeParameters = new ArrayList<TypeParameterTransformer>();

    public InterfaceMethodTransformer(String name, ReturnValueTransformer returnValue, InterfaceTransformer parent) {
        this.name = name;
        this.returnValue = returnValue;
        this.parent = parent;
        
        parent.addMethod(this);
    }

    public void addArgument(ArgumentTransformer arg) {
        this.args.add(arg);
    }
    
    @Override
    public String serialize(int level) {
        TSBuilder builder = new TSBuilder(level);

        builder.indent();

        builder.add(name);

        if (this.typeParameters.size() != 0) {
            builder.add("<");

            for (int i = 0; i < this.typeParameters.size(); i++) {
                TypeParameterTransformer parameter = this.typeParameters.get(i);

                builder.add(parameter.serialize());

                if (i < this.typeParameters.size() - 1) builder.add(", ");
            }

            builder.add(">");
        }

        builder.add("(");

        for (int i = 0; i < this.args.size(); i++) {
            ArgumentTransformer arg = this.args.get(i);

            builder.add(arg.serialize());

            if (i < this.args.size() - 1) builder.add(", ");
        }

        builder.add("): ");

        builder.add(this.returnValue.serialize());

        builder.add(";\n");

        return builder.getString();
    }
}
