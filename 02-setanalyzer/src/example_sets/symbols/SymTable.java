package example_sets.symbols;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

public class SymTable {
    private Stack<Map<String, Set<Integer>>> scopes;
    private List<Map<String, Set<Integer>>> allScopes;

    public SymTable() {
        this.scopes = new Stack<>();
        this.allScopes = new ArrayList<>();
        // Crea el scope raíz
        this.pushScope();
    }
    
    public void pushScope() {
        // Agrega un nuevo scope a la pila
        this.scopes.push(new HashMap<>());
    }
    
    public void popScope() {
        // Guarda el scope actual antes de eliminarlo
        if (!this.scopes.isEmpty()) {
            Map<String, Set<Integer>> currentScope = this.scopes.pop();
            // Guarda una copia del scope en la lista de todos los scopes
            this.allScopes.add(new HashMap<>(currentScope));
        }
    }
    
    public void add(String name, Set<Integer> elements) {
        if (!this.scopes.isEmpty()) {
            this.scopes.peek().put(name, elements);
        }
    }
    
    public Set<Integer> get(String name) {
        for (int i = this.scopes.size() - 1; i >= 0; i--) {
            if (this.scopes.get(i).containsKey(name)) {
                return this.scopes.get(i).get(name);
            }
        }
        return null;
    }
    
    public boolean contains(String name) {
        for (int i = this.scopes.size() - 1; i >= 0; i--) {
            if (this.scopes.get(i).containsKey(name)) {
                return true;
            }
        }
        return false;
    }

    // Método para imprimir todas las tablas de símbolos creadas
    public void printAllScopes() {
        System.out.println("Tablas de símbolos creadas:");
        int scopeCounter = 0;
        for (Map<String, Set<Integer>> scope : allScopes) {
            System.out.println("Scope " + scopeCounter + ": " + scope);
            scopeCounter++;
        }
    }
}
