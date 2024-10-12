import Context from "../Context/Context.js";
import ReturnEx from "../ControlFlow/ReturnEx.js";
import Expression from "../Expressions/Expression.js";
import Statement from "./Statement.js";
import { TokenLocation } from "@ts-jison/common";

export default class Return implements Statement {
    private expr: Expression;
    location: TokenLocation; 

    constructor(expr: Expression, location: TokenLocation){
        this.expr = expr;
        this.location = location;
    }

    interpret (ctx: Context) {
        const result = this.expr.interpret(ctx);
        throw new ReturnEx(result);
    };

    
}