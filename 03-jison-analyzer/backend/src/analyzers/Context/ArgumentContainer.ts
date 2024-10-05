import Expression from "../Expressions/Expression.js";

export default class ArgumentContainer {
    
    constructor(
        public name: string, 
        public expr: Expression){}
    
}