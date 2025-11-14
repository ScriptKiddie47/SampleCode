## Pydantic Object

1. Note : THIS IS USING THE OLD WAY OF AGENT CREATION.
1. 'langchain_classic' -> THIS IS DEPRECATED LIBRARY.
1. ONLY TAKEWAY IS THE USAGE OF PYDANTIC


    ```python
    from typing import List
    from pydantic import BaseModel, Field


    class Source(BaseModel):
        """Schema for a source used by the agent"""

        url: str = Field(description="The URL of the source")


    class AgentResponse(BaseModel):
        """Schema for agent response with Answer and Sources"""

        answer: str = Field(description="The agent's answer to the query")
        sources: List[Source] = Field(
            default_factory=list, description="List of sources used to generate the answer"
        )
    ```

## Prompt

```py
REACT_PROMPT_WITH_FORMAT_INSTRUCTION = """
Answer the following questions as best you can. You have access to the following tools:

{tools}

Use the following format:

Question: the input question you must answer
Thought: you should always think about what to do
Action: the action to take, should be one of [{tool_names}]
Action Input: the input to the action
Observation: the result of the action
... (this Thought/Action/Action Input/Observation can repeat N times)
Thought: I now know the final answer
Final Answer: the final answer to the original input question formatted according to format_instruction: {format_instruction}

Begin!

Question: {input}
Thought:{agent_scratchpad}
"""
```

## Invoke LLM for Structured Output [ OLD METHOD ]

1. Note : THIS IS USING THE OLD WAY OF AGENT CREATION.
1. 'langchain_classic' -> THIS IS DEPRECATED LIBRARY.
1. ONLY TAKEWAY IS THE USAGE OF PYDANTIC
1. We gave a prompt o LLM to give us the data structure in a particular way & hope for the best. This isn't the best approach.
1. We can see the instruction is the output log below

    ```py
    from dotenv import load_dotenv
    load_dotenv()

    from langchain_openai import ChatOpenAI
    from langchain_classic import hub
    from langchain_classic.agents import AgentExecutor
    from langchain_classic.agents.react.agent import create_react_agent
    from langchain_classic.prompts import PromptTemplate
    from langchain_tavily import TavilySearch
    from langchain_core.output_parsers.pydantic import PydanticOutputParser

    from prompt import REACT_PROMPT_WITH_FORMAT_INSTRUCTION
    from schemas import AgentResponse


    llm = ChatOpenAI(model="gpt-4o" )
    tools = [TavilySearch()]
    react_prompt = hub.pull("hwchase17/react")
    output_paser = PydanticOutputParser(pydantic_object=AgentResponse)

    print(f" FORMAT INSTRUCTION : {output_paser.get_format_instructions()}")
    # Pydantic generates an instruction set for us.

    react_prompt_with_format_instruction = PromptTemplate(
        template=REACT_PROMPT_WITH_FORMAT_INSTRUCTION,
        input_variables=["input","agent_scratchpad","tool_names"]
    ).partial(format_instruction=output_paser.get_format_instructions())

    agent = create_react_agent(
        llm =llm,
        tools = tools,
        prompt= react_prompt_with_format_instruction
    )

    agent_executor = AgentExecutor(agent=agent,tools=tools,verbose=True)
    chain = agent_executor


    def main():
        print("Hello from Langchain")
        result = chain.invoke(
            input={
                "input": "Search for 3 Job posting for an AI Engineer"
                "using Langchain in the bay area on Linkedin & list thier details",
            }
        )
        # print(result)
        print(f"{output_paser.parse(result['output'])}")
        print(f"-------------------------------------------------------")
        print(f"Answer : {output_paser.parse(result['output']).answer}")


    # This is Suposed to be fun


    if __name__ == "__main__":
        main()
    ```

