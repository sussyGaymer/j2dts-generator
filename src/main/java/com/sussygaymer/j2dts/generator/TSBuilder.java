package com.sussygaymer.j2dts.generator;

import lombok.Getter;

public class TSBuilder {
    @Getter
    private String string = "";
    @Getter
    private int indentSize;
    
    public TSBuilder(int indentLevel) {
        this.indentSize = Constants.TAB_WIDTH * (indentLevel + 1);
    }
    
    public void add(String line) {
        this.string += line;
    }
    
    public void indent() {
        // funny way to repeat chars
        this.string += new String(new char[this.indentSize]).replace("\0", " ");   
    }
}
