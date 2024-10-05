import { Router } from "express";
import parser from "../controllers/parser.controller";

const router = Router();

router.get("/parse", parser);

export default router;