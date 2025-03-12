## Playwright Commands

| Command                                                       | Usage                                |
|---------------------------------------------------------------|--------------------------------------|
| `npm playwright -v`                                           | Playwright Version                   |
| `npx playwright -h`                                           | Playwright Help                      |
| `npx playwright test`                                         | Runs all the test                    |
| `npx playwright test --workers 3`                             | Runs all test using 3 workers        |
| `npx playwright test example.spec.js`                         | Run particular test file             |
| `npx playwright test -g "get started link"`                   | Run a particular test                |
| `npx playwright test --project=chromium"`                     | Run on a specific browser            |
| `npx playwright test --project=chromium --headed --workers 1` | Run on headed mode [ See Browser ]   |
| `npx playwright test --project=chromium --debug --workers 1 ` | Debug Mode [ By defaulty its Headed] |
