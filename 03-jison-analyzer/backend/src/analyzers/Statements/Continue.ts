import { TokenLocation } from "@ts-jison/common";
import Context from "../Context/Context.js";
import Statement from "./Statement.js";
import ContinueEx from "../ControlFlow/ContinueEx.js";

export default class Continue implements Statement {
    location: TokenLocation;
    constructor(location: TokenLocation){
        this.location = location;
    }
    interpret (ctx: Context) {
        throw new ContinueEx;
    };

}