import { Router } from "express";
import parser from "../controllers/parser.controller.js";

const router = Router();

router.post("/parse", parser);

export default router;