# Certification Name

1. GH-300 - GitHub Copilot - English

# Study Guide

1. Offical Doc : https://learn.microsoft.com/en-us/credentials/certifications/resources/study-guides/gh-300
1. GitHub Copilot Fundamentals Part 1 of 2 : https://learn.microsoft.com/en-us/training/paths/copilot/
1.

## Responsible AI

1. <span style="color: red;">AI Risk</span> : One of the primary concerns is that AI systems can sometimes make decisions that are difficult to interpret, leading to a lack of transparency and accountability. Additionally, AI can result in unintended and harmful outcomes, such as biased decision-making or privacy violations.
1. <span style="color: orange;">Responsible AI</span> is an approach to developing, assessing, and deploying artificial intelligent systems in a safe, trustworthy, and ethical way.Responsible AI can help proactively guide these decisions toward more beneficial and equitable outcomes

### 6 Principles

1. Fairness: AI systems should treat all people fairly.
1. Reliability and safety: AI systems should perform reliably and safely.
1. Privacy and security: AI systems should be secure and respect privacy.
1. Inclusiveness: AI systems should empower everyone and engage people.
1. Transparency: AI systems should be understandable.
1. Accountability: People should be accountable for AI systems.

## Introduction to GitHub Copilot

1. GitHub Copilot uses <span style="color: green;">OpenAI Codex</span> to suggest code and entire functions in real time, right from your editor.
1. OpenAI created the generative pretrained language model in GitHub Copilot, powered by OpenAI Codex. An extension is available for <span style="color: orange;">Visual Studio Code (VS Code), Visual Studio, Neovim, and the JetBrains suite of integrated development environments (IDEs)</span> .

### GitHub Copilot features

1. Copilot for chat
1. Copilot for pull requests
1. Copilot for the CLI

### Subscription plans

1. <span style="color: green;">GitHub Copilot Free </span> : GitHub Copilot Free allows individual developers to use GitHub Copilot at no cost.
   - The GitHub Copilot Free tier includes 2000 code completions per month, 50 chat requests per month, and access to both GPT-4o and Claude 3.5 Sonnet models
1. <span style="color: green;">GitHub Copilot Business </span> : GitHub Copilot Business allows you to control who can use GitHub Copilot in your company. After you give access to an organization, its admins can give access to individuals and teams.

   - Code completion
   - Chat in IDE and mobil
   - Filter for security vulnerabilitie
   - Code referencin
   - Filter for public cod
   - IP indemnit
   - Enterprise-grade security, safety, and privacy
   - VPN proxy support
   - Simple sign-up

1. <span style="color: green;">GitHub Copilot Enterprise</span> : GitHub Copilot Enterprise is available for organizations through GitHub Enterprise Cloud. This subscription plan enables your teams of developers to:

   - Quickly get up to speed on your codebase.
   - Search through and build documentation.
   - Get suggestions based on internal and private code.
   - Quickly review pull requests.
   - Create Kowledge base
   - Fine tune a private,custom model built on Company Knowledge Base.
   - Docsets are private custom collections of internal code and documentation tailored to organizations' specific needs and workflows.

1. GitHub Copilot Enterprise includes everything in GitHub Copilot Business, plus a layer of personalization for organizations. It provides integration into GitHub as a chat interface, so developers can converse about their codebase. It also provides action buttons throughout the platform.

### Modes
<span style="color: red;">
GitHub Copilot's Chat view provides three modes that can be used to create unit tests: Ask, Edit, and Agent. Each mode has its own strengths and weaknesses, and the best mode to use depends on the specific task at hand. The ask mode is optimized for asking questions about your code projects, coding topics, and general technology concepts. The edit mode is optimized for making edits across multiple files in your codebase. The agent mode is optimized for starting an agentic coding workflow.</span>

### Interact with Copilot

In GitHub Copilot, <span style="color: orange;"> ghost text </span> refers to the light, semi-transparent (gray and often italicized) inline code suggestions that appear as you type in your code editor

