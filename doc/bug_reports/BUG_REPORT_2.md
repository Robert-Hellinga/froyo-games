## Description

Summary of the feature's bug (without describing implementation details)

Othello pieces are not placed on-click, do still affect board.


## Expected Behavior

When a user clicks on a potential move in an Othello game, 
a piece should appear in that square.

## Current Behavior

When a user clicks on a potential move in an Othello game, a piece does not appear in that
square. All the opponent's pieces between one of the user's pieces and the square clicked
change as intended.  

## Steps to Reproduce

Provide detailed steps for reproducing the issue.

 1. step 1: Start a 2-player game of Othello 
 1. step 2: Click on a green potential move square
 
## Failure Logs

N/A: Runtime error


## Hypothesis for Fixing the Bug

When a potential-move square is clicked in the Grid, it should update to the state of the players,
and its boolean notEmpty should be turned false. I believe notEmpty isn't being changed here. 
We only need to check the status of the appropriate square in the Grid for our test.