import { logger } from "../winstonLogger";

// External Dependencies
import * as mongoDB from "mongodb";
import * as dotenv from "dotenv";
dotenv.config();

// Global Variables
export const collections: { grades?: mongoDB.Collection } = {};

// Initialize Connection
export async function connectToDatabase(): Promise<void> {
  const db_con_string: string = process.env.DB_CONN_STRING || "";
  const collection_name: string = process.env.GRADES_COLLECTION_NAME || "";
  const client: mongoDB.MongoClient = new mongoDB.MongoClient(db_con_string);
  await client.connect();
  const db: mongoDB.Db = client.db(process.env.DB_NAME);
  const gamesCollection: mongoDB.Collection = db.collection(collection_name);
  collections.grades = gamesCollection;
  logger.info(
    `Successfully connected to database: ${db.databaseName} and collection: ${gamesCollection.collectionName}`
  );
}
