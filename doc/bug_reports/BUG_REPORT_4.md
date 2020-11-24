## Description

Summary of the feature's bug (without describing implementation details)

Once a king is kinged and it goes past the second rank, it can no longer move backwards

## Expected Behavior

Describe the behavior you expect

Kings on all squares are capable of moving and jumping to any diagonal square

## Current Behavior

Describe the actual behavior

Only kings on the last and second-to-last rows can move in all directions.

## Steps to Reproduce

Provide detailed steps for reproducing the issue.

 1. step 1: Start a checkers game
 2. step 2: Make a king (move a piece to the last row of the opponent's board)
 3. Step 3: Move king off of last row onto second to last row
 4. Step 4: Move king off of second to last row onto third to last row
 5: Step 5: Click on king to get list of potential moves, note the missing ones

## Failure Logs

Include any relevant print statements or stack traces.
N/A

## Hypothesis for Fixing the Bug

Describe the test you think will verify this bug and the code fix you believe will solve this issue.

Looking at the Grid, it seems like Kings don't stay Kings when they move, so I think there is an
issue maintaining variables of class-specific pieces when a piece is moved/cloned. To test this
bug, we're writing a test that gets the available move of a king once it's moved a few times.