import { ObjectId } from "mongodb";

type Products = {
    products : [string,number]
}

export default class Grades {
  constructor(
    public student_id: number,
    public class_id : number,
    public products : Products,
    public id?: ObjectId
  ) {}
}
