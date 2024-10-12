import { TokenLocation } from "@ts-jison/common";
import Context from "../Context/Context.js";
import Statement from "./Statement.js";
import BreakEx from "../ControlFlow/BreakEx.js";

export default class Break implements Statement {
    location: TokenLocation;
    constructor(location: TokenLocation){
        this.location = location;
    }
    interpret (ctx: Context) {
        throw new BreakEx;
    };

}