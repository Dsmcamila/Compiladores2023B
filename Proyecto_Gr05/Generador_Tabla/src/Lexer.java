import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Lexer {
    public static void tokenize(String sourceCode, SymbolTable symbolTable) {
        // Expresiones regulares para identificar identificadores, números, palabras clave y espacios en blanco
        Pattern identifierPattern = Pattern.compile("\\b[a-zA-Z_]\\w*\\b");
        Pattern numberPattern = Pattern.compile("\\b\\d+\\b");
        Pattern keywordPattern = Pattern.compile("\\b(if|else|for|while|return)\\b");
        Pattern functionPattern = Pattern.compile("\\b[a-zA-Z_]\\w*\\b\\s*\\(");

        // Buscar coincidencias en el código fuente línea por línea
        String[] lines = sourceCode.split("\n");
        int lineNumber = 1; // Iniciar en la línea 1
        for (String line : lines) {
            Matcher matcher;

            // Buscar identificadores
            matcher = identifierPattern.matcher(line);
            while (matcher.find()) {
                boolean isFunction = functionPattern.matcher(line.substring(matcher.start())).find();
                symbolTable.addSymbol(new Symbol(matcher.group(), "Identificador", isFunction, lineNumber, matcher.start() + 1));
            }

            // Buscar números
            matcher = numberPattern.matcher(line);
            while (matcher.find()) {
                symbolTable.addSymbol(new Symbol(matcher.group(), "Entero", false, lineNumber, matcher.start() + 1));
            }

            // Buscar palabras clave
            matcher = keywordPattern.matcher(line);
            while (matcher.find()) {
                symbolTable.addSymbol(new Symbol(matcher.group(), "Palabra clave", false, lineNumber, matcher.start() + 1));
            }

            lineNumber++; // Incrementar el número de línea después de procesar cada línea
        }
    }
}