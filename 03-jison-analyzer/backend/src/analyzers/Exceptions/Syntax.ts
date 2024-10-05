import { TokenLocation } from '@ts-jison/common';

export default class SintaxError extends Error {
    constructor(token: string, location: TokenLocation) {
        super(
            `Syntax error at ${location.first_line}, ${location.first_column}: ${token}`
        );
    }
}
