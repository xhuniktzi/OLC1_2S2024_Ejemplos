import { TokenLocation } from '@ts-jison/common';
import Context from '../Context/Context.js';

export default interface Expression {
    location: TokenLocation;
    interpret: (ctx: Context) => any;
}
