import { TokenLocation } from '@ts-jison/common';
import RuntimeError from '../../Exceptions/Runtime.js';

export default function getComparison(
    left: any,
    operator: string,
    right: any,
    location: TokenLocation
) {
    const leftType = typeof left;
    const rightType = typeof right;
    switch (operator) {
        case '==':
            if (leftType === 'number' || leftType === 'object') {
                if (leftType === 'number' || leftType === 'object') {
                    return left === right;
                }
            }
    }
    throw new RuntimeError(
        `The ${operator} operator is undefined for types ${leftType} and ${rightType}`,
        location
    );
}
