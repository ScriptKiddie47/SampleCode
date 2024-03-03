# Nano

1. Check if its installed

```bash
Syn: ~/Desktop/test 
$ which nano
/usr/bin/nano
```

1. If we just type nano on the terminal we open nano on a buffer which means no file is selected

1. `^O Write out` - Same as save.
1. This will bring up the save dialog. We can give a file name and press enter.
1. File is created and saved in the current working directory if no specified location is set.

1. `^G` - Help
1. Start on line number 2 - `$ nano +2 nano-test.txt `
1. View only mode - `$ nano -v nano-test.txt`
1. Navigate to specific line - `^W -> ^T enter line#`

## Search

1. `^W Where is` - This is same as text search.

## Cut Copy Paste

1. `^K Cut` - Cuts entire line
1. `^U Paste` - Paste the line

1.  Copy : 
    - To select text, press the Ctrl + 6 key combination. 
    - You will see "Mark Set" appear at the bottom of the screen as an acknowledgement.
    - Now, use the arrow keys (keys like Home, End, Page Up and Page Down can also be used) to select/highlight text.
    - To copy selected text, press Alt + 6 key combination.
    - Paste is `^U`

