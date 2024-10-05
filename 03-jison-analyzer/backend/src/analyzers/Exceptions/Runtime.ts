import { TokenLocation } from '@ts-jison/common';

export default class RuntimeError extends Error {
    constructor(msg: string, location: TokenLocation) {
        super(
            `Runtime error at ${location.first_line}, ${location.first_column}: ${msg}`
        );
    }
}
