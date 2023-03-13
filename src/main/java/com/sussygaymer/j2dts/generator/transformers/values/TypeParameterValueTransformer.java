package com.sussygaymer.j2dts.generator.transformers.values;

import java.util.ArrayList;

import com.sussygaymer.j2dts.generator.TSBuilder;
import com.sussygaymer.j2dts.generator.transformers.EntityTransformer;

import lombok.Getter;

@Getter
public class TypeParameterValueTransformer extends EntityTransformer {
    private EntityTransformer target;
    private ArrayList<ValueTransformer> parameters = new ArrayList<ValueTransformer>();

    public TypeParameterValueTransformer(EntityTransformer target) {
        // Gotta simulate this so it works as a normal entity
        this.name = target.getName();
        this.namespace = target.getNamespace();
        this.typeParameters = target.getTypeParameters();

        this.target = target;
    }
    
    public void addParameter(ValueTransformer parameter) {
        this.parameters.add(parameter);
    }

    @Override
    public String serialize(int level) {
        TSBuilder builder = new TSBuilder(level);

        builder.add(this.target.getFullName());

        builder.add("<");

        for (int i = 0; i < this.parameters.size(); i++) {
            ValueTransformer parameterValue = this.parameters.get(i);

            builder.add(parameterValue.serialize());

            if (i < this.parameters.size() - 1) builder.add(", ");
        }

        builder.add(">");

        return builder.getString();
    }
}
