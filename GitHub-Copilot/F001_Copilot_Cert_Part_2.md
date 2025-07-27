## Developer Use Case

### AI in the Software Development Lifecycle ( SDLC )

1. Requirement analysis
1. Design & development
1. Testing & quality assurance
1. Deployment
1. Maintenance & support

### Understand limitations and measure impact

1. Code quality and correctness
1. Language and framework specificity - GitHub Copilot's effectiveness can vary across different programming languages and frameworks. Niche technologies
1. Dependency on training data - Bias, Copyright claims
1. Complex problem solving - Limitation in high-level design,Creativity constraints

### Measure productivity gains

1. Use the REST API endpoints for GitHub Copilot usage metrics
   1. GitHub provides a REST API to access GitHub Copilot usage metrics for enterprise members, teams, and organization members. These metrics offer insights into daily usage of GitHub Copilot, including completions, chat interactions, and user engagement across different editors and languages.
   1. Get a summary of GitHub Copilot usage for enterprise members
      - GET /enterprises/{enterprise}/GitHub Copilot/usage
   1. Get a summary of GitHub Copilot usage for an enterprise team
      - GET /enterprises/{enterprise}/team/{team_slug}/GitHub Copilot/usage
   1. Get a summary of GitHub Copilot usage for organization members
      - GET /orgs/{org}/GitHub Copilot/usage
   1. Implementing a measurement framework
      1. To systematically assess GitHub Copilot’s impact, consider the following framework, using the GitHub Copilot usage metric API at each stage
         - Evaluation
         - Adoption
         - Optimization
         - Sustained efficiency
1. GitHub Copilot Developer Survey
   1. Survey cadence and timing
   1. Structuring the survey
   1. Analyzing survey results - Ensure that survey responses are anonymized ,Collate the survey responses into existing Business Intelligence (BI) tools or spreadsheets
   1. Continuous improvement

## Testing

1. GitHub Copilot Chat can suggest test cases for error handling, null values, or unexpected input types.

### Write unit tests with GitHub Copilot

1. Chat view: Use the Chat view generate unit tests for a project, class, or method using Ask, Edit, or Agent mode.
1. Inline Chat: Use Inline Chat to generate unit tests for selected classes or methods.
1. Smart actions: Use the Generate Tests smart action to generate unit tests for selected code without writing a prompt.
1. Code line completions: Use code line completions to suggest addition unit tests for an existing test case.

### Set up your testing framework

1. To accelerate your testing workflow, Copilot can help set up the testing framework and VS Code extensions for your project. Enter the /setupTests command in the chat input field.

### Fix failing tests

1. /fixTestFailure

### C# Dev Kit support for unit tests

1. xUnit
1. NUnit
1. MSTest
