package com.sussygaymer.j2dts.generator.transformers.interfaces;

import java.util.ArrayList;

import com.sussygaymer.j2dts.generator.TSBuilder;
import com.sussygaymer.j2dts.generator.transformers.EntityTransformer;
import com.sussygaymer.j2dts.generator.transformers.NamespaceTransformer;
import com.sussygaymer.j2dts.generator.transformers.TypeParameterTransformer;

import lombok.Getter;

public class InterfaceTransformer extends EntityTransformer {
    @Getter
    private ArrayList<InterfaceMethodTransformer> methods = new ArrayList<InterfaceMethodTransformer>();
    @Getter
    private ArrayList<InterfacePropertyTransformer> properties = new ArrayList<InterfacePropertyTransformer>();
    @Getter
    private InterfaceTransformer parent = null;

    public InterfaceTransformer(String name, NamespaceTransformer namespace) {
        this.name = name;
        this.namespace = namespace;

        namespace.addInterface(this);
    }
    
    public InterfaceTransformer(String name, NamespaceTransformer namespace, InterfaceTransformer parent) {
        this(name, namespace);

        this.parent = parent;
    }

    public void addMethod(InterfaceMethodTransformer method) {
        this.methods.add(method);
    }

    public void addProperty(InterfacePropertyTransformer property) {
        this.properties.add(property);
    }

    @Override
    public String serialize(int level) {
        TSBuilder builder = new TSBuilder(level);   

        builder.indent();

        builder.add("interface ");

        builder.add(this.name);

        if (this.typeParameters.size() != 0) {
            builder.add("<");

            for (int i = 0; i < this.typeParameters.size(); i++) {
                TypeParameterTransformer parameter = this.typeParameters.get(i);

                builder.add(parameter.serialize());

                if (i < this.typeParameters.size() - 1) builder.add(", ");
            }

            builder.add(">");
        }

        if (this.parent != null) {
            builder.add(" extends ");
            builder.add(this.parent.getFullName());
        }

        builder.add(" {\n");

        this.properties.forEach(property -> {
            builder.add(property.serialize(level + 1));
        });

        this.methods.forEach(method -> {
            builder.add(method.serialize(level + 1));
        });

        builder.indent();

        builder.add("}\n");

        return builder.getString();

    }
}
