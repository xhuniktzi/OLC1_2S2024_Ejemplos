package example_sets.symbols;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

public class SymTable {
    private Stack<Map<String, Set<Integer>>> scopes;
    
    public SymTable() {
        // Inicia la pila de scopes
        this.scopes = new Stack<>();
        // Crea el scope raíz
        this.pushScope();
    }
    
    public void pushScope() {
        // Agrega un nuevo scope a la pila
        this.scopes.push(new HashMap<>());
    }
    
    public void popScope() {
        // Elimina el scope más reciente de la pila
        if (!this.scopes.isEmpty()) {
            this.scopes.pop();
        }
    }
    
    public void add(String name, Set<Integer> elements) {
        if (!this.scopes.isEmpty()) {
            this.scopes.peek().put(name, elements);
        }
    }
    
    public Set<Integer> get(String name) {
        // Busca en la pila de scopes desde el más interno al más externo
        for (int i = this.scopes.size() - 1; i >= 0; i--) {
            if (this.scopes.get(i).containsKey(name)) {
                return this.scopes.get(i).get(name);
            }
        }
        return null;  // Retorna null si el nombre no se encuentra en ninguno de los scopes
    }
    
    public boolean contains(String name) {
        // Busca en la pila de scopes desde el más interno al más externo
        for (int i = this.scopes.size() - 1; i >= 0; i--) {
            if (this.scopes.get(i).containsKey(name)) {
                return true;
            }
        }
        return false;
    }
}
