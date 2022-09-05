{ "principalId": "secretToken", "policyDocument": { "Version": "2012-10-17", "Statement": [{"Action": "execute-api:Invoke", "Resource": ["arn:aws:execute-api:ap-south-1:378475259575:apiId/dev/GET/get-company-files"], "Effect": auth}] }}

exports.handler = async (event) => {
  console.log(event);
  let response = {};
  let auth = "deny";
  if (event.authorizationToken === "secretToken") {
    auth = "allow";
  }

  response = {
    principalId: "secretToken",
    policyDocument: {
      Version: "2012-10-17",
      Statement: [
        {
          Action: "execute-api:Invoke",
          Resource: [
            "arn:aws:execute-api:ap-south-1:378475259575:apiId/*/*",
          ],
          Effect: auth,
        },
      ],
    },
  };

  return response;
};
