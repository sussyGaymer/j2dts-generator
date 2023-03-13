package com.sussygaymer.j2dts.generator.transformers;

import java.util.ArrayList;

import com.sussygaymer.j2dts.generator.TSBuilder;
import com.sussygaymer.j2dts.generator.transformers.classes.ClassTransformer;
import com.sussygaymer.j2dts.generator.transformers.enums.EnumTransformer;
import com.sussygaymer.j2dts.generator.transformers.interfaces.InterfaceTransformer;

import lombok.Getter;

@Getter
public class NamespaceTransformer extends Transformer {
    private ArrayList<ClassTransformer> classes = new ArrayList<ClassTransformer>();
    private ArrayList<InterfaceTransformer> interfaces = new ArrayList<InterfaceTransformer>();
    private ArrayList<EnumTransformer> enums = new ArrayList<EnumTransformer>();
    
    public NamespaceTransformer(String name) {
        this.name = name;
    }
    
    public void addClass(ClassTransformer clazz) {
        this.classes.add(clazz);
    }

    public void addInterface(InterfaceTransformer interfacee) {
        this.interfaces.add(interfacee);
    }

    public void addEnum(EnumTransformer lenum) {
        this.enums.add(lenum);
    }
    @Override
    public String serialize(int level) {
        TSBuilder builder = new TSBuilder(-1);
        
        builder.indent();
        
        builder.add("declare namespace ");

        builder.add(this.name);

        builder.add(" {\n");

        this.interfaces.forEach((interfacee) -> {
            builder.add(interfacee.serialize());
        });

        this.classes.forEach((clazz) -> {
            builder.add(clazz.serialize());
        });

        this.enums.forEach((lenum) -> {
            builder.add(lenum.serialize());
        });

        builder.add("}\n");

        return builder.getString();
    }
}
