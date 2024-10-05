import { TokenLocation } from '@ts-jison/common';
import RuntimeError from '../../Exceptions/Runtime.js';

export default function getSum(
    left: any,
    right: any,
    location?: TokenLocation
) {
    const leftType = typeof left;
    const rightType = typeof right;
    if (leftType === 'number' || leftType === 'string') {
        if (rightType === 'number' || rightType === 'string') {
            return left + right;
        }
    }
    throw new RuntimeError(
        `Sum is undefined for types ${leftType} and ${rightType}`,
        location
    );
}
