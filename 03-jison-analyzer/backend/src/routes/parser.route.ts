import { Router } from "express";
import parser from "../controllers/parser.controller.js";

const router = Router();

router.get("/parse", parser);

export default router;