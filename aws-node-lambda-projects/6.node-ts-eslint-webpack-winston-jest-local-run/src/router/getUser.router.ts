import { IUser } from "../models/users";
import { collections, connectToDatabase } from "../services/database.service";
import { logger } from "../winstonLogger";

export async function findUsers(): Promise<IUser[]> {
    await connectToDatabase();
    const filter = {
        'age': {
            '$lte': 30
        }
    };
    const projection = {
        'name': 1
    };
    // let grades !: Grades; //Definite Assignment Assertions
    let users: IUser[] | PromiseLike<IUser[]> = [];
    try {
        users = (await collections.grades?.find(filter, { projection }).toArray()) as unknown as IUser[];
        logger.debug(`User List : ${JSON.stringify(users)}`);
    } catch (error) {
        logger.error(error);
    }
    return users;
}