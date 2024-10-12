import { TokenLocation } from '@ts-jison/common';
import Statement from './Statement.js';
import Context from '../Context/Context.js';
import Global from '../Context/Global.js';
import ParamContainer from '../Context/ParamContainer.js';

export default class FunctionDefine implements Statement {
    private name: string;
    private list_params: ParamContainer[];
    private list_stmts: Statement[];  
    location: TokenLocation;

    constructor(name: string, list_params: ParamContainer[], list_stmts: Statement[], location: TokenLocation){
        this.name = name;
        this.list_params = list_params;
        this.list_stmts = list_stmts;
        this.location = location;
    }

    interpret(ctx: Context){
        Global.list_functions.push({
            name: this.name,
            list_params: this.list_params,
            list_stmts: this.list_stmts
        })
    }
}