1.  Inline suggestions
    1. To accept a suggestion, select the Tab key or the > (right arrow) key.
    1. To reject a suggestion, keep typing or select the Esc key.
1.  Command palette - Open the command palette in Visual Studio Code by selecting Ctrl+Shift+P
1.  Copilot chat - Copilot chat is an interactive feature that enables you to communicate with Copilot by using natural language.
1.  Inline chat - Inline chat enables context-specific conversations with Copilot directly within your code editor.Here are some common slash commands and their usage:

    1. /doc: Adds comments to the specified or selected code.
    1. /explain: Gets explanations about the code.
    1. /generate: Generates code to answer the specified question.
    1. /help: Gets help on how to use Copilot chat.
    1. /optimize: Analyzes and improves the runtime of the selected code.
    1. /tests: Creates unit tests for the selected code.

1.  Comments to code - Copilot uses natural language processing to convert comments into code
1.  Muliple suggestions - For complex code snippets, Copilot can offer multiple alternatives.

    1. When Copilot offers a suggestion, look for the light bulb icon.
    1. Select the icon or use Alt+] (Windows/Linux) or Option+] (Mac) to cycle through alternatives.

1.  <span style="color: orange;">Agents</span> : Visual Studio Code has a feature called agents that allows you to interact with GitHub Copilot. These agents allow you to ask questions using a specific context. For example the @terminal agent helps you chat with GitHub Copilot to interact with the terminal.Another agent is @workspace, which is aware of your entire workspace. It allows you to ask questions about the entire project

    1.  @terminal: Provides suggestions based on the terminal output.
        1. Example: @terminal How do I fix the error message I'm seeing?
    1.  @file: Focuses on the content of a specific file.
        1. Example: @file Can you help me refactor this function in main.py?
    1.  @directory: Considers the contents of a specific directory.
        1. Example: @directory How can I optimize the scripts in the utils directory?

1.  <span style="color: orange;">Understanding comment context</span> : Natural Language Processing , Contextual Analysis. Types of comments utilized :

    1. Inline comments: Short explanations next to specific lines of code.
    1. Block comments: Longer explanations that might describe a function or class.
    1. Docstrings: Formal documentation strings in languages like Python.
    1. TODO comments: Notes about future implementations or improvements.
    1. API Documentation: Comments that describe the usage and parameters of functions or methods.

1.  GitHub Copilot Chat ( Inline + Separate Chat ) is particularly beneficial in certain scenarios :
1.  Complex code generation
1.  Debugging assistance
1.  Code explanations

1.  <span style="color: orange;"> How to improve GitHub Copilot Chat responses</span> :

    1. Scope referencing :
       - File references : You can specify a particular file in your question by adding a #file: before the file name
       - Environment References: You can reference the entire solution or workspace by using @workspace
    1. Slash commands.
    1. Agents.

1.  Sharing feedback on GitHub Copilot Chat : Thumbs up or Thumbs down

### Set up, configure, and troubleshoot GitHub Copilot

