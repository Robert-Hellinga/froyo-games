## Description

Summary of the feature's bug (without describing implementation details):

Othello game says the losing player wins

## Expected Behavior

When neither player can move in Othello, the game should pop up with a message that
the player with the most pieces won.

## Current Behavior

When neither player can move in Othello, the game pops up with a message that
the player with less pieces won.

## Steps to Reproduce

Provide detailed steps for reproducing the issue.

 1. step 1: Start a 2-player game of Othello
 1. step 2: Make moves until neither player can move anymore
 1. and so on

## Failure Logs

N/A

## Hypothesis for Fixing the Bug

Describe the test you think will verify this bug and the code fix you believe will solve this issue.

Given that our players are offset from their indexes in the list by 1, it has been a common mistake 
for the players to get turned around. I suspect that if we write a test with a small board
that compares the expected winner to the actual one, we'll verify that.