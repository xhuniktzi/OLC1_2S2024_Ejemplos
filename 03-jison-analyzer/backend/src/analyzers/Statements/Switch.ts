import { TokenLocation } from "@ts-jison/common";
import Expression from "../Expressions/Expression.js";
import Case from "./Case.js";
import Statement from "./Statement.js";
import Context from "../Context/Context.js";

export default class Switch implements Statement {
    private condition: Expression;
    private cases: Case[];
    private default_stmts: Statement[];
    location: TokenLocation;

    constructor (condition: Expression, cases: Case[], default_stmts: Statement[], location: TokenLocation){
        this.condition = condition;
        this.cases = cases;
        this.default_stmts = default_stmts;
        this.location = location;
    }

    interpret (ctx: Context) {
        const value = this.condition.interpret(ctx);

        for(const element of this.cases) {
           const data = element.value.interpret(ctx);
           if (data == value){
                element.interpret(ctx);
                return;
           }
        }

        if (this.default_stmts != null){
            const localCtx = new Context();
            localCtx.prev = ctx;
            for (const stmt of this.default_stmts) {
                stmt.interpret(localCtx);
            }
        }

    }
}