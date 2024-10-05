import { TokenLocation } from '@ts-jison/common';
import Statement from './Statement.js';
import Context from '../Context/Context.js';

export default class BlockStmt implements Statement {
    private stmts: Statement[];
    location: TokenLocation;

    constructor(stmts: Statement[], location: TokenLocation) {
        this.stmts = stmts;
        this.location = location;
    }

    interpret(ctx: Context) {
        const localCtx = new Context();
        localCtx.prev = ctx;
        for (const stmt of this.stmts) {
            stmt.interpret(localCtx);
        }
    }
}
