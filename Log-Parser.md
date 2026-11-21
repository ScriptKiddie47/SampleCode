# Log Scroller — Spec

A desktop viewer for one or more folders of log files. It shows each file's raw lines and
keeps up with appended content (live tail). Built in Rust with GPUI Kit.

**Product name:** Log Scroller (crate/exe: `log-parser`).

## Stack

| Item | Choice |
| --- | --- |
| Language | Rust (edition 2024, 1.92+) |
| UI | `gpui-kit = "0.10"` (GPUI + GPUI Base + GPUI Component) |
| Config | `serde` + `serde_yaml_ng` (YAML); `serde_json` kept for legacy `config.json` |
| Fonts/colors | GPUI Kit theme; monospace family (`Consolas` on Windows) |
| Windows icon | `winres` build dependency embeds `assets/icon.ico` as resource ID 1 |
| Platform | Windows first; macOS/Linux best-effort |

## Project layout

```
src/main.rs   # application entry, window title, wraps the view in Root
src/app.rs    # UI, state, selection, theme, action logging
src/logs.rs   # file discovery and incremental tailing (no UI)
build.rs      # embeds assets/icon.ico (Windows resource ID 1)
assets/       # icon.png (1024), icon-transparent.png, icon.ico (16–256 px)
scripts/      # make-icon.ps1 — regenerates the icon (optionally from a PNG)
Cargo.toml
SPEC.md
README.md
config.yaml   # not in the repo: created at runtime next to the exe
```

`main.rs` opens a window titled **Log Scroller** and wraps the `LogViewer` in
`gpui_component::Root` (component chrome such as tooltips/overlays). Selection and
Ctrl+C handling live in `app.rs`.

## Behavior

1. **Folders**
   - Path input, default `C:\Users\S Bala\Documents\Logs`.
   - `Add` (or Enter in the input) appends that folder to the watch list. The path must
     be a directory; invalid paths show `Not a folder: <path>` and change nothing.
   - Adding a folder that is already watched (same path, symlinks tolerated) just
     switches to it.
   - Each watched folder appears as a chip above the file list. Click a chip to make it
     active; the `×` on a chip removes it from the watch list.
   - All folders are polled simultaneously; the file list shows the active folder only.
   - Adding a folder makes it active, selects its first file, and scrolls to the tail.
   - **Config file** — `config.yaml` sits next to the exe, shaped:
     ```yaml
     folders:
       - C:\Users\S Bala\Documents\Logs
       - D:\logs\app
     ```
     It is created automatically on first run with the default folder (unless a legacy
     `config.json` exists), and is read at startup (falling back to the default folder
     when empty/invalid). The header shows the config path plus two buttons: `Open`
     (open it in the default editor) and `Reload` (re-read it and replace the watched
     folders). The old `config.json` is still read when `config.yaml` is absent.

2. **File list (left pane)**
   - Every regular `*.log` file directly inside the **active** folder (case-insensitive
     extension), sorted by name.
   - Header shows `FILES` and that folder's file count.
   - Each row shows the file name and its line count; the selected row has a rounded
     highlight plus a primary-colored bar on the left.
   - Files that disappear are kept in the list, marked `unavailable` in the danger color.
   - New files discovered while running are added automatically.
   - Clicking a file selects it and **always scrolls to its newest line**, regardless of
     Follow state.

3. **Log pane**
   - Shows one raw file line per row, exactly as written (no parsing, no columns).
   - Monospace, left-aligned, no row striping.
   - Lines are virtualized (`uniform_list`); only visible rows are built.
   - A slim vertical scrollbar sits on the right; its thumb is hidden when the content
     fits the pane.

4. **Live tail**
   - Poll every 500 ms; for each file read only bytes after the last offset.
   - `\n` and `\r\n` supported. A trailing partial line is held until its newline
     arrives, or flushed after ~1 s of no file growth (so historical last lines show).
   - If the file shrinks, treat it as truncated: clear its lines and restart at 0.
   - If the file is missing, mark it gone but keep the lines already read.

