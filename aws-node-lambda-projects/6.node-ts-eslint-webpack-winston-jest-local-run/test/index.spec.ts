import { Context, APIGatewayEvent } from "aws-lambda";
import  *  as index from "../src/index";
import { IUser } from "../src/models/users";
import * as  findUsers  from "../src/router/getUser.router";

// const eventJson = {"level":"info","message":{"body":null,"headers":{"Accept":"*/*","Accept-Encoding":"gzip, deflate, br","Authorization":"Bearer eyJraWQiOiJZNXo1STJ6ZDc0Tm9KbWV3NGwyZHh3WWRjWlwvWDRcL2VLT1ZMXC9PSXNtcno4PSIsImFsZyI6IlJTMjU2In0.eyJzdWIiOiI0OWwxZTNsN2Rrb3Bjb2YxNGQ4b2t1djF2MCIsInRva2VuX3VzZSI6ImFjY2VzcyIsInNjb3BlIjoiY29tcGFueS11c2VyLXBvb2wtcnNcL3JlYWQiLCJhdXRoX3RpbWUiOjE2NzgyOTcxOTcsImlzcyI6Imh0dHBzOlwvXC9jb2duaXRvLWlkcC5hcC1zb3V0aC0xLmFtYXpvbmF3cy5jb21cL2FwLXNvdXRoLTFfTVRJTFNrSlVaIiwiZXhwIjoxNjc4MzAwNzk3LCJpYXQiOjE2NzgyOTcxOTcsInZlcnNpb24iOjIsImp0aSI6ImZmYzViMWZhLTY3MjktNDk3Mi04YWY4LTMzYTZiODE0YWQ4ZCIsImNsaWVudF9pZCI6IjQ5bDFlM2w3ZGtvcGNvZjE0ZDhva3V2MXYwIn0.iPNSZJA5dUyqJvxCHrAx-KvPKoA8uCsZ85Vl20FDUWHXvbF9JC6CEd-eXUaY2aatwQen5et6oYwbvjkAZZx9zi8AxGHmx_YB7xDtWailwN0jHJZnTSmBNK_jtaJOUM_hyKAW49OZ5r5LO47_Y_Q8rXnnwwbNrBXQ-5qgwofG57L8MYhrP4GU64N4kl-yoEruEGqeMyC4x5TEY2BB0UWbYWZDY779safG1-ERj0OX2WkK0vxMA-R8hryMljP0nOJT-dCXyqwQBFexhy44yPB3qo9R6PEAs4pHiDud6z3aZD6X_PkQP3x2E5NNVKgzv7rXbmhZm9WHdcfOzLy7AzkVbw","Host":"a5vnegfola.execute-api.ap-south-1.amazonaws.com","Postman-Token":"a4ecf57e-dede-4f98-a648-9bcb88900e8a","User-Agent":"PostmanRuntime/7.31.1","X-Amzn-Trace-Id":"Root=1-6408c917-109527b763dbd52e07e2678c","X-Forwarded-For":"202.78.236.166","X-Forwarded-Port":"443","X-Forwarded-Proto":"https"},"httpMethod":"GET","isBase64Encoded":false,"multiValueHeaders":{"Accept":["*/*"],"Accept-Encoding":["gzip, deflate, br"],"Authorization":["Bearer eyJraWQiOiJZNXo1STJ6ZDc0Tm9KbWV3NGwyZHh3WWRjWlwvWDRcL2VLT1ZMXC9PSXNtcno4PSIsImFsZyI6IlJTMjU2In0.eyJzdWIiOiI0OWwxZTNsN2Rrb3Bjb2YxNGQ4b2t1djF2MCIsInRva2VuX3VzZSI6ImFjY2VzcyIsInNjb3BlIjoiY29tcGFueS11c2VyLXBvb2wtcnNcL3JlYWQiLCJhdXRoX3RpbWUiOjE2NzgyOTcxOTcsImlzcyI6Imh0dHBzOlwvXC9jb2duaXRvLWlkcC5hcC1zb3V0aC0xLmFtYXpvbmF3cy5jb21cL2FwLXNvdXRoLTFfTVRJTFNrSlVaIiwiZXhwIjoxNjc4MzAwNzk3LCJpYXQiOjE2NzgyOTcxOTcsInZlcnNpb24iOjIsImp0aSI6ImZmYzViMWZhLTY3MjktNDk3Mi04YWY4LTMzYTZiODE0YWQ4ZCIsImNsaWVudF9pZCI6IjQ5bDFlM2w3ZGtvcGNvZjE0ZDhva3V2MXYwIn0.iPNSZJA5dUyqJvxCHrAx-KvPKoA8uCsZ85Vl20FDUWHXvbF9JC6CEd-eXUaY2aatwQen5et6oYwbvjkAZZx9zi8AxGHmx_YB7xDtWailwN0jHJZnTSmBNK_jtaJOUM_hyKAW49OZ5r5LO47_Y_Q8rXnnwwbNrBXQ-5qgwofG57L8MYhrP4GU64N4kl-yoEruEGqeMyC4x5TEY2BB0UWbYWZDY779safG1-ERj0OX2WkK0vxMA-R8hryMljP0nOJT-dCXyqwQBFexhy44yPB3qo9R6PEAs4pHiDud6z3aZD6X_PkQP3x2E5NNVKgzv7rXbmhZm9WHdcfOzLy7AzkVbw"],"Host":["a5vnegfola.execute-api.ap-south-1.amazonaws.com"],"Postman-Token":["a4ecf57e-dede-4f98-a648-9bcb88900e8a"],"User-Agent":["PostmanRuntime/7.31.1"],"X-Amzn-Trace-Id":["Root=1-6408c917-109527b763dbd52e07e2678c"],"X-Forwarded-For":["202.78.236.166"],"X-Forwarded-Port":["443"],"X-Forwarded-Proto":["https"]},"multiValueQueryStringParameters":null,"path":"/fetch","pathParameters":null,"queryStringParameters":null,"requestContext":{"accountId":"378475259575","apiId":"a5vnegfola","authorizer":{"claims":{"auth_time":"1678297197","client_id":"49l1e3l7dkopcof14d8okuv1v0","exp":"Wed Mar 08 18:39:57 UTC 2023","iat":"Wed Mar 08 17:39:57 UTC 2023","iss":"https://cognito-idp.ap-south-1.amazonaws.com/ap-south-1_MTILSkJUZ","jti":"ffc5b1fa-6729-4972-8af8-33a6b814ad8d","scope":"company-user-pool-rs/read","sub":"49l1e3l7dkopcof14d8okuv1v0","token_use":"access","version":"2"}},"domainName":"a5vnegfola.execute-api.ap-south-1.amazonaws.com","domainPrefix":"a5vnegfola","extendedRequestId":"BeRbuG7dBcwFpXg=","httpMethod":"GET","identity":{"accessKey":null,"accountId":null,"caller":null,"cognitoAuthenticationProvider":null,"cognitoAuthenticationType":null,"cognitoIdentityId":null,"cognitoIdentityPoolId":null,"principalOrgId":null,"sourceIp":"202.78.236.166","user":null,"userAgent":"PostmanRuntime/7.31.1","userArn":null},"path":"/dev-1/fetch","protocol":"HTTP/1.1","requestId":"e3073355-fcb7-418b-82b2-055889966718","requestTime":"08/Mar/2023:17:42:47 +0000","requestTimeEpoch":1678297367515,"resourceId":"yzk695","resourcePath":"/fetch","stage":"dev-1"},"resource":"/fetch","stageVariables":null}}
const mongoJsonResponse : IUser[] = [{"name":"John Doe"},{"name":"Quick Shot"},{"name":"Death Shotter"}];
const event: APIGatewayEvent = {
    queryStringParameters: {
        a: "1"
    }
} as any;
const context : Context = {

    "callbackWaitsForEmptyEventLoop": true,
    "functionVersion": "$LATEST",
    "functionName": "webpack-test1",
    "memoryLimitInMB": "128",
    "logGroupName": "/aws/lambda/webpack-test1",
    "logStreamName": "2023/03/07/[$LATEST]3c4b7279f0974b69b932bae59b1c5539",
    "invokedFunctionArn": "arn:aws:lambda:ap-south-1:378475259575:function:webpack-test1",
    "awsRequestId": "ebc4dfc1-5a1a-41ce-8e8a-bdc85a191f9e"
} as any;


describe('This test actually runs the whole service locally', function () {
    it('Assert successful response', async () => {
        const result = await index.handler(event,context);
        expect(result.statusCode).toEqual(200);
        expect(result.body).toEqual(`{"message":["John Doe","Quick Shot","Death Shotter"]}`);
    });
});

describe('Mock Response Data', function () {
    it('Assert successful response', async () => {

        const findUsersmock = jest.spyOn(findUsers,"findUsers");
        findUsersmock.mockResolvedValue(mongoJsonResponse);

        const result = await index.handler(event,context);
        expect(result.statusCode).toEqual(200);
        expect(result.body).toEqual(`{"message":["John Doe","Quick Shot","Death Shotter"]}`);
    });
});