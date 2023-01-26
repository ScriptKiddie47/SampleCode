// External Dependencies
import { ObjectId } from "mongodb";
import { collections } from "../services/database.service";
import Grades from "../models/grades";
import { logger } from "../winstonLogger";

export async function getGrades() {
  let grades !: Grades; //Definite Assignment Assertions
  try {
    const query = { _id: new ObjectId("63a2e5d37034eb0eb2b460b6") };
    grades = (await collections.grades?.findOne(query)) as unknown as Grades
    logger.debug(`grades : ${JSON.stringify(grades)}`);
  } catch (error) {
    console.error(error);
  }
  return grades;
}