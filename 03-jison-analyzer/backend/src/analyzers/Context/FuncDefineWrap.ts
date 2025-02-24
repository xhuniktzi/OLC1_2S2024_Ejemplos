import Statement from "../Statements/Statement.js";
import ParamContainer from "./ParamContainer.js";

export default interface FunctionDefineWrap {
    name: string;
    list_params: ParamContainer[];
    list_stmts: Statement[];
    // is_procedure: boolean; // false is function, true is procedure
}