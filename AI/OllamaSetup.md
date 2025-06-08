# Ollama

1. Source : 
1. Install Ollama

    ```ps
    curl -fsSL https://ollama.com/install.sh | sh
    ```
1. Commands

    ```ps
    $ ollama ps 	# Shows the currently running models.
    $ ollama list 	# Lists all the downloaded models.
    $ ollama run llama3.1:8b # Runs the specified model, making it ready for interaction.
    ```
1. 


```ps
curl http://localhost:11434/api/generate -d '{
  "model": "llama3.1:8b",
  "prompt":"Why is the sky blue?"
}'
```

```ps
curl http://localhost:11434/api/chat -d '{
  "model": "llama3.1:8b",
  "messages": [
    { "role": "user", "content": "why is the sky blue?" }
  ]
}'
```