1. Note : GPT-5 doesn't support 'stop' argument. So we go with `model="gpt-4o" `
1. Output 

    ```ps
    FORMAT INSTRUCTION : The output should be formatted as a JSON instance that conforms to the JSON schema below.

    As an example, for the schema {"properties": {"foo": {"title": "Foo", "description": "a list of strings", "type": "array", "items": {"type": "string"}}}, "required": ["foo"]}
    the object {"foo": ["bar", "baz"]} is a well-formatted instance of the schema. The object {"properties": {"foo": ["bar", "baz"]}} is not well-formatted.

    Here is the output schema:
    
    {"$defs": {"Source": {"description": "Schema for a source used by the agent", "properties": {"url": {"description": "The URL of the source", "title": "Url", "type": "string"}}, "required": ["url"], "title": "Source", "type": "object"}}, "description": "Schema for agent response with Answer and Sources", "properties": {"answer": {"description": "The agent's answer to the query", "title": "Answer", "type": "string"}, "sources": {"description": "List of sources used to generate the answer", "items": {"$ref": "#/$defs/Source"}, "title": "Sources", "type": "array"}}, "required": ["answer"]}
    
    Hello from Langchain


    > Entering new AgentExecutor chain...
    Action: tavily_search
    Action Input: "AI Engineer Langchain jobs Bay Area site:linkedin.com"{'query': 'AI Engineer Langchain jobs Bay Area', 'follow_up_questions': None, 'answer': None, 'images': [], 'results': [{'url': 'https://www.linkedin.com/jobs/view/ai-engineer-at-labelbox-4219025705', 'title': 'Labelbox hiring AI Engineer in San Francisco Bay Area', 'content': '[Skip to main content](https://www.linkedin.com/jobs/view/ai-engineer-at-labelbox-4219025705#main-content)[LinkedIn](https://www.linkedin.com/?trk=public_jobs_nav-header-logo) [Sign in](https://www.linkedin.com/login?emailAddress=&fromSignIn=&fromSignIn=true&session_redirect=https%3A%2F%2Fwww.linkedin.com%2Fjobs%2Fdrift-a-salesloft-company-jobs%3Ftrk%3Dexpired_jd_redirect&trk=public_jobs_nav-header-signin)[Join now](https://www.linkedin.com/signup/cold-join?source=jobs_registration&session_redirect=https%3A%2F%2Fwww.linkedin.com%2Fjobs%2Fdrift-a-salesloft-company-jobs%3Ftrk%3Dexpired_jd_redirect&trk=public_jobs_nav-header-join) *   [About](https://about.linkedin.com/?trk=public_jobs_footer-about) *   [Accessibility](https://www.linkedin.com/accessibility?trk=public_jobs_footer-accessibility) *   [User Agreement](https://www.linkedin.com/legal/user-agreement?trk=public_jobs_footer-user-agreement) *   [Privacy Policy](https://www.linkedin.com/legal/privacy-policy?trk=public_jobs_footer-privacy-policy) *   [Your California Privacy Choices](https://www.linkedin.com/legal/california-privacy-disclosure?trk=public_jobs_footer-california-privacy-rights-act) *   [Cookie Policy](https://www.linkedin.com/legal/cookie-policy?trk=public_jobs_footer-cookie-policy) *   [Copyright Policy](https://www.linkedin.com/legal/copyright-policy?trk=public_jobs_footer-copyright-policy) *   [Brand Policy](https://brand.linkedin.com/policies?trk=public_jobs_footer-brand-policy) *   [Guest Controls](https://www.linkedin.com/psettings/guest-controls?trk=public_jobs_footer-guest-controls) *   [Community Guidelines](https://www.linkedin.com/legal/professional-community-policies?trk=public_jobs_footer-community-guide) *    Bahasa Indonesia (Indonesian)  *    Bahasa Malaysia (Malay)  *    简体中文 (Chinese (Simplified))  *    正體中文 (Chinese (Traditional))  By clicking Continue to join or sign in, you agree to LinkedIn’s [User Agreement](https://www.linkedin.com/legal/user-agreement?trk=linkedin-tc_auth-button_user-agreement), [Privacy Policy](https://www.linkedin.com/legal/privacy-policy?trk=linkedin-tc_auth-button_privacy-policy), and [Cookie Policy](https://www.linkedin.com/legal/cookie-policy?trk=linkedin-tc_auth-button_cookie-policy). [](https://www.linkedin.com/jobs/view/ai-engineer-at-labelbox-4219025705) [Forgot password?](https://www.linkedin.com/uas/request-password-reset?trk=public_jobs_contextual-sign-in-modal_sign-in-modal_forgot_password) Sign in  By clicking Continue to join or sign in, you agree to LinkedIn’s [User Agreement](https://www.linkedin.com/legal/user-agreement?trk=public_jobs_contextual-sign-in-modal_sign-in-modal_auth-button_user-agreement), [Privacy Policy](https://www.linkedin.com/legal/privacy-policy?trk=public_jobs_contextual-sign-in-modal_sign-in-modal_auth-button_privacy-policy), and [Cookie Policy](https://www.linkedin.com/legal/cookie-policy?trk=public_jobs_contextual-sign-in-modal_sign-in-modal_auth-button_cookie-policy). [Join now](https://www.linkedin.com/signup/cold-join?source=jobs_registration&trk=public_jobs_contextual-sign-in-modal_sign-in-modal_join-link) [Join now](https://www.linkedin.com/signup/cold-join?source=jobs_registration&trk=public_jobs_contextual-sign-in-modal_join-link) By clicking Continue to join or sign in, you agree to LinkedIn’s [User Agreement](https://www.linkedin.com/legal/user-agreement?trk=linkedin-tc_auth-button_user-agreement), [Privacy Policy](https://www.linkedin.com/legal/privacy-policy?trk=linkedin-tc_auth-button_privacy-policy), and [Cookie Policy](https://www.linkedin.com/legal/cookie-policy?trk=linkedin-tc_auth-button_cookie-policy).', 'score': 0.99929035, 'raw_content': None}, {'url': 'https://www.linkedin.com/jobs/view/ai-engineer-at-avesta-computer-services-4331714520', 'title': 'Avesta Computer Services hiring AI Engineer in Fremont, CA', 'content': 'San Francisco Bay Area $130,000 - $180,000 1 week ago. Software Engineer, AI Platform - New Grad. Software Engineer, AI Platform - New Grad.See more', 'score': 0.9941801, 'raw_content': None}, {'url': 'https://www.linkedin.com/jobs/view/founding-ai-engineer-at-aimhire-4321153273', 'title': 'Aimhire hiring Founding AI Engineer in San Francisco Bay ...', 'content': 'Get notified about new Artificial Intelligence Engineer jobs in **San Francisco Bay Area**. ## Similar jobs * ### Senior Engineering Manager jobs 17,824 open jobs 114,435 open jobs 52,084 open jobs 1,965,194 open jobs 49,178 open jobs 81,749 open jobs 37,177 open jobs * ### Engineering Manager jobs 145,990 open jobs 5,878 open jobs 34,340 open jobs 690,514 open jobs * ### Solutions Engineer jobs 92,218 open jobs * ### System Engineer jobs * ### Engineer jobs * ### Senior Software Engineer jobs + Senior Engineering Manager jobs + Engineering Manager jobs + Solutions Engineer jobs + System Engineer jobs + Engineer jobs + Senior Software Engineer jobs + Software Engineer jobs + Manager jobs + Google jobs + Visa jobs', 'score': 0.9932025, 'raw_content': None}, {'url': 'https://www.linkedin.com/jobs/view/ai-engineer-relational-foundation-models-agentic-systems-at-kumo-4294635243', 'title': 'Kumo hiring AI Engineer - Relational Foundation Models & ...', 'content': 'San Francisco Bay Area 1 week ago. AI/ML Engineer. AI/ML Engineer. General Motors. Sunnyvale, CA $118,000 - $188,400 4 days ago. Machine Learning Engineer', 'score': 0.99135584, 'raw_content': None}, {'url': 'https://www.linkedin.com/jobs/view/ai-engineer-at-coderound-ai-4300325388', 'title': 'CodeRound AI hiring AI Engineer in San Francisco Bay Area', 'content': '### AI Engineer # AI Engineer Direct message the job poster from CodeRound AI Get notified about new Artificial Intelligence Engineer jobs in **San Francisco Bay Area**. * AI Engineer  ### AI Engineer * AI Engineer  * ### Senior Software Engineer Team Lead jobs * ### Modeling Engineer jobs * ### Computer Engineer jobs * ### System Designer jobs * ### Machine Learning Engineer jobs * ### Scientist jobs * ### Engineer jobs * ### Designer jobs + Senior Software Engineer Team Lead jobs + Modeling Engineer jobs + Computer Engineer jobs + System Designer jobs + Machine Learning Engineer jobs + Scientist jobs + Engineer jobs + Designer jobs + Java Software Engineer jobs + Senior Software Engineer jobs', 'score': 0.98667485, 'raw_content': None}], 'response_time': 0.89, 'request_id': 'da0f55c1-218f-4c26-8041-d07f3da13441'}I have found three job postings for AI Engineers using Langchain in the Bay Area from LinkedIn. Here are the details:

    1. **Labelbox - AI Engineer**
    - Location: San Francisco Bay Area
    - [LinkedIn Job URL](https://www.linkedin.com/jobs/view/ai-engineer-at-labelbox-4219025705)

    2. **Avesta Computer Services - AI Engineer**
    - Location: Fremont, CA
    - Salary: $130,000 - $180,000
    - [LinkedIn Job URL](https://www.linkedin.com/jobs/view/ai-engineer-at-avesta-computer-services-4331714520)

    3. **Aimhire - Founding AI Engineer**
    - Location: San Francisco Bay Area
    - [LinkedIn Job URL](https://www.linkedin.com/jobs/view/founding-ai-engineer-at-aimhire-4321153273)

    Final Answer: The requested job postings have been listed above, along with relevant details and URLs.
    ```
    {
    "answer": "I have found three job postings for AI Engineers using Langchain in the Bay Area from LinkedIn. Here are the details:\n\n1. Labelbox - AI Engineer\n   - Location: San Francisco Bay Area\n   - [LinkedIn Job URL](https://www.linkedin.com/jobs/view/ai-engineer-at-labelbox-4219025705)\n\n2. Avesta Computer Services - AI Engineer\n   - Location: Fremont, CA\n   - Salary: $130,000 - $180,000\n   - [LinkedIn Job URL](https://www.linkedin.com/jobs/view/ai-engineer-at-avesta-computer-services-4331714520)\n\n3. Aimhire - Founding AI Engineer\n   - Location: San Francisco Bay Area\n   - [LinkedIn Job URL](https://www.linkedin.com/jobs/view/founding-ai-engineer-at-aimhire-4321153273)",
    "sources": [
        {"url": "https://www.linkedin.com/jobs/view/ai-engineer-at-labelbox-4219025705"},
        {"url": "https://www.linkedin.com/jobs/view/ai-engineer-at-avesta-computer-services-4331714520"},
        {"url": "https://www.linkedin.com/jobs/view/founding-ai-engineer-at-aimhire-4321153273"}
    ]
    }
    ```

    > Finished chain.
    answer='I have found three job postings for AI Engineers using Langchain in the Bay Area from LinkedIn. Here are the details:\n\n1. Labelbox - AI Engineer\n   - Location: San Francisco Bay Area\n   - [LinkedIn Job URL](https://www.linkedin.com/jobs/view/ai-engineer-at-labelbox-4219025705)\n\n2. Avesta Computer Services - AI Engineer\n   - Location: Fremont, CA\n   - Salary: $130,000 - $180,000\n   - [LinkedIn Job URL](https://www.linkedin.com/jobs/view/ai-engineer-at-avesta-computer-services-4331714520)\n\n3. Aimhire - Founding AI Engineer\n   - Location: San Francisco Bay Area\n   - [LinkedIn Job URL](https://www.linkedin.com/jobs/view/founding-ai-engineer-at-aimhire-4321153273)' sources=[Source(url='https://www.linkedin.com/jobs/view/ai-engineer-at-labelbox-4219025705'), Source(url='https://www.linkedin.com/jobs/view/ai-engineer-at-avesta-computer-services-4331714520'), Source(url='https://www.linkedin.com/jobs/view/founding-ai-engineer-at-aimhire-4321153273')]
    ```

