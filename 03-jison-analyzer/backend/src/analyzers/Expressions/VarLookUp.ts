import { TokenLocation } from '@ts-jison/common';
import Expression from './Expression.js';
import Context from '../Context/Context.js';

export default class VarLookUpExpr implements Expression {
    private identifier: string;
    location: TokenLocation;

    constructor(identifier: string, location: TokenLocation) {
        this.identifier = identifier;
        this.location = location;
    }

    interpret(ctx: Context) {
        const symbol = ctx.get(this.identifier, this.location);
        return symbol.value;
    }
}
