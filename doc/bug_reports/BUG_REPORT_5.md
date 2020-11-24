## Description

Summary of the feature's bug (without describing implementation details)

Some tests have the wrong states associated with them

## Expected Behavior

Describe the behavior you expect

Tests return true, use state 3 for potential Othello moves, and 4 and 5 for Kings

## Current Behavior

Describe the actual behavior

Test return false, use state 5 for Othello moves, 5 and 6 for Kings

## Steps to Reproduce

Provide detailed steps for reproducing the issue.

 1. Run all tests
 2. See tests fail in model, note that the grid is the exact same except for the states being off

## Failure Logs

Include any relevant print statements or stack traces.

org.opentest4j.AssertionFailedError: 
Expected :[[0, 0, 1, 0], [0, 0, 0, 0], [0, 0, 6, 0]]
Actual   :[[0, 0, 1, 0], [0, 0, 0, 0], [0, 0, 5, 0]]

## Hypothesis for Fixing the Bug

Describe the test you think will verify this bug and the code fix you believe will solve this issue.

No additional tests needed since we're only fixing tests, not actual code; Only thing that needs to be
fixed is the values the expected test results are storing