5. **Wrap toggle (default ON)**
   - ON: each line is split into fixed-width chunks based on the pane width and font
     size, so long lines continue on following rows. No characters are lost.
   - OFF: one row per line; overflow is clipped at the pane edge (no ellipsis).
   - Toggling clears the current text selection.

6. **Filter**
   - Case-insensitive substring match against the whole raw line.
   - Applied to the selected file only; affects both the list and `Copy`.
   - Changing the filter clears the text selection.

7. **Follow (default ON)**
   - When on, new lines (and the initial load) scroll to the newest line.
   - Scrolling up turns Follow off automatically (status becomes `PAUSED`).
   - Pressing `Follow` turns it back on and jumps to the tail.

8. **Font size**
   - Toolbar `FONT -` / `+`, range 9–24 px, default 13 px.
   - Row height = font size + 10 px.
   - Changing it clears the selection and re-wraps.

9. **Copy**
   - Toolbar `Copy` copies **all lines currently shown** (i.e. after filtering) to the
     clipboard, raw and newline-separated.
   - Status: `Copied N line(s) to the clipboard`.

10. **Text selection**
    - Drag across log text to select characters; Shift+click extends.
    - Highlight uses the theme primary color.
    - `Ctrl+C` (Windows/Linux) or `Cmd+C` copies only the selection; wrapped chunks of
      the same line are rejoined, separate lines get newlines.
    - Status bar advertises this: `Select text · Ctrl+C to copy`.
    - Selection is limited to rows currently rendered. No right-click menu.
    - Clicking in the pane starts a new collapsed selection at the nearest caret.
    - Selection clears on file/folder switch, filter change, wrap toggle, or font change.

11. **Theme**
    - Toolbar button shows the mode to switch to (`Dark` while light, `Light` while dark).
    - Switching calls `Theme::change(...)` plus `Theme::sync_scrollbar_appearance(cx)`;
      all colors come from theme tokens, so no per-widget work is needed.
    - Choice is persisted:
      - Windows: `%APPDATA%\Log Scroller\theme.txt`
      - macOS/Linux: `$XDG_CONFIG_HOME/log-scroller/theme.txt`, else
        `~/.config/log-scroller/theme.txt`
      - Contents: `dark` or `light`.
    - First launch (no file) follows the window/system appearance.
    - The native Windows title bar keeps the system theme (known limitation).

12. **Status bar**
    - `LIVE` (green dot) or `PAUSED` (muted dot).
    - Folder count, file count and total line count, with thousands separators.
    - `Select text · Ctrl+C to copy`.
    - Right side: `N of M lines` for the selected file (filtered vs total).

13. **Action log**
    - Written to stderr and appended to `%TEMP%\log-parser.log`.
    - Events: started (folders/files/font), folder added/removed/switched, file
      discovered, Add clicked/failed, config opened/reloaded, file clicked, filter
      changed, wrap toggled, font size, follow toggled / auto-off, streamed N lines,
      copied all, copied selection.

## UI layout

```
┌ ● Log Scroller · live log viewer   [ Path.................. ] [Add] [Open] [Reload] config.yaml
├───────────────┬─────────────────────────────────────────────────────
│ [Logs ×][Logs2 ×] │ [ Filter... ]  FONT - 13 +  [Wrap]  [Copy] [Follow] [Dark]
│ FILES      2  │ 20171223-22:15:29:606|Step_LSC|30002312|onStandStepChanged 3579
│ service-a.log │ 20171223-22:15:29:615|Step_LSC|30002312|onExtend:1514038530000 14 0 4
│ 1,999 lines   │ ...
│ service-b.log │                                              ▐ scrollbar
│ 2,004 lines   │
├───────────────┴─────────────────────────────────────────────────────
│ ● LIVE | 2 folders · 4 files · 8,006 lines | Select text · Ctrl+C to copy
│                                                        1,999 of 1,999 lines
└─────────────────────────────────────────────────────────────────────
```

## State

