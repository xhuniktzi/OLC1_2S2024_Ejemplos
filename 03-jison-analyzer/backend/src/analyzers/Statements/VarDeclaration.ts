import { TokenLocation } from '@ts-jison/common';
import Statement from './Statement.js';
import { Type } from '../Expressions/Types.js';
import Context from '../Context/Context.js';
import Expression from '../Expressions/Expression.js';
import wrapInSym from '../Context/WrapInSym.js';

export default class VarDeclarationStmt implements Statement {
    private identifier: string;
    private type: Type;
    private expr: Expression;
    location: TokenLocation;

    constructor(
        identifier: string,
        type: Type,
        expr: Expression,
        location: TokenLocation
    ) {
        this.identifier = identifier;
        this.type = type;
        this.expr = expr;
        this.location = location;
    }

    interpret(ctx: Context) {
        const value = this.expr.interpret(ctx);
        const symbol = wrapInSym(this.type, value, this.location);
        ctx.declare(this.identifier, symbol, this.location);
    }
}
