import { TokenLocation } from '@ts-jison/common';
import Statement from './Statement.js';
import Context from '../Context/Context.js';
import Expression from '../Expressions/Expression.js';

export default class IfStmt implements Statement {
    private stmts: Statement[]
    private condition: Expression;
    location: TokenLocation;

    constructor(condition: Expression, stmts: Statement[], location: TokenLocation){
        this.condition = condition;
        this.stmts = stmts;
        this.location = location;
    }
    
    interpret(ctx: Context){
        const localCtx = new Context();
        localCtx.prev = ctx;
        if (this.condition.interpret(ctx)){
            for (const stmt of this.stmts) {
                stmt.interpret(localCtx);
            }
        }

    }
}

