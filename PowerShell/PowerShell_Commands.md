## Commands

| Command | Description |
| --- | --- |
| `> get-alias` | List of all Aliases |
| `> Get-ChildItem \| select-object Name` | Select the name object |
| `> Get-ChildItem \| select-object -First 1` | Return the first instance |
| `> (Get-ChildItem \| select-object -Index 0).name.toUpper()` | Returns the first instance name object/string in Upper Case |
| `> dir > directory.txt` | Response Stored in a Text File |
| `> get-content .\directory.txt` | See Content inside a file |
| `> Get-ChildItem \| Out-File .\directory.txt` | Store response , the powershell way |
| `> ls \| Out-GridView` | Opens a cool grid view |
| `> mv .\directory.txt .\Desktop\` | Move file from 1 directory to another |
| `git diff` | Show file differences that **haven't been** staged |
