export default class ReturnEx extends Error {
    constructor(public value: any){
        super()
    }
}