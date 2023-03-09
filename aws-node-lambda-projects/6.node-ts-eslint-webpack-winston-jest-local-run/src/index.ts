import { Context, APIGatewayProxyResult, APIGatewayEvent } from 'aws-lambda';
import { logger } from "./winstonLogger";
import { findUsers } from "./router/getUser.router";


export const handler = async (event: APIGatewayEvent, context: Context): Promise<APIGatewayProxyResult> => {
    logger.debug(`Event: ${JSON.stringify(event)}`);
    logger.debug(`Context: ${JSON.stringify(context)}`);
    // const grades : Grades = await getGrades();
    const users = await findUsers();
    logger.debug(`users : `, users);
    const resultList: string[] = [];
    users.forEach(e => {
        resultList.push(e.name);
    })
    return {
        statusCode: 200,
        body: JSON.stringify({
            // message: grades,
            message: resultList
        }),
    };
};