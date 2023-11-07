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
| `$ git rebase master ` | Git rebase <source_branch>. This command is executed form Feature branch. Also allows us to do fast forward merge |
| `$ git rebase --abort` | Abort Rebase |
| `$ git rebase --continue` | Once Rebase conflict resolve is completed , we can execute  |
| `$ git status` | Create Git Stash  |
| `$ git stash apply` | Apply Git Stash |
| `$ git stash list` | View Stash List |
| `$ git stash drop` | Drop last stash | 
| `$ git stash -u` | Stash Untracked files as well | 
| `$ git stash pop` | Apply Stash and delete it | 
| `$ git stash -u` | Stash Untracked files as well | 
| `$ git stash save "simple change"` | Create a stash with a commit message |
| `$ git stash show stash@{1}` | Show changes for a particular stash |
| `$ git stash apply stash@{1}`| Apply a specific stash | 
| `$ git stash drop stash@{1}`| Drop a specific stash | 
| `$ git stash clear`| Remove all stashes | 
| `$ git stash branch newchanges`| Create a new branch wth stash changes and delete stash | 
| `$ git stash clear`| Remove all stashes | 



 


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

Note : 
1. Git stash only captures the tracked file by default.
2. 4cc88c4 (HEAD -> master, origin/master, origin/HEAD) changes after git stash - HEAD is just a pointer, master referes to local repo , origin master refers to remote repo master branch.

## Tags

1. Tags are just labes which we can apply on any commit in the history.
`$ git tag myTag` - Create a lightweight tag , more like an marker on a particular commit
2. 4cc88c4 (HEAD -> master, tag: myTag, origin/master, origin/HEAD) changes after git stash
3. Tag list - `$ git tag --list`
4. We can use the name of the tag in other git commands for reference - `$ git show myTag`
5. Delete tag : `$ git tag --delete myTag` -->  Deleted tag 'myTag' (was 4cc88c4)

 ### Annotated Tag : 
 1. Contains extra information : `$ git tag -a v-1.0`
 2. $ `git hist` -> 4cc88c4 (HEAD -> master, tag: v-1.0, origin/master, origin/HEAD) changes after git stash

Also the show command displays more data : 

```
Syn: ~/Documents/CodeSource/Github/starter-web (master)
$ git show v-1.0 
tag v-1.0
Tagger: syndicate <ritambala@live.com>
Date:   Tue Nov 7 15:31:23 2023 +0530

Release 1.0

commit 4cc88c479ae66b5dcd4f56595a8ee6910fd17a15 (HEAD -> master, tag: v-1.0, origin/master, origin/HEAD)
Author: syndicate <ritambala@live.com>
Date:   Tue Nov 7 14:54:02 2023 +0530
```
3. Tag a specific commit - `$ git tag -a v-0.9-beta 3a14bb4`
4. 
