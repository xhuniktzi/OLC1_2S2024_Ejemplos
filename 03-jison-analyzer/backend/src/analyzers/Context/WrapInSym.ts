import { Type } from '../Expressions/Types.js';
import Sym from './Symbol.js';
import { TokenLocation } from '@ts-jison/common';
import RuntimeError from '../Exceptions/Runtime.js';

export default function wrapInSym(
    type: Type,
    value: any,
    location: TokenLocation
): Sym {
    const symbol: Sym = {
        value: value,
        type: type,
    };
    switch (type.toUpperCase()) {
        case 'INT':
            if (typeof value === 'number') {
                return symbol;
            }
            break;
        case 'STRING':
            if (typeof value === 'string') {
                return symbol;
            }
            break;
        case 'NULL':
            if (value === null) {
                return symbol;
            }
            break;
    }
    throw new RuntimeError(
        `Invalidad value for type ${type}: (${typeof value}) ${value}`,
        location
    );
}
