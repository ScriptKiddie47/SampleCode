https://docs.github.com/en/authentication/connecting-to-github-with-ssh/generating-a-new-ssh-key-and-adding-it-to-the-ssh-agent
## Generating a new SSH key
1. You can generate a new SSH key on your local machine. After you generate the key, you can add the public key to your account on GitHub.com to enable authentication for Git operations over SSH.
2. Paste the text below, substituting in your GitHub email address.
3. ssh-keygen -t ed25519 -C "ritambala@live.com"
4. At the prompt, type a secure passphrase
5. Start the ssh-agent in the background.
6. eval "$(ssh-agent -s)"
```
syndicate@syndicate-H610M-H-DDR4:~$ eval "$(ssh-agent -s)"
Agent pid 12197
```
7. Add your SSH private key to the ssh-agent. If you created your key with a different name, or if you are adding an existing key that has a different name, replace id_ed25519 in the command with the name of your private key file.
8. ssh-add ~/.ssh/id_ed25519

```
syndicate@syndicate-H610M-H-DDR4:~$ ssh-add ~/.ssh/id_ed25519
Enter passphrase for /home/syndicate/.ssh/id_ed25519: 
Identity added: /home/syndicate/.ssh/id_ed25519 (ritambala@live.com)
```

## Adding a new SSH key to your GitHub account
1. https://docs.github.com/en/authentication/connecting-to-github-with-ssh/adding-a-new-ssh-key-to-your-github-account?platform=linux&tool=webui
