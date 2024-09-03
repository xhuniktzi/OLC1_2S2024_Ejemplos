import { Router } from "express";
import parser from "../controllers/parser.controller";

const router = Router();

router.post("/parse", parser);

export default router;