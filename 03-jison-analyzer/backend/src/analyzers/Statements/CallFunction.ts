import { TokenLocation } from "@ts-jison/common";
import Statement from "./Statement.js";
import Context from "../Context/Context.js";
import Global from '../Context/Global.js';
import RuntimeError from "../Exceptions/Runtime.js";
import ArgumentContainer from "../Context/ArgumentContainer.js";

export default class CallFunction implements Statement {

    private name: string;
    private args: ArgumentContainer[];
    location: TokenLocation;

    constructor(name: string, args: ArgumentContainer[], location: TokenLocation) {
        this.name = name;
        this.args = args;
        this.location = location;
    }

    interpret(ctx: Context) {
        const func = Global.list_functions.find(f => f.name == this.name);
        if (func) {
            const innerCtx = new Context(); // Contexto dentro de la llamada

            for (let i = 0; i < func.list_params.length; i++) {
                const param = func.list_params[i];
                const arg = this.args.find(f => f.name == param)
                // Este contexto es del punto de la llamada no dentro de la funcion
                // NOTA: type quemado, aqui deben validar tipo, asumiendo int para fines didacticos
                innerCtx.declare(param, { type: 'INT', value: arg.expr.interpret(ctx) }, this.location)
            }

            for (const stmt of func.list_stmts){
                stmt.interpret(innerCtx) // Contexto de la llamada
            }
        } else {
            throw new RuntimeError(
                `Function ${this.name} not found`,
                this.location
            )
        }
    };
}