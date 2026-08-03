Compute

EC2 – Virtual servers in the cloud
Lambda – Serverless, event-driven functions (no server management)
ECS / EKS – Container orchestration (ECS = AWS-native, EKS = Kubernetes)
Elastic Beanstalk – PaaS; deploy apps without managing infrastructure


Storage

S3 – Object storage (files, images, backups)
EBS – Block storage attached to EC2 (like a hard drive)
EFS – Shared file system across multiple EC2s
Glacier – Archival/cold storage, cheap but slow retrieval


Database

RDS – Managed relational DB (MySQL, PostgreSQL, etc.)
Aurora – AWS-optimized relational DB, highly available
DynamoDB – Managed NoSQL key-value/document store
ElastiCache – In-memory caching (Redis / Memcached)
Redshift – Data warehouse for analytics


Networking

VPC – Isolated virtual network in AWS
Route 53 – DNS and domain routing
CloudFront – CDN for low-latency content delivery
API Gateway – Managed API endpoint layer
ELB – Load balancer (ALB, NLB, CLB variants)


Messaging / Streaming

SQS – Managed message queue (decouples services)
SNS – Pub/Sub notification service
Kinesis – Real-time data streaming (like Kafka)
EventBridge – Event bus for routing events between services


Security & IAM

IAM – Identity & access management (users, roles, policies)
KMS – Key management for encryption
Secrets Manager – Store and rotate secrets/credentials
WAF – Web Application Firewall
Shield – DDoS protection


Monitoring & DevOps

CloudWatch – Metrics, logs, and alarms
CloudTrail – Audit log of all API activity
CodePipeline / CodeDeploy – CI/CD tooling
CloudFormation – Iructure as Code (IaC)