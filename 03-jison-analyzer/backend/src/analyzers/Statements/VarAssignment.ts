import { TokenLocation } from '@ts-jison/common';
import Statement from './Statement.js';
import Context from '../Context/Context.js';
import Expression from '../Expressions/Expression.js';

export default class VarAssignmentStmt implements Statement {
    private identifier: string;
    private expr: Expression;
    location: TokenLocation;

    constructor(identifier: string, expr: Expression, location: TokenLocation) {
        this.identifier = identifier;
        this.expr = expr;
        this.location = location;
    }

    interpret(ctx: Context) {
        const value = this.expr.interpret(ctx);
        ctx.set(this.identifier, value, this.location);
    }
}
