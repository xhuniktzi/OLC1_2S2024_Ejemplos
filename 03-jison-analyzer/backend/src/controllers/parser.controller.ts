import { Request, Response } from "express";
import { TsJisonExampleParser } from "../analyzers/ts-jison-example";

const parser = (req: Request, res: Response) => {
    const text = `
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

    const jisonParser = new TsJisonExampleParser();
    
    jisonParser.parseError = (_err: string, hash) => {
        const msg = "No se esperaba el token: " + hash.token + " Se esperaba: " + hash.expected;
        console.log(msg);
        res.status(400).send(msg);
    }
    jisonParser.parse(text);
    res.status(200).send()
};

export default parser;