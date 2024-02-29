import java.util.HashMap;
import java.util.Map;

public class SymbolTable {
    private Map<String, Symbol> symbols = new HashMap<>();

    public void addSymbol(Symbol symbol) {
        symbols.put(symbol.getName(), symbol);
    }

    public Symbol getSymbol(String name) {
        return symbols.get(name);
    }

    public Map<String, Symbol> getSymbols() {
        return symbols;
    }

    // Métodos adicionales según sea necesario
}