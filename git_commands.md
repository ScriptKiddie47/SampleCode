# Github Commands

| Command | Description |
| --- | --- |
| `$ git log --oneline --graph --decorate` | Show all logs for current branch |
| `$ git log d2a71d7...3424cc5 --oneline --graph --decorate` | Show all logs between couple of commits |
| `$ git log -- robots.txt` | Show all commits for a single file |
| `$ git log --follow -- level1/level2/level2filev2.txt`| Commit History of specific file going through renames |
| `$ git show 239830cf0fbc73121aa80ad70e3251d4aba9e4a6`| Show information about a specific commit | 
| `$ git diff`| Compares between local working dir & stagging area | 
| `$ git difftool` | Compares between local working dir & stagging area using the diff tool | 
| `$ git diff HEAD` | Working directory & last commit | 
| `$ git diff --staged HEAD` | Compares between stagged vs Head ( Latest commit ) | 
| `$ git diff -- README.md` | Compares between local & stagging area for a single file | 
| `$ git difftool 467727d af34a60` | Compares between 2 commits | 
| `$ git difftool HEAD HEAD^` | Compares Head and Head - 1  | 
| `$ git branch -a` | Git See all branches ( -a is upstream ) |
| `$ git branch xyz ` | Creates a branch called xyz |
| `$ git checkout xyz ` | Switches to branch xyz |
| `$ git checout -b xyz ` | Creates a branch called xyz & switches |
| `$ git merge xyz ` | If executed from master , merges all xyz changes in master |
| `$ git mergetool ` | When there is a conflict  post the merge command, a merge tool gets launched using this command|
| `$ git branch -d realwork ` | Git Delete branch  |
| `$ git merge xyz ` | If executed from master , merges all xyz changes in master |



### Create Git Alias
```
Syn: ~/Documents/CodeSource/Github/starter-web (master)
$ git config --global alias.hist "log --all --oneline --graph --decorate"
```
How does the file look : 

$ cat .gitconfig 
```
───────┬───────────────────────────────────────────────────
       │ File: .gitconfig
───────┼────────────────────────────────────────────────────
   1   │ [user]
   2   │     email = ritambala@live.com
   3   │     name = syndicate
   4   │ [alias]
   5   │     hist = log --all --oneline --graph --decorate
───────┴────────────────────────────────────────────────────
```
How to use alias : $ git hist

###  Integrate Merge/Diff Tool : Meld

```
$ git config --global diff.tool meld
$ git config --global difftool.meld.path /home/syndicate/Documents/Tools/meld-3.22.0/bin/meld
$ git config --global merge.tool meld
$ git config --global mergetool.meld.path /home/syndicate/Documents/Tools/meld-3.22.0/bin/meld
```
```
$ cat .gitconfig 
───────┬───────────────────────────────────────────────────────────────────────────────────────────
       │ File: .gitconfig
───────┼───────────────────────────────────────────────────────────────────────────────────────────
   1   │ [user]
   2   │     email = ritambala@live.com
   3   │     name = syndicate
   4   │ [alias]
   5   │     hist = log --all --oneline --graph --decorate
   6   │ [diff]
   7   │     tool = meld
   8   │ [difftool "meld"]
   9   │     path = /home/syndicate/Documents/Tools/meld-3.22.0/bin/meld
  10   │ [merge]
  11   │     tool = meld
  12   │ [mergetool "meld"]
  13   │     path = /home/syndicate/Documents/Tools/meld-3.22.0/bin/meld
───────┴─────

```
