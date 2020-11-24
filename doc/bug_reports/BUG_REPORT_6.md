## Description

Summary of the feature's bug (without describing implementation details)

Othello AI does not play - instead, it allows for a second player (implementation for the AI 
was working prior)

## Expected Behavior

Describe the behavior you expect

Othello AI plays a move after the player does

## Current Behavior

Describe the actual behavior

Othello AI never moves, but the player can move for them

## Steps to Reproduce

Provide detailed steps for reproducing the issue.

 1. step 1: Start 1 player game of Othello
 2. step 2: Make the first move
 3. step 3: Wait for AI to move, note that nothing happens
 4. step 4: Click on one of the potential squares, note the piece moves

## Failure Logs

Include any relevant print statements or stack traces.

N/A: Logical error

## Hypothesis for Fixing the Bug

Describe the test you think will verify this bug and the code fix you believe will solve this issue.

Not positive but it's worth noting that the step loops forever and continues calling the AI - 
it's an issue with the AI's returned moves. Test will see if the AI's number of pieces 
on the board increases.