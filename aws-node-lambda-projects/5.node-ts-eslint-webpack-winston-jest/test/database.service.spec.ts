import { connectToDatabase } from "../src/services/database.service";

describe("DB Connection", () => {
  test("DB Connection is a Success", async () => {
    await connectToDatabase();
  });
});
