import { TokenLocation } from '@ts-jison/common';
import RuntimeError from '../../Exceptions/Runtime.js';

export default function getNegation(expr: any, location: TokenLocation) {
    const exprType = typeof expr;
    if (exprType === 'number') {
        return expr * -1;
    }
    throw new RuntimeError(
        'Negation is undefined for type ' + exprType,
        location
    );
}
