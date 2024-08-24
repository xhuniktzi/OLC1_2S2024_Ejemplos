package example_sets.operations;

import example_sets.contracts.IStatement;
import example_sets.symbols.SymTable;
import java.util.LinkedList;

public class ScopeStatement extends IStatement {
    private final String name;
    private final LinkedList<IStatement> statements;

    public ScopeStatement(String name, LinkedList<IStatement> statements) {
        this.name = name;
        this.statements = statements;
    }

    @Override
    public void execute(SymTable table) {
        // Abre un nuevo scope
        table.pushScope();
        System.out.println("Entrando al scope: " + name);
        
        // Ejecuta todas las instrucciones dentro del scope
        for (IStatement statement : statements) {
            statement.execute(table);
        }

        // Cierra el scope
        table.popScope();
        System.out.println("Saliendo del scope: " + name);
    }

    @Override
    public String graph() {
        StringBuilder str = new StringBuilder();
        str.append("S_").append(id).append("[label=\"Scope: ").append(name).append("\"];\n");
        
        for (IStatement statement : statements) {
            str.append("S_").append(id).append(" -> S_").append(statement.getId()).append(";\n");
            str.append(statement.graph());
        }
        
        return str.toString();
    }
}