## USING LCEL ( Lanchain Expression Language ) [ OLD WAY ]

1. https://api.python.langchain.com/en/latest/core/runnables/langchain_core.runnables.base.RunnableLambda.html
1. Lightweight wrapper that wraps any python lambda function into a Runnable.
1. Great for integration custom logic.

    ```py
    agent_executor = AgentExecutor(agent=agent,tools=tools,verbose=True)
    extract_output = RunnableLambda(lambda x: x["output"])
    parse_output = RunnableLambda(lambda x: output_paser.parse(x))

    chain = agent_executor | extract_output | parse_output 


    def main():
        print("Hello from Langchain")
        result = chain.invoke(
            input={
                "input": "Search for 3 Job posting for an AI Engineer"
                "using Langchain in the bay area on Linkedin & list thier details",
            }
        )
        print(result)


    # The output is a hit and miss.
    ```
1. Note : GPT-5 doesn't support 'stop' argument. So we stick with `model="gpt-4o" `

## Structured Output Function with create_agent [ Current Best Way ] 

1. Call function `response_format=AgentResponse`
1. This leverages function calling capabilies of LLMs.
1. Below logic is actually powered by LangGraph. Will be explained later


    ```py
    from dotenv import load_dotenv

    load_dotenv()

    from langchain_openai import ChatOpenAI
    from langchain_tavily import TavilySearch
    from langchain.agents import create_agent
    from schemas import AgentResponse


    model = ChatOpenAI(model="gpt-5-mini")
    tools = [TavilySearch()]


    agent = create_agent(
        model,
        tools=tools,
        response_format=AgentResponse
    )

    def main():
        print("Start the Chain !!!!")
        result = agent.invoke(
            {
                "messages": [
                    {
                        "role" : "user",
                        "content" : "Search for 3 job posting for an AI Engineer using Langchain in Linkedin"
                    }
                ]
            }
        )
        print(result["structured_response"])


    if __name__ == "__main__":
        main()

    ```

