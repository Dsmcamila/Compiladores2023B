public class Symbol {
    private String name;
    private String type;
    private boolean isFunction;
    private int line; // Campo para almacenar la línea del símbolo

    public Symbol(String name, String type, boolean isFunction, int line, int position) {
        this.name = name;
        this.type = type;
        this.isFunction = isFunction;
        this.line = line;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public boolean isFunction() {
        return isFunction;
    }

    public int getLine() {
        return line;
    }

}