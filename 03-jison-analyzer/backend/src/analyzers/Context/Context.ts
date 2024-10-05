import Sym from './Symbol.js';
import { TokenLocation } from '@ts-jison/common';
import RuntimeError from '../Exceptions/Runtime.js';
import wrapInSym from './WrapInSym.js';

export default class Context {
    symbols: Map<string, Sym>;
    console: string;
    prev: Context;

    constructor() {
        this.prev = null;
        this.symbols = new Map();
    }

    get(key: string, location: TokenLocation) {
        let symbol = this.symbols.get(key);
        if (!symbol && this.prev) {
            symbol = this.prev.get(key, location);
        }
        return symbol;
    }

    declare(key: string, symbol: Sym, location: TokenLocation) {
        if (this.symbols.has(key)) {
            throw new RuntimeError(`${key} already exists`, location);
        }
        this.symbols.set(key, symbol);
    }

    set(key: string, value: any, location: TokenLocation) {
        const symbol = this.get(key, location);
        if (!symbol) {
            throw new RuntimeError(`${key} is undefined`, location);
        }
        const newSymbol = wrapInSym(symbol.type, value, location);
        symbol.value = newSymbol.value;
    }
}
