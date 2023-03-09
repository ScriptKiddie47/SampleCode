/* eslint-disable @typescript-eslint/no-empty-function */
import * as dbcon from "../src/services/database.service";
import { MongoClient } from "mongodb";

// eslint-disable-next-line @typescript-eslint/no-empty-function

describe("DB Connection", () => {
  test("DB Connection is a Success", async () => {
    
    const mongoConnect = jest.spyOn(MongoClient.prototype, "connect");
    mongoConnect.mockImplementation(() => {});
    await dbcon.connectToDatabase();
  });
});
