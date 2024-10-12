import { TokenLocation } from "@ts-jison/common";
import Statement from "./Statement.js";
import Context from "../Context/Context.js";
import ContinueEx from "../ControlFlow/ContinueEx.js";
import BreakEx from "../ControlFlow/BreakEx.js";

export default class Loop implements Statement {
    private stmts: Statement[];
    location: TokenLocation;

    constructor(stmts: Statement[], location: TokenLocation) {
        this.stmts = stmts;
        this.location = location;
    }
    interpret(ctx: Context) {

        const localCtx = new Context();
        localCtx.prev = ctx;

        while (true) {
            try {
                for (const stmt of this.stmts) {
                    stmt.interpret(localCtx);
                }
            } catch (error) {
                if (error instanceof ContinueEx) {
                    continue;
                }
                if (error instanceof BreakEx) {
                    break;
                }
            }
        }
    };

}