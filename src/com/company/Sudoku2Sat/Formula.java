package com.company.Sudoku2Sat;

import java.util.*;

public class Formula {

    private List<Clause> clauses;
    private Set<Variable> variables;
    private Map<String, Variable> map;

    public Formula(List<Clause> clauses) {
        this.clauses = clauses;
    }

    public Formula(Formula formula) {

        this.clauses = formula.getClauses();

        this.variables = new HashSet<>();

        for (Clause c : this.clauses) {
            this.variables.addAll(c.getVariables());
        }

        this.map = new HashMap<>();

        for (Variable v : this.variables) this.map.put(v.getName(), v);
    }

    public List<Clause> getClauses() {
        return clauses;
    }

    public void setClauses(List<Clause> clauses) {
        this.clauses = clauses;
    }

    public Map<String, Variable> getMap() { return this.map; }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        for (Clause c : this.clauses) {
            str.append('(').append(c.toString()).append(')').append('^');
        }
        return str.substring(0, str.length()-1);
    }
}
