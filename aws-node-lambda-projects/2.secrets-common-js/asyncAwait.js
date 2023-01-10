const {SecretsManagerClient,GetSecretValueCommand} = require('@aws-sdk/client-secrets-manager');

const fetchSecrets = async() => {
    const secret_name = "dev/myapp";

    const client = new SecretsManagerClient({
        region: "ap-south-1",
    });


    let responseSecret;

    try {
        responseSecret = await client.send(
            new GetSecretValueCommand({
                SecretId: secret_name,
                VersionStage: "AWSCURRENT", // VersionStage defaults to AWSCURRENT if unspecified
            })
        );
    } catch (error) {
        // For a list of exceptions thrown, see
        // https://docs.aws.amazon.com/secretsmanager/latest/apireference/API_GetSecretValue.html
        throw error;
    }

    const secret = responseSecret.SecretString;
    return secret;
}
exports.fetchSecrets = fetchSecrets;