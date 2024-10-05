import { Request, Response } from "express";
import { TsJisonExampleParser } from "../analyzers/ts-jison-example.js";
import Statement from "../analyzers/Statements/Statement.js";
import Context from "../analyzers/Context/Context.js";
import RuntimeError from "../analyzers/Exceptions/Runtime.js";

const parser = (req: Request, res: Response) => {
    
    const input = `
    echo 1 + 1 - 2;
    echo 3 + 1 == 4;
    echo -3 + 2;
    echo -(-3 + 2);
    let hello: string = "Hello";
    let world: string = "World";
    echo hello + ", " + world + "!";
    let foo: string = "Foo afuera de bloque";
    let bar: string = "Bar afuera de bloque";
    {
        let foo:string = "Foo dentro del bloque";
        echo foo;
        echo bar;
    };
    echo foo;
    foo = "Nuevo foo";
    echo foo;
    `;

const globalCtx = new Context();
try {
    const { errors, ast }: { errors: SyntaxError[]; ast: Statement[] } =
        new TsJisonExampleParser().parse(input);
    if (errors.length !== 0) {
        for (const err of errors) {
            console.error(err.message);
        }
    } else {
        for (const stmt of ast) {
            stmt.interpret(globalCtx);
        }
    }
} catch (err) {
    if (err instanceof RuntimeError) {
        console.error(err.message);
    } else {
        console.log(err);
    }
}


    res.status(200).send()
};

export default parser;