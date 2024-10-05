import { Type } from './Types.js';
import Expression from './Expression.js';
import { TokenLocation } from '@ts-jison/common';
import Context from '../Context/Context.js';

export default class LiteralExpr implements Expression {
    private literal: any;
    private t: Type;
    location: TokenLocation;

    constructor(literal: any, t: Type, location: TokenLocation) {
        this.literal = literal;
        this.t = t;
        this.location = location;
    }

    interpret(ctx: Context) {
        switch (this.t) {
            case 'INT':
                return Number(this.literal);
            case 'STRING':
                return this.literal.replaceAll('"', '');
            case 'BOOL':
                return this.literal == 'True' ? true : this.literal == 'False' ? false : null;
            case 'NULL':
                return null;
        }
    }
}