1. To get started, select your GitHub profile photo, and then select Settings. Copilot is on the left menu under Code, planning, and automation.
1. Enable or disable GitHub Copilot in VS Code - On the bottom pane of the VS Code window, select the status icon, and then select Enable or Disable. ( This doesn't work anymore )
1. Enable or disable inline suggestions in VS Code -
   1. On the File menu, select Preferences > Settings.
   1. On the left-side pane of the Settings tab, select Extensions, and then select GitHub Copilot.
   1. Under Editor: Enable Auto Completions, select or clear the checkbox to enable or disable inline suggestions.
1. Troubleshoot GitHub Copilot in VS Code:
   1. You can find the log files by opening the command palette and then entering either Developer: Open Log File or Developer: Open Extensions Logs Folder.
   1. Process enables you to view the Electron logs. You can find these logs by selecting Help > Toggle Developer Tools in VS Code.
   1. VS Code command palette -> Enter Diagnostics, and then select GitHub Copilot: Collect Diagnostics from the list.

## Prompt Engineering

1. Prompt engineering is the process of crafting clear instructions to guide AI systems, like GitHub Copilot, to generate context-appropriate code tailored to your project's specific needs. This ensures the code is syntactically, functionally, and contextually correct.
1. 4s Principles of prompt engineering:

   1. Single: Always focus your prompt on a single, well-defined task or question. This clarity is crucial for eliciting accurate and useful responses from Copilot.
   1. Specific: Ensure that your instructions are explicit and detailed. Specificity leads to more applicable and precise code suggestions.
   1. Short: While being specific, keep prompts concise and to the point. This balance ensures clarity without overloading Copilot or complicating the interaction.
   1. Surround: Utilize descriptive filenames and keep related files open. This provides Copilot with rich context, leading to more tailored code suggestions.

1. Best practices in prompt engineering:

   1. Provide enough clarity
   1. Provide enough context with details
   1. Provide examples for learning
   1. Assert and iterate

1. How Copilot learns from your prompt
   1. <span style="color: orange;">Zero-shot learning </span> : GitHub Copilot generates code without any specific examples, relying solely on its foundational training
   1. <span style="color: orange;">One-shot learning </span> : With this approach, a single example is given, aiding the model in generating a more context-aware response
   1. <span style="color: orange;">Few-shot learning</span> : In this method, Copilot is presented with several examples, which strike a balance between zero-shot unpredictability and the precision of fine-tuning
1. GitHub Copilot user prompt process flow

   1. Inbound flow : Code Editor -> Proxy Server -> Toxicity filtering -> Code generation with LLM
   1. Outbound flow : LLM -> Proxy Server -> Toxicity filtering -> Code Editor

1. <span style="color: orange;">Proxy filter </span> : Once the context is gathered and the prompt is built, it passes securely to a proxy server hosted in a GitHub-owned Microsoft Azure tenant. The proxy filters traffic, blocking attempts to hack the prompt or manipulate the system into revealing details about how the model generates code suggestions.
1. <span style="color: orange;">Toxicity filtering</span> : Hate speech and inappropriate content, Personal data

### GitHub Copilot data

1. Data handling for GitHub Copilot code suggestions - GitHub Copilot in the code editor does not retain any prompts like code or other context used for the purposes of providing suggestions to train the foundational models. It discards the prompts once a suggestion is returned.
1. Data handling for GitHub Copilot chat - Formatting , User engagement , Data retention ( For Copilot Chat used outside the code editor, GitHub typically retains prompts, suggestions, and supporting context for 28 days. )
1. Prompt types supported by GitHub Copilot Chat

   1. Direct Questions: You can ask specific questions about coding concepts, libraries, or troubleshooting issues. For example, "How do I implement a quick sort algorithm in Python?" or "Why is my React component not rendering?"
   1. Code-Related Requests: You can request code generation, modification, or explanation. Examples include "Write a function to calculate factorial," "Fix this error in my code," or "Explain this code snippet."
   1. Open-Ended Queries: You can explore coding concepts or seek general guidance by asking open-ended questions like "What are the best practices for writing clean code?" or "How can I improve the performance of my Python application?"
   1. Contextual Prompts: You can provide code snippets or describe specific coding scenarios to seek tailored assistance. For instance, "Here's a part of my code, can you suggest improvements?" or "I'm building a web application, can you help me with the authentication flow?"

1. <span style="color: orange;"> Limited context windows - GitHub Copilot's context window typically ranges from approximately 200-500 lines of code or up to a few thousand tokens. Copilot Chat currently operates with a context window of 4k tokens, providing a broader scope for understanding and responding to user queries compared to the standard Copilot.</span>

### GitHub Copilot Large Language Models

1. Large Language Models (LLMs) are artificial intelligence models designed and trained to understand, generate, and manipulate human language.
1. Fine-tuning is a critical process that allows us to tailor pretrained large language models (LLMs) for specific tasks or domains
1. <span style="color: orange;"> LoRA fine-tuning </span> - Traditional full fine-tuning means to train all parts of a neural network, which can be slow and heavily reliant on resources. But LoRA (Low-Rank Adaptation) fine-tuning is a clever alternative

   1. Here's how LoRA works:
      1. LoRA adds smaller trainable parts to each layer of the pretrained model, instead of changing everything.
      1. The original model remains the same, which saves time and resources.
   1. What's great about LoRA:
      1. It beats other adaptation methods like adapters and prefix-tuning.
      1. It's like getting great results with fewer moving parts.

### GitHub Copilot for the Command Line

1. Common commands:
   1. gh copilot explain "sudo apt-get"
   1. gh copilot suggest "Undo the last commit"
   1. ghcs suggest "What command to see running docker containers"
1. Configuration options
   1. Create Alias
   1. Data handling : GitHub Copilot CLI doesn't retain your prompts, but it keeps your usage analytics. You can configure whether you want GitHub Copilot to keep and use your usage data to improve the product

### Copilot Excellent Supported Languages

1. Python
1. JavaScript
1. Java
1. TypeScript
1. Ruby
1. Go
1. C#
1. C++

## Management and customization considerations with GitHub Copilot

1. <span style="color: red;"> Contractual protections </span>

   1. IP indemnity: The GitHub Copilot Business and Enterprise plans include IP indemnity, which provides legal protection against intellectual property claims related to the use of Copilot suggestions.
   1. Data Protection Agreement (DPA): GitHub offers a DPA that outlines the measures taken to protect your data and ensure compliance with data privacy regulations. These agreements provide transparency and assurance that your data is handled securely and responsibly.
   1. GitHub Copilot Trust Center: The GitHub Copilot Trust Center provides detailed information about how GitHub Copilot works, including security, privacy, compliance, and intellectual property safeguards. This resource helps organizations feel confident using GitHub Copilot while adhering to best practices and legal requirements.

1. <span style="color: red;"> Filtering out matching public code </span> : GitHub Copilot can help minimize potential code overlap by identifying and filtering out code suggestions that match publicly available code. This feature is essential for maintaining the originality and security of your codebase. It can reduce the risk of incorporating nonsecure or noncompliant code into your projects
1. Manage content exclusions - The content exclusion feature in GitHub Copilot helps protect sensitive information by preventing the use of specific files, directories, or repositories to inform code-completion suggestions. Configurations for content exclusion
   1. Configure content exclusions for repositories
   1. Configure content exclusions for organizations
1. Impact of content exclusion on code suggestions - You can use content exclusions to configure GitHub Copilot to ignore certain files.

   1. Code completion is no longer available in the affected files.
   1. The content in affected files won't inform code completion suggestions in other files.
   1. The content in affected files won't inform GitHub Copilot Chat responses.
1. Limitations of content exclusions :

    1. IDE limitations: In some integrated development environments (IDEs), content exclusions might not apply when you're using certain features, such as Copilot Chat. For example, in Visual Studio Code and Visual Studio, content exclusions are not applied when you use the @github chat participant in your question.
    1. Semantic information: Copilot might still use semantic information from an excluded file if the IDE provides the information in a nonexcluded file. This includes type information and hover-over definitions for symbols or function calls used in code.
    1. Policy scope: Content exclusion settings apply only to members of the organization in which you configure the content exclusion. Anyone else who can access the specified files can still see code completion suggestions and Copilot Chat responses referencing the specified files.

1. Content exclusions aren't working as expected :
   1. Delayed application of exclusions : Policy change takes 30 mins
   1. <span style="color: red;"> Inadequate scope of exclusions </span> : GitHub Copilot content exclusion applies to the file, the GitHub Copilot icon has a diagonal line through it
   1. IDE-specific limitations : 

1. Code suggestions are unsatisfactory : Provide clear context,Use Copilot commands, Adjust prompt length