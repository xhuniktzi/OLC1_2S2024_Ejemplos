export default class LexicalError extends Error {
    constructor(lexeme: string, row: number, col: number) {
        super(`Invalid token at ${row}, ${col}: ${lexeme}`);
    }
}
