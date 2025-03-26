# Download and Setup MiniConda

1. Download url : https://www.anaconda.com/docs/getting-started/miniconda/main
2. Follow Guide : https://docs.conda.io/projects/conda/en/latest/user-guide/install/linux.html
3. Say yes to everything. Run the below command. Observe (base) at the start

```ps
(base) syndicate@syn-debian:~$ conda list
# packages in environment at /home/syndicate/miniconda3:
#
# Name                    Version                   Build  Channel
_libgcc_mutex             0.1                        main  
_openmp_mutex             5.1                       1_gnu  
anaconda-anon-usage       0.5.0           py312hfc0e8ea_100
..
..
```

4. (base) indicates auto activation of conda during startup. Lets disable that.
5. `conda config --set auto_activate_base false` in .bashrc

### Create a Conda Envrionment , Install Python

```sh
syndicate@syn-debian:~/Documents/CodeSource/python-projects/python-conda-demo
$ conda create --name demo-env
syndicate@syn-debian:~/Documents/CodeSource/python-projects/python-conda-demo
$ conda activate demo-env
(demo-env) syndicate@syn-debian:~/Documents/CodeSource/python-projects/python-conda-demo
$
(demo-env) syndicate@syn-debian:~/Documents/CodeSource/python-projects/python-conda-demo
$ conda search python
Loading channels: done
# Name                       Version           Build  Channel             
python                        2.7.13     hac47a24_15  pkgs/main           
python                        2.7.13     heccc3f1_16  pkgs/main           
python                        2.7.13     hfff3488_13  pkgs/main 
..
.


(demo-env) syndicate@syn-debian:~/Documents/CodeSource/python-projects/python-conda-demo
$ conda install python=3.12.9                # If no version number mentioned , grabs latest package


(demo-env) syndicate@syn-debian:~/Documents/CodeSource/python-projects/python-conda-demo
$ conda list               # List all the packages currently available in this environment
# packages in environment at /home/syndicate/miniconda3/envs/demo-env:
#
# Name                    Version                   Build  Channel
_libgcc_mutex             0.1                        main  
_openmp_mutex             5.1                       1_gnu  
bzip2                     1.0.8                h5eee18b_6  
ca-certificates           2025.2.25            h06a4308_0  
expat                     2.6.4                h6a678d5_0  
ld_impl_linux-64          2.40                 h12ee557_0  
libffi                    3.4.4                h6a678d5_1  
libgcc-ng                 11.2.0               h1234567_1  
libgomp                   11.2.0               h1234567_1  
libstdcxx-ng              11.2.0               h1234567_1  
libuuid                   1.41.5               h5eee18b_0  
ncurses                   6.4                  h6a678d5_0  
openssl                   3.0.16               h5eee18b_0  
pip                       25.0            py312h06a4308_0  
python                    3.12.9               h5148396_0  
readline                  8.2                  h5eee18b_0 


(demo-env) syndicate@syn-debian:~/Documents/CodeSource/python-projects/python-conda-demo
$ python -V
Python 3.12.9
(demo-env) syndicate@syn-debian:~/Documents/CodeSource/python-projects/python-conda-demo
$ pip -V
pip 25.0 from /home/syndicate/miniconda3/envs/demo-env/lib/python3.12/site-packages/pip (python 3.12)


(demo-env) syndicate@syn-debian:~
$ which python
/home/syndicate/miniconda3/envs/demo-env/bin/python

(demo-env) syndicate@syn-debian:~/Documents/CodeSource/python-projects/python-conda-demo
$ conda deactivate
syndicate@syn-debian:~/Documents/CodeSource/python-projects/python-conda-demo
$ 


```

### Conda Packages 

1. https://conda-forge.org/ 


### Conda List of Environments

```ps
syndicate@syn-debian:~
$ conda info --envs

# conda environments:
#
base                   /home/syndicate/miniconda3
demo-env               /home/syndicate/miniconda3/envs/demo-env
```
