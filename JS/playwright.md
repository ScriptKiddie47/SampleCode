## Playwright Commands

| Command                                                       | Usage                                |
|---------------------------------------------------------------|--------------------------------------|
| `npm playwright -v`                                           | Playwright Version                   |
| `npx playwright -h`                                           | Playwright Help                      |
| `npx playwright test`                                         | Runs all the test                    |
| `npx playwright test --workers 3`                             | Runs all test using 3 workers        |
| `npx playwright test example.spec.js`                         | Run particular test file             |
| `npx playwright test -g "get started link"`                   | Run a particular test                |
| `npx playwright test --project="chromium"`                     | Run on a specific browser            |
| `npx playwright test --project=chromium --headed --workers 1` | Run on headed mode [ See Browser ]   |
| `npx playwright test --project=chromium --debug --workers 1 ` | Debug Mode [ By defaulty its Headed] |

## Playwright BrowserType

1. Download Binary : https://chromium.woolyss.com/ [ Could be Better sources ] & UnZip
2. Install playwright [ Do not get browsers ]
3. Create File

```js
const { chromium } = require('playwright');
import { test, expect } from '@playwright/test';

test('First Test',async () => {
  const browser = await chromium.launch({
    headless: false,
    executablePath: '/home/syndicate/Documents/Tools/browser-binaries/ungoogled-chromium_134.0.6998.116_1.vaapi_linux/chrome'
  });
  const page = await browser.newPage();
  await page.goto('https://playwright.dev/');
  await expect(page).toHaveTitle(/Playwright/);
  await browser.close();
});
```

4. Command to Run it:

```ps
$ npx playwright test browser-type.spec.js --project="chromium"
```