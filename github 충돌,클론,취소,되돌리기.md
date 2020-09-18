# 오전실습

* 로컬
  * 폴더(remote)를 만들고
  * README.md를 넣고
  * 커밋
* 원격저장소(remote)
  * push

---



# 수업내용(2020.09.18)

* 하나의 파일이 가질 수 있는 상태
  * `생성` , `변경` , `삭제` 



> git bash창에서 글이 빨간색 : working directory에 있는 상태 中

* Untracked files  : `생성`

* modified : `변경`

* deleted : `삭제`



## Git 홈페이지에서 commit 후 bash에서 commit

* github 홈페이지에서 커밋한 후 bash에서 push 할 때 `충돌상황`!
  * 원격 저장소의 이력과 로컬 저장소의 이력이 다르다.

```bash
$ git push origin master
To https://github.com/ps4417/remote.git
 ! [rejected]        master -> master (fetch first)
 # 에러!
error: failed to push some refs to 'https://github.com/ps4417/remote.git'
# rejected(거절) - 원격저장소의 작업이 로컬에 없다.
hint: Updates were rejected because the remote contains work that you do
hint: not have locally. This is usually caused by another repository pushing
# 너는 원할 것 같다.. 원격저장소의 변경사항을 먼저 통합(integrate) 다시 push 하기 전에
hint: to the same ref. You may want to first integrate the remote changes
hint: (e.g., 'git pull ...') before pushing again.
hint: See the 'Note about fast-forwards' in 'git push --help' for details.

```

* 해결방법

```bash
$ git pull origin master
```

* 이렇게 하면 , vim 창으로 커밋 메시지가 뜨고
* 자동으로 작성된 커밋 메시지를 확인하고, `:wq` 로 저장하고 나간다.
* 그 이후 log 를 확인하고, push를 한다.

```bash
$ git log --oneline
146f4ab (HEAD -> master) Merge branch 'master' of https://github.com/ps4417/remote into master
215565b Make local file
4081d75 Update
38a97ca (origin/master) Create remote.txt
9f28344 Make remote
```

---

수업들을 때 여기서

[CS50](https://ide.cs50.io/)

---



## Clone

1. github 홈페이지에서 clone하고 싶은 repository에 들어가 초록색으로 되어있는 `Code` 를 클릭하여 https 를 복사한다.
2. clone하길 원하는 컴퓨터에서 clone을 진행한다.

```bash
$ git clone https://github.com/ps4417/remote.git
Cloning into 'remote'...
remote: Enumerating objects: 13, done.
remote: Counting objects: 100% (13/13), done.
remote: Compressing objects: 100% (7/7), done.
remote: Total 13 (delta 1), reused 10 (delta 1), pack-reused 0
Unpacking objects: 100% (13/13), done.
```

3. config 한다.

4.  ```bash
   $ git pull origin master
    ```





push -pull

clone - init

복제후 cd 를 통해 이동해서 거기서 작업을 해야한다.





## Git branch

> 독립적인 작업 공간과 흐름을 만들기 위해 필요하다.

[A successful Git branching model](https://nvie.com/posts/a-successful-git-branching-model/)

### branch 기초 명령어

http://hpy.hk/git-branch

* 브랜치 생성

  ```bash
  $ git branch {브랜치이름}
  ```

* 브랜치 목록

  ```bash
  $ git branch
  * master
    test
  ```

* 브랜치 이동

  ```bash
  $ git checkout test
  Switched to branch 'test'
  (test) $
  ```

* 브랜치 생성 및 이동

  ```bash
  $ git checkout -b test2
  Switched to a new branch 'test2'
  (test2) $
  ```

* 브랜치 병합(master로 이동해서)

  ```bash
  $ git merge test2
  Updating 4ed89ea..4d2380a
  Fast-forward
   test2.txt | 0
   1 file changed, 0 insertions(+), 0 deletions(-)
   create mode 100644 test2.txt
  ```

* 브랜치 삭제

  ```bash
  $ git branch -d test2
  Deleted branch test2 (was 4d2380a).
  ```



git flow

https://hpy.hk/github-flow



## Git 명령어 취소 및 되돌리기

1. `add` 취소

   ```bash
   $ git restore --staged <파일명> #최신
   $ git reset HEAD <파일명> #구버전
   ```

   ```bash
   $ git status
   On branch master
   Changes to be committed:
    # unstage.. add를 취소하려면
    # git restore --staged
     (use "git restore --staged <file>..." to unstage)
           new file:   a.txt
           new file:   b.txt
   ```

   ```bash
   $ git restore --staged b.txt
   $ git status
   On branch master
   Changes to be committed:
     (use "git restore --staged <file>..." to unstage)
           new file:   a.txt
   
   Untracked files:
     (use "git add <file>..." to include in what will be committed)
           b.txt
   ```

   

2. `commit` 메시지 변경

   > 주의! 커밋 메시지를 변경하면 , hash값이 변경된다.
   >
   > 즉, 공개된 저장소에 `push` 를 한 이후에는 하지 않는다.

   ```bash
   $ git commit --amend
   ```

   * vim 편집기 창에서 직접 메시지를 수정하고 저장

     ```bash
     $ git log --oneline
     03ca0cb (HEAD -> master) c.txt 추가
     538d35d Add b.txt
     3867506 Add a.txt
     76b305f README
     
     $ git commit --amend
     [master 30ca836] Add c.txt
      Date: Fri Sep 18 16:12:38 2020 +0900
      1 file changed, 0 insertions(+), 0 deletions(-)
      create mode 100644 c.txt
     
     $ git log --oneline
     30ca836 (HEAD -> master) Add c.txt
     538d35d Add b.txt
     3867506 Add a.txt
     76b305f README
     ```

     

3. 커밋 변경

   > 주의! 커밋 메시지를 변경하면, hash 값이 변경된다!
   >
   > 즉, 공개된 저장소에 `push` 를 한 이후에는 하지 않는다.
   >
   > 2번과 동일한 명령어

   * 예) 내가 어떤 파일을 빠트리고 커밋했을 때

     ```bash
     $ touch d.txt
     $ touch omit.txt
     $ git add d.txt
     $ git commit -m 'Add d&omit'
     $ git status
     ```

   * 해결방법

     ```bash
     $ git add omit.txt
     $ git status
     On branch master
     Changes to be committed:
       (use "git restore --staged <file>..." to unstage)
             new file:   omit.txt
     $ git commit --amend
     [master 7844532] Add d & omit
      Date: Fri Sep 18 16:20:18 2020 +0900
      2 files changed, 0 insertions(+), 0 deletions(-)
      create mode 100644 d.txt
      # 들어가있죠!
      create mode 100644 omit.txt
     $ git status
     On branch master
     nothing to commit, working tree clean
     ```

     

4. `working derectory` 변경사항 삭제

   > 주의! 아래의 명령어를 입력하면 절대 과거로 돌아갈 수는 없다.
   >
   > 커밋한 내용만 복구 가능!

   ```bash
   $ git restore <파일명>
   ```

   ```bash
   $ git status
   On branch master
   Changes not staged for commit:
     (use "git add/rm <file>..." to update what will be committed)
     # WD 있는 변경사항들을(changes) 버리기 위해서는...
     # git restore <파일>
     (use "git restore <file>..." to discard changes in working directory)
           deleted:    README.md
           deleted:    a.txt
           deleted:    b.txt
           deleted:    c.txt
           deleted:    d.txt
           deleted:    omit.txt
   
   no changes added to commit (use "git add" and/or "git commit -a")
   ```

   ```bash
   $ git restore .
   $ git status
   On branch master
   nothing to commit, working tree clean
   ```

   

## 이력 되돌리기(reset vs revert)

* 두 명령어는 특정 시점의 상태로 커밋을 되돌리는 작업을 한다.

* `reset` : 이력을 삭제

  * `--mixed` : (default) 해당 커밋 이후 변경사항을 보관
  * `--hard` : 해당 커밋 이후 변경사항을 모두 삭제(주의!)
  * `--soft` : 해당 커밋 이후 변경사항 및 WD 내용까지 보관

  ```bash
  $ git log --oneline
  da0ae77 (HEAD -> master) Update README
  7844532 Add d & omit
  30ca836 Add c.txt
  $ git reset 7844532
  $ git log --oneline
  7844532 (HEAD -> master) Add d & omit
  30ca836 Add c.txt
  ```

  

* `revert` : 되돌렸다는 이력을 남긴다.

  ```bash
  $ git revert 7844532
  Removing omit.txt
  Removing d.txt
  [master f050b8e] Revert "Add d & omit"
   2 files changed, 0 insertions(+), 0 deletions(-)
   delete mode 100644 d.txt
   delete mode 100644 omit.txt
  $ git log --oneline
  f050b8e (HEAD -> master) Revert "Add d & omit"
  7844532 Add d & omit
  30ca836 Add c.txt
  ```

  