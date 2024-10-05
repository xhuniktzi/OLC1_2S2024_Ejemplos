import { Request, Response } from "express";
import { TsJisonExampleParser } from "../analyzers/ts-jison-example.js";
import Statement from "../analyzers/Statements/Statement.js";
import Context from "../analyzers/Context/Context.js";
import RuntimeError from "../analyzers/Exceptions/Runtime.js";
import Global from "../analyzers/Context/Global.js";

const parser = (req: Request, res: Response) => {
    const { input } = req.body;

    const globalCtx = new Context();
    try {
        const { errors, ast }: { errors: SyntaxError[]; ast: Statement[] } =
            new TsJisonExampleParser().parse(input);
        if (errors.length !== 0) {
            for (const err of errors) {
                console.error(err.message);
                res.status(400).send();
            }
        } else {
            for (const stmt of ast) {
                stmt.interpret(globalCtx);
            }
        }
    } catch (err) {
        if (err instanceof RuntimeError) {
            console.error(err.message);
            res.status(400).send();
        } else {
            console.log(err);
            res.status(500).send();
        }
    }


    res.status(200).send({
        "output": Global.console
    })
};

export default parser;