import { connectToDatabase } from "./services/database.service";
import { Context, APIGatewayProxyResult, APIGatewayEvent } from 'aws-lambda';
import { getGrades } from "./router/grades.router";
import { logger } from "./winstonLogger";
import Grades from "./models/grades";


export const handler = async (event: APIGatewayEvent, context: Context): Promise<APIGatewayProxyResult> => {
    logger.debug(`Event: ${JSON.stringify(event, null, 2)}`);
    logger.debug(`Context: ${JSON.stringify(context, null, 2)}`);
    await connectToDatabase();
    const grades : Grades = await getGrades();
    return {
        statusCode: 200,
        body: JSON.stringify({
            message: grades,
        }),
    };
};