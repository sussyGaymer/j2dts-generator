package com.sussygaymer.j2dts.generator.transformers.classes;

import java.util.ArrayList;

import com.sussygaymer.j2dts.generator.TSBuilder;
import com.sussygaymer.j2dts.generator.transformers.EntityTransformer;
import com.sussygaymer.j2dts.generator.transformers.NamespaceTransformer;
import com.sussygaymer.j2dts.generator.transformers.TypeParameterTransformer;
import com.sussygaymer.j2dts.generator.transformers.TypeParameterValueTransformer;
import com.sussygaymer.j2dts.generator.transformers.interfaces.InterfaceTransformer;

import lombok.Getter;
import lombok.Setter;

public class ClassTransformer extends EntityTransformer {
    @Getter @Setter
    private boolean isAbstract = false;
    @Getter
    private ArrayList<ClassMethodTransformer> methods = new ArrayList<ClassMethodTransformer>();
    @Getter
    private ArrayList<ClassPropertyTransformer> properties = new ArrayList<ClassPropertyTransformer>();
    @Getter
    private ArrayList<ConstructorTransformer> constructors = new ArrayList<ConstructorTransformer>();
    @Getter
    private EntityTransformer parent = null;

    public ClassTransformer(String name, NamespaceTransformer namespace) {
        this.name = name;
        this.namespace = namespace;

        namespace.addClass(this);
    }
    
    public ClassTransformer(String name, NamespaceTransformer namespace, EntityTransformer parent) {
        this(name, namespace);

        this.parent = parent;
    }
    
    public void addMethod(ClassMethodTransformer method) {
        if (method.isAbstract() && !this.isAbstract)
            throw new IllegalArgumentException("Abstract methods can only appear within an abstract class.");
        
        this.methods.add(method);
    }
    
    public void addProperty(ClassPropertyTransformer property) {
        this.properties.add(property);
    }
    
    public void addConstructor(ConstructorTransformer constructor) {
        this.constructors.add(constructor);
    }

    @Override
    public String serialize(int level) {
        TSBuilder builder = new TSBuilder(level);
        
        builder.indent();
        
        if (this.isAbstract) {
            builder.add("abstract ");
        }

        builder.add("class ");
        
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
            if (this.parent instanceof InterfaceTransformer || (this.parent instanceof TypeParameterValueTransformer && ((TypeParameterValueTransformer)this.parent).getTarget() instanceof InterfaceTransformer)) {
                builder.add(" implements ");
                builder.add(parent.serialize());
            } else {
                builder.add(" extends ");
                builder.add(this.parent.getFullName());
            }
        }

        builder.add(" {\n");
        
        this.properties.forEach(property -> {
            builder.add(property.serialize(level + 1));
        });
        
        this.constructors.forEach(constructor -> {
            builder.add(constructor.serialize(level + 1));
        });

        this.methods.forEach(method -> {
            builder.add(method.serialize(level + 1));
        });

        builder.indent();
        
        builder.add("}\n");

        return builder.getString();
    }
}
