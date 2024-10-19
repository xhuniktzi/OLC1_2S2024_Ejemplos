import FunctionDefineWrap from "./FuncDefineWrap.js";
import Sym from './Symbol.js';
export default class Global {
    public static console: string = '';

    public static list_functions: FunctionDefineWrap[] = [];
    // public static global_context:  Map<string, Sym> = new Map();
}