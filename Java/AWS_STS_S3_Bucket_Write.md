# AWS SDK Version 2.0 - STS & S3 Bucket

## TFE Code

```ps
terraform {
  required_providers {
    aws = {
      source  = "hashicorp/aws"
      version = "~> 5.92"
    }
  }

  required_version = ">= 1.2"
}


provider "aws" {
  region = "ap-south-1"
}

# 1. S3 Bucket Creation
resource "aws_s3_bucket" "s3_bucket" {
  bucket = "sbala-test-bucket"
}

# 2. S3 Write & Read Policy

resource "aws_iam_policy" "s3_read_write_policy" {
  name        = "sbala-s3-read-write-policy"
  description = "S3 Policy that includes read and write"

  # Terraform expression result to valid JSON syntax.
  policy = jsonencode({
    Version = "2012-10-17"
    Statement = [
      {
        Action = [
          "s3:PutObject",
          "s3:GetObject"
        ]
        Effect   = "Allow",
        Resource = ["${aws_s3_bucket.s3_bucket.arn}/*"]
      },
    ]
  })

  depends_on = [aws_s3_bucket.s3_bucket]
}

# 3. Create IAM Role - Trust policy (IAM user can assume role)

resource "aws_iam_role" "s3_read_write_role" {
  name = "sbala-s3-read-write-role"

  # Terraform's "jsonencode" function converts a
  # Terraform expression result to valid JSON syntax.
  assume_role_policy = jsonencode({
    Version = "2012-10-17"
    Statement = [
      {
        Action = "sts:AssumeRole"
        Effect = "Allow"
        Sid    = ""
        Principal = {
          AWS = "arn:aws:iam::378475259575:user/syndicate-user" # User here must be created in advance
        }
      },
    ]
  })

  tags = {
    tag-key = "tag-value"
  }
}

# 4. Attach s3_read_write_policy to s3_read_write_role 

resource "aws_iam_role_policy_attachment" "attach_s3_policy" {
  role       = aws_iam_role.s3_read_write_role.name
  policy_arn = aws_iam_policy.s3_read_write_policy.arn
}

# 5. Allow user to assume the role

resource "aws_iam_policy" "assume_role_policy" {
  name = "allow-assume-s3-role"

  policy = jsonencode({
    Version = "2012-10-17"
    Statement = [
      {
        Effect   = "Allow"
        Action   = "sts:AssumeRole"
        Resource = aws_iam_role.s3_read_write_role.arn
      }
    ]
  })
}

# 6. Attach Policy to User

resource "aws_iam_user_policy_attachment" "attach_assume_policy" {
  user       = "syndicate-user"
  policy_arn = aws_iam_policy.assume_role_policy.arn
}

# 7. S3 Bucket Policy 

resource "aws_s3_bucket_policy" "allow_read_write_role" {
  bucket = aws_s3_bucket.s3_bucket.id

  policy = jsonencode({
    Version = "2012-10-17"
    Statement = [
      {
        Effect = "Allow"
        Principal = {
          AWS = aws_iam_role.s3_read_write_role.arn
        }
        Action   = "s3:PutObject"
        Resource = ["${aws_s3_bucket.s3_bucket.arn}/*"]
      }
    ]
  })
}
```

## Java Code

```java
package org.example;

import software.amazon.awssdk.auth.credentials.*;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.sts.StsClient;
import software.amazon.awssdk.services.sts.model.AssumeRoleRequest;
import software.amazon.awssdk.services.sts.model.AssumeRoleResponse;
import software.amazon.awssdk.services.sts.model.Credentials;

import java.io.File;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class S3Upload {

    // First Lets Assume Role
    // Copy Role Arn from AWS Console
    String ROLE_ARN = "arn:aws:iam::378475259575:role/sbala-s3-read-write-role";

    // An identifier for the assumed role session. This can be anything
    String ROLE_SESSION_NAME = "s3-access-session";

    Region region = Region.AP_SOUTH_1;

    String ACCESS_KEY_ID  = "<USER_ACCESS_KEY_ID>";
    String SECRET_ACCESS_KEY = "<USER_SECRET_ACCESS_KEY_ID>";
    String S3_BUCKET_NAME = "sbala-test-bucket";
    String OBJECT_NAME = "lorem.txt";
    String OBJECT_LOCATION = "resources";


    public void uploadToS3() {
        try {
            // AWS Docs for STS :  https://docs.aws.amazon.com/sdk-for-java/latest/developer-guide/java_sts_code_examples.html
            // AWS Docs for STS & S3 Bucket :  https://docs.aws.amazon.com/sdk-for-java/latest/developer-guide/credentials-explicit.html
            AwsBasicCredentials awsBasicCredentials = AwsBasicCredentials
                    .create(ACCESS_KEY_ID,SECRET_ACCESS_KEY);
            StsClient stsClient = StsClient
                    .builder()
                    .region(region)
                    .credentialsProvider(StaticCredentialsProvider.create(awsBasicCredentials))
                    .build();
            AssumeRoleRequest assumeRoleRequest = AssumeRoleRequest
                    .builder()
                    .roleArn(ROLE_ARN)
                    .roleSessionName(ROLE_SESSION_NAME)
                    .build();
            AssumeRoleResponse assumeRoleResponse = stsClient
                    .assumeRole(assumeRoleRequest);
            Credentials credentials = assumeRoleResponse
                    .credentials();

            // Use the following temporary credential items for the S3 client.
            // The access key & secret access key is different from the USER ID defined above.

            String s3AccessKeyId = credentials.accessKeyId();
            String s3SecretAccessKeyId = credentials.secretAccessKey();
            String s3SessionToken = credentials.sessionToken();


            StaticCredentialsProvider staticCredentialsProvider = StaticCredentialsProvider
                    .create(AwsSessionCredentials.create(s3AccessKeyId,s3SecretAccessKeyId,s3SessionToken));

            S3Client s3Client = S3Client
                    .builder()
                    .credentialsProvider(staticCredentialsProvider)
                    .region(Region.AP_SOUTH_1)
                    .build();

            Map<String,String> metadata = new HashMap<>();
            metadata.put("author","Mary Doe");
            metadata.put("version","1.0.0");

            PutObjectRequest request = PutObjectRequest
                    .builder()
                    .bucket(S3_BUCKET_NAME)
                    .key(OBJECT_NAME)
                    .metadata(metadata)
                    .build();

            InputStream inputStream = getClass().getResourceAsStream("/" + OBJECT_NAME);

            if(inputStream == null) throw new RuntimeException(OBJECT_NAME + " not found in classpath");

            s3Client.putObject(request, RequestBody.fromInputStream(inputStream,inputStream.available()));
            System.out.println("Successfully placed : " + OBJECT_NAME + " in Bucket: " + S3_BUCKET_NAME);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

```

## Java File Structure

```ps
syndicate@pop-os:~/Documents/CodeSource/java-projects/s3-java-test/src
$ tree
.
├── main
│   ├── java
│   │   └── org
│   │       └── example
│   │           ├── Main.java
│   │           └── S3Upload.java
│   └── resources
│       └── lorem.txt
└── test
    ├── java
    └── resources
```

