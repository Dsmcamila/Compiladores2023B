import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class Main {
    public static void main(String[] args) {
        // ------------- VENTANA PRINCIPAL ------------- //

        // Crear la ventana principal
        JFrame frame = new JFrame("Generador de Tablas de Símbolos");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setResizable(false);

        // Crear el panel principal
        JPanel panel = new JPanel();
        frame.add(panel);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        // ------------- AREA DE TEXTO DE CODIGO FUENTE ------------- //

        // Crear el título para el área de texto de código fuente con letra más grande
        JTextArea title = new JTextArea("Ingrese el código fuente a procesar:");
        title.setEditable(false);
        Font titleFont = new Font("Arial", Font.BOLD, 17);
        title.setFont(titleFont);
        panel.add(title);

        // Crear el área de texto para ingresar el código fuente
        JTextArea sourceCodeArea = new JTextArea(17, 20);
        panel.add(new JScrollPane(sourceCodeArea));

        // ------------- BOTONES ------------- //

        // Crear el botón para procesar el código
        JButton processButton = new JButton("Procesar Código");
        panel.add(processButton);

        // Crear el boton para limpiar el codigo
        JButton clearButton = new JButton("Limpiar ");
        panel.add(clearButton);

        // Crear un panel para los botones
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS)); // Usar BoxLayout para organizar los
                                                                             // botones horizontalmente

        // Añadir los botones al panel de botones
        buttonPanel.add(processButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(80, 50))); // Espacio entre los botones
        buttonPanel.add(clearButton);

        // Añadir el panel de botones al panel principal
        panel.add(buttonPanel);

        // ------------- TABLA DE SIMBOLOS ------------- //

        // Crear el titulo para el area de texto de la tabla de simbolos
        JTextArea title2 = new JTextArea("Tabla de Símbolos:");
        title2.setEditable(false);
        Font titleFont2 = new Font("Arial", Font.BOLD, 17);
        title2.setFont(titleFont2);
        panel.add(title2);

        // Crear el área de texto para mostrar la tabla de símbolos
        JTextArea symbolTableArea = new JTextArea(10, 30);
        symbolTableArea.setEditable(false); // Hacer que el área de texto sea de solo lectura
        panel.add(new JScrollPane(symbolTableArea));

        
        // ------------- ACCIONES DE LOS BOTONES ------------- //

        processButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String sourceCode = sourceCodeArea.getText();
                SymbolTable symbolTable = new SymbolTable();
                Lexer.tokenize(sourceCode, symbolTable);

                // Imprimir la tabla de símbolos en el área de texto
                StringBuilder symbolTableText = new StringBuilder();

                // Crear una lista temporal de símbolos ordenados por línea
                List<Symbol> sortedSymbols = new ArrayList<>(symbolTable.getSymbols().values());
                Collections.sort(sortedSymbols, Comparator.comparingInt(Symbol::getLine));

                // Primero, imprimir solo los identificadores
                symbolTableText.append("Identificadores:\n");
                for (Symbol symbol : sortedSymbols) {
                    if (symbol.getType().equals("Identificador")) {
                        int line = symbol.getLine();
                        String type = symbol.getType();
                        String value = symbol.getName();

                        symbolTableText.append("Linea ").append(line).append("    Tipo ").append(type)
                                .append("    Valor ")
                                .append(value);
                        if (symbol.isFunction()) {
                            symbolTableText.append(" (Función)");
                        }
                        symbolTableText.append("\n");
                    }
                }

                // Luego, imprimir solo las palabras clave
                symbolTableText.append("\nPalabras Clave:\n");
                for (Symbol symbol : sortedSymbols) {
                    if (symbol.getType().equals("Palabra clave")) {
                        int line = symbol.getLine();
                        String type = symbol.getType();
                        String value = symbol.getName();

                        symbolTableText.append("Linea ").append(line).append("    Tipo ").append(type)
                                .append("    Valor ")
                                .append(value).append("\n");
                    }
                }

                symbolTableArea.setText(symbolTableText.toString());
            }
        });

        // Añadir un ActionListener al botón para limpiar el código cuando se haga clic
        // en él
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sourceCodeArea.setText("");
                symbolTableArea.setText("");
            }
        });

        // Hacer visible la ventana
        frame.setVisible(true);
    }
}