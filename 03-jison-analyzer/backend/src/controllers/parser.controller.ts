import { Request, Response } from "express";
import { TsJisonExampleParser } from "../analyzers/ts-jison-example";

const parser = (req: Request, res: Response) => {
    const { text } = req.body;

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