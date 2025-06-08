# GitHub Actions

1. File Breakdown


```yaml
name: Java CI with Gradle

on:
  push:
    branches: [ "main" ] # Easy to Understand
  pull_request:
    branches: [ "main" ] # Easy to Understand

jobs: # Groups Set of Actions that will be executed.
  build:

    runs-on: ubuntu-latest # Run on Image
    permissions:
      contents: read

    steps:
    - uses: actions/checkout@v4 # 'actions' hosts predefined functions. V4 is the version
    - name: Set up JDK 17
      uses: actions/setup-java@v4
      with:
        java-version: '17'
        distribution: 'temurin'

    - name: Setup Gradle
      uses: gradle/actions/setup-gradle@af1da67850ed9a4cedd57bfd976089dd991e2582 # v4.0.0 

    - name: Build with Gradle Wrapper
      run: ./gradlew build


  dependency-submission:

    runs-on: ubuntu-latest
    permissions:
      contents: write

    steps:
    - uses: actions/checkout@v4
    - name: Set up JDK 17
      uses: actions/setup-java@v4
      with:
        java-version: '17'
        distribution: 'temurin'

    # Generates and submits a dependency graph, enabling Dependabot Alerts for all project dependencies.
    # See: https://github.com/gradle/actions/blob/main/dependency-submission/README.md
    - name: Generate and submit dependency graph
      uses: gradle/actions/dependency-submission@af1da67850ed9a4cedd57bfd976089dd991e2582 # v4.0.0

```

### Events that trigger workflow

1. https://docs.github.com/en/actions/writing-workflows/choosing-when-your-workflow-runs/events-that-trigger-workflows

### GitHub Action

1. https://github.com/actions
2. We have used : https://github.com/actions/setup-java


### Setps 

1. `uses` - selects an actions
1. `run` - Runs a command line command