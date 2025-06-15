# PIP

1. List all PIP packages

    ```ps
    [~/Documents/CodeSource/python-projects/pip-learner]
    learn-pip  syndicate  pip list                     
    Package    Version
    ---------- -------
    pip        25.1
    setuptools 78.1.1
    wheel      0.45.1
    ```

1. PIP install a package

    ```ps
    [~/Documents/CodeSource/python-projects/pip-learner]
    learn-pip  syndicate  pip install Pympler          
    Collecting Pympler
    Downloading Pympler-1.1-py3-none-any.whl.metadata (3.6 kB)
    Downloading Pympler-1.1-py3-none-any.whl (165 kB)
    Installing collected packages: Pympler
    Successfully installed Pympler-1.1
    ```

1. PIP Search

    ```ps
    [~/Documents/CodeSource/python-projects/pip-learner]
    learn-pip  syndicate  pip uninstall Pympler
    Found existing installation: Pympler 1.1
    Uninstalling Pympler-1.1:
    Would remove:
        /home/syndicate/miniconda3/envs/learn-pip/lib/python3.12/site-packages/Pympler-1.1.dist-info/*
        /home/syndicate/miniconda3/envs/learn-pip/lib/python3.12/site-packages/pympler/*
    Proceed (Y/n)? y
    Successfully uninstalled Pympler-1.1
    ```

1. PIP list with outdated version

    ```ps
    [~/Documents/CodeSource/python-projects/pip-learner]
    learn-pip  syndicate  pip list -o          
    Package    Version Latest Type
    ---------- ------- ------ -----
    pip        25.1    25.1.1 wheel
    setuptools 78.1.1  80.9.0 wheel
    ```

1. Update an existing package

    ```ps
    [~/Documents/CodeSource/python-projects/pip-learner]
    learn-pip  syndicate  pip install -U setuptools    
    Requirement already satisfied: setuptools in /home/syndicate/miniconda3/envs/learn-pip/lib/python3.12/site-packages (78.1.1)
    Collecting setuptools
    Downloading setuptools-80.9.0-py3-none-any.whl.metadata (6.6 kB)
    Downloading setuptools-80.9.0-py3-none-any.whl (1.2 MB)
    ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━ 1.2/1.2 MB 10.3 MB/s eta 0:00:00
    Installing collected packages: setuptools
    Attempting uninstall: setuptools
        Found existing installation: setuptools 78.1.1
        Uninstalling setuptools-78.1.1:
        Successfully uninstalled setuptools-78.1.1
    Successfully installed setuptools-80.9.0
    ```

1. PIP Freeze - Output package & version number in a requirements format. We can see on our console to push it to a requirements file.

    ```ps
    [~/Documents/CodeSource/python-projects/pip-learner]
    learn-pip  syndicate  pip freeze 
    setuptools==80.9.0
    wheel==0.45.1
                
    [~/Documents/CodeSource/python-projects/pip-learner]
    learn-pip  syndicate  pip freeze > requirements.txt
    ```

1. Install from a Requirements file with correct version

    ```ps
    [~/Documents/CodeSource/python-projects/pip-learner]
    learn-pip  syndicate  pip install -r requirements.txt 
    Requirement already satisfied: setuptools==80.9.0 in /home/syndicate/miniconda3/envs/learn-pip/lib/python3.12/site-packages (from -r requirements.txt (line 1)) (80.9.0)
    Requirement already satisfied: wheel==0.45.1 in /home/syndicate/miniconda3/envs/learn-pip/lib/python3.12/site-packages (from -r requirements.txt (line 2)) (0.45.1)
    ```