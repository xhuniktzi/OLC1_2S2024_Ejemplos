import Statement from "../Statements/Statement.js";

export default interface FunctionDefineWrap {
    name: string;
    list_params: string[];
    list_stmts: Statement[];   
}