import { TokenLocation } from '@ts-jison/common';
import RuntimeError from '../../Exceptions/Runtime.js';

export default function getDifference(
    left: any,
    right: any,
    location?: TokenLocation
) {
    const leftType = typeof left;
    const rightType = typeof right;
    if (leftType === 'number') {
        if (rightType === 'number') {
            return left - right;
        }
    }
    throw new RuntimeError(
        `Sum is undefined for types ${leftType} and ${rightType}`,
        location
    );
}
