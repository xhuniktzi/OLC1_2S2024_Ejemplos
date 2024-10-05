import { TokenLocation } from '@ts-jison/common';
import Expression from './Expression.js';
import getDifference from './operations/Substraction.js';
import getSum from './operations/Sum.js';
import getRelational from './operations/Relational.js';
import Context from '../Context/Context.js';

export default class BinaryExpr implements Expression {
    private left: Expression;
    private right: Expression;
    private operator: string;
    location: TokenLocation;

    constructor(
        left: Expression,
        operator: string,
        right: Expression,
        location: TokenLocation
    ) {
        this.left = left;
        this.right = right;
        this.operator = operator;
        this.location = location;
    }

    interpret(ctx: Context) {
        const left = this.left.interpret(ctx);
        const right = this.right.interpret(ctx);

        switch (this.operator) {
            case '+':
                return getSum(left, right, this.location);
            case '-':
                return getDifference(left, right, this.location);
            case '==':
                return getRelational(left, this.operator, right, this.location);
            case '>':
                return getRelational(left, this.operator, right, this.location);
        }
    }
}
