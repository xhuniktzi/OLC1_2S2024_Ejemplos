import { TokenLocation } from "@ts-jison/common";
import Expression from "../Expressions/Expression.js";
import Statement from "./Statement.js";
import Context from "../Context/Context.js";

export default class CaseStmt implements Statement {
    public value: Expression;
    private stmts: Statement[];
    location: TokenLocation;

    constructor(value: Expression, stmts: Statement[], location: TokenLocation){
        this.value = value;
        this.stmts = stmts;
        this.location = location;
    }

    interpret (ctx: Context) {
        const localCtx = new Context();
        localCtx.prev = ctx;

        for (const stmt of this.stmts) {
            stmt.interpret(localCtx);
        }
    }
}