1. Output:

    ```ps
    $ /home/syndicate/miniconda3/envs/langchain-env/bin/python /home/syndicate/Documents/CodeSource/python-projects/langchain-app/main.py
    Start the Chain !!!!
    answer='Here are 3 LinkedIn job postings for AI Engineer roles mentioning LangChain (title, company, location, short summary, link):\n\n1) AI Engineer (Generative AI, AWS, LangChain) — Cuedo Analytics (Bengaluru, India)\n- Summary: Build/deploy LangChain-based generative AI solutions on AWS; Python (FastAPI/Flask), LLM APIs, RAG/agents, cloud integration.\n- Link: https://in.linkedin.com/jobs/view/ai-engineer-generative-ai-aws-langchain-at-cuedo-analytics-4339924545\n\n2) AI Engineer – LangChain / RAG / Vector Search / Fine-Tuning — Dimension Studios (North Charleston, SC, USA)\n- Summary: Develop LangChain + vector search features, design RAG pipelines, fine-tune models (LoRA/full), prompt/embeddings optimization, collaborate with product/engineering.\n- Link: https://www.linkedin.com/jobs/view/ai-engineer-%E2%80%93-langchain-rag-vector-search-fine-tuning-at-dimension-studios-4211008249\n\n3) AI Engineer - LangChain & Agentic Systems (Python) — Tvara (location listed on posting)\n- Summary: Build agentic systems with LangChain/LangGraph, implement RAG, embeddings/vector DBs (FAISS/Chroma/Supabase), AWS deployment, evaluation and POCs.\n- Link: https://in.linkedin.com/jobs/view/ai-engineer-langchain-agentic-systems-python-at-tvara-4321717133\n\nNote: LinkedIn may require sign-in to view full job descriptions.' sources=[Source(url='https://in.linkedin.com/jobs/view/ai-engineer-generative-ai-aws-langchain-at-cuedo-analytics-4339924545'), Source(url='https://www.linkedin.com/jobs/view/ai-engineer-%E2%80%93-langchain-rag-vector-search-fine-tuning-at-dimension-studios-4211008249'), Source(url='https://in.linkedin.com/jobs/view/ai-engineer-langchain-agentic-systems-python-at-tvara-4321717133')]
    ```

1. Note : It is the model that decides if the repsonse is satisfactory.