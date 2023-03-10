package com.sussygaymer.j2dts.generator.transformers;

import lombok.Getter;

public abstract class Transformer {
    @Getter
    protected String name;
    
    /**
     * Serialize the object to TypeScript code.
     * @return The TS code
     */
    public abstract String serialize(int indentLevel);

    /**
     * Serialize the object to TypeScript code.
     * @return The TS code
     */
    public String serialize() {
        return this.serialize(0);
    }
}