```rust
struct LogFolder {
    path: PathBuf,
    label: String,        // last path component, shown on the chip
    files: Vec<LogFile>,
}

struct LogFile {
    path: PathBuf,
    name: String,
    lines: Vec<String>,   // raw lines, no terminators
    offset: u64,          // bytes consumed
    pending: String,      // partial trailing line
    stalled_polls: u8,    // quiet polls since last growth
    gone: bool,
    error: Option<String>,
}

struct Row { line: usize, start: usize, end: usize } // visible row -> byte range of a line
struct Cursor { row: usize, col: usize }             // visual row + character column
struct TextSelection { anchor: Cursor, cursor: Cursor }
```

`LogViewer` holds: path/filter input entities, the watched folders
(`Vec<LogFolder { path, label, files }>`), the active folder index, the selected file's
path, filter text, font size, wrap flag, dark flag, visible logical line indices, visible
rows, selection, focus handle, scroll handle, settle counter, per-tick new line count,
and status.

Key helpers:

- `rebuild_visible(columns)` — filters lines and produces `Row`s (one per line, or one
  per wrapped chunk).
- `columns_for(window)` — `(viewport - 260 px pane - 16 px scrollbar - 24 px padding) /
  (font_size × 0.55)`, clamped to 8–400.
- `cursor_at(position)` — maps a window point to `Cursor` using the list's scroll bounds,
  offset, row height and approximate character width.
- `selection_text(rows, lines, selection)` — pure; joins the selected text, joining
  wrapped chunks of the same logical line and separating different lines with `\n`.

## Look and feel

- Colors exclusively from the theme (works in light and dark).
- Brand block: small rounded primary square, **Log Scroller**, muted `live log viewer`.
- Monospace log text; UI labels/buttons use GPUI Kit defaults.
- No zebra striping on log rows.
- Rounded file rows with hover; selected row uses `list_active` + primary accent bar.
- Slim scrollbar on the right of the log pane (thumb hidden when nothing scrolls).

## Build, run, test

```sh
cargo build          # debug
cargo build --release
cargo run
cargo test           # 16 tests
```

First build compiles the GPUI dependency tree (several minutes).

Icon:

- Regenerate: `powershell -ExecutionPolicy Bypass -File scripts\make-icon.ps1`
- Or supply a new 1024×1024 PNG at `assets/icon.png` and run the same script with
  `-FromPng`.
- `build.rs` embeds the `.ico` on Windows; GPUI loads resource ID 1 for the window,
  taskbar and Alt-Tab.

## Tests

- `logs`: raw line preservation, append-only tail, CRLF, missing final newline, truncation
  reset, missing file, discovery filter, stalled partial-line flush, real sample folder.
- `app`: selection text (single row, cross-row, wrapped chunks, reversed, empty),
  theme preference parsing, YAML config parsing (unescaped Windows paths, empty file),
  and the legacy JSON config fallback.

## Acceptance criteria

- Both sample files open and show their full line count (≈2,000 each); opening lands on
  the newest line.
- Adding a second folder creates a chip; switching chips shows that folder's files and
  polling continues for all folders. Removing a chip drops it from the watcher.
- Editing `config.yaml` and pressing `Reload` replaces the watched folders; `Open`
  launches the file in the default editor.
- Appending a line shows up within ~1 second; Follow keeps the view pinned.
- Scrolling up pauses Follow; Follow resumes at the tail.
- Long lines wrap by default; `Wrap` off shows one clipped row per line.
- Filter narrows the list; `Copy` copies the filtered lines; Ctrl+C copies a selection.
- Font size 9–24 px affects text and row height.
- Scrollbar is visible and reflects position.
- `Dark`/`Light` flips the whole UI and the choice survives a restart.

## Out of scope (v1)

- Remote collection/agents (planned later; a mapped drive like `K:\Logs` already works).
- Log parsing into columns, level filters, multi-line record joining.
- Retention limits (all lines are kept in memory per file).
- Draggable scrollbar thumb.
- Auto-scroll while dragging a selection past the viewport.
- Persisting in-app folder changes, font, wrap or filter (the folder list is read from
  `config.yaml`; only the theme is saved automatically).
- Theming the native Windows title bar.
