## Description

Summary of the feature's bug (without describing implementation details)

Connect 4 pieces cannot be placed for any player or AI

## Expected Behavior

Describe the behavior you expect
- When clicking on the Connect4 grid in the View, we expect a piece to be updated in the model 
and then placed on the grid. 

## Current Behavior

Describe the actual behavior
- When clicking on the Connect4 grid in the View, neither the model nor the view gets updated

## Steps to Reproduce

Provide detailed steps for reproducing the issue.

 1. step 1: Start game, select Connect 4 with one or two players
 1. step 2: Click anywhere on the board
Reproduction rate: 100%

## Failure Logs

Include any relevant print statements or stack traces.

org.opentest4j.AssertionFailedError: 
Expected :[[0, 0, 0, 0], [0, 0, 0, 0], [0, 0, 1, 2]]
Actual   :[[0, 0, 0, 0], [0, 0, 0, 0], [0, 0, 0, 0]]


No fatal errors - logic error

## Hypothesis for Fixing the Bug

Describe the test you think will verify this bug and the code fix you believe will solve this issue.

This is likely due to a miscommunication with how the states in our pieces are maintained 
when the Grid is updated.