import { TokenLocation } from '@ts-jison/common';
import Statement from './Statement.js';
import Context from '../Context/Context.js';
import Expression from '../Expressions/Expression.js';

export default class IfStmt implements Statement {
    private stmts: Statement[]
    private condition: Expression;
    private else_statements: Statement[];
    location: TokenLocation;

    constructor(condition: Expression, stmts: Statement[], else_statements: Statement[], location: TokenLocation){
        this.condition = condition;
        this.stmts = stmts;
        this.else_statements = else_statements;
        this.location = location;
    }
    
    interpret(ctx: Context){
        const localCtx = new Context();
        localCtx.prev = ctx;
        if (this.condition.interpret(ctx)){
            for (const stmt of this.stmts) {
                stmt.interpret(localCtx);
            }
        } else {
            for (const stmt of this.else_statements){
                stmt.interpret(localCtx)
            }
        }

    }
}

