package com.sussygaymer.j2dts.generator.transformers.classes;

import java.util.ArrayList;

import com.sussygaymer.j2dts.generator.TSBuilder;
import com.sussygaymer.j2dts.generator.transformers.Transformer;
import com.sussygaymer.j2dts.generator.transformers.values.ArgumentTransformer;
import com.sussygaymer.j2dts.generator.transformers.values.ReturnValueTransformer;
import com.sussygaymer.j2dts.generator.transformers.values.TypeParameterTransformer;

import lombok.Getter;
import lombok.Setter;

@Getter
public class ClassMethodTransformer extends Transformer {
    private ClassTransformer parent;
    private ReturnValueTransformer returnValue;
    private ArrayList<ArgumentTransformer> args = new ArrayList<ArgumentTransformer>();
    private boolean isAbstract = false;
    private boolean isStatic = false;
    @Setter
    private Visibility visibility = null;
    protected ArrayList<TypeParameterTransformer> typeParameters = new ArrayList<TypeParameterTransformer>();

    public ClassMethodTransformer(String name, ReturnValueTransformer returnValue, ClassTransformer parent) {
        this.name = name;
        this.returnValue = returnValue;
        this.parent = parent;
        
        parent.addMethod(this);
    }

    public void setAbstract(boolean isAbstract) {
        if (isAbstract && !this.parent.isAbstract())
            throw new IllegalArgumentException("Abstract methods can only appear within an abstract class.");
        
        if (this.isStatic)
            throw new IllegalArgumentException("'static' modifier cannot be used with 'abstract' modifier.");

        this.isAbstract = isAbstract;
    }

    public void setStatic(boolean isStatic) {
        if (this.isAbstract && isStatic)
            throw new IllegalArgumentException("'static' modifier cannot be used with 'abstract' modifier.");

        this.isStatic = isStatic;
    }

    public void addArgument(ArgumentTransformer arg) {
        this.args.add(arg);
    }
    
    @Override
    public String serialize(int level) {
        TSBuilder builder = new TSBuilder(level);

        builder.indent();

        if (this.visibility != null)
            builder.add(this.visibility.toString().toLowerCase() + " ");

        if (this.isStatic)
            builder.add("static ");
        
        if (this.isAbstract)
            builder.add("abstract ");

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
