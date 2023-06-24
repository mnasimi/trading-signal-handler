## Problem Statement
This project involves developing a Trading application that executes Trading Algorithms based on provided signals. The TradingApplication is expected to handle an increasing number in the future, with up to 50 new signals added monthly.

## Proposed Solution
Use NoSQL Database: Store the signal specifications in database (e.g., MongoDB or ArangoDB). This allows for dynamic updates and avoids the need for code changes when new signals are added.

![img.png](img.png)

**_Example of signal configuration in MongoDB._**

## Implementation

The MethodMapBuilder provides the map of <actionName and action()> for the available methods in external library..
Considering the large number of signals, the signal-ID and corresponding actions-names are stored (as document) into db.
Per HTTP API request, the document (i.e., signal-ID and actions) retrieved from database and employ the map produced by
MethodMapBuilder, enabling us to invoke each function for the given signal-ID.

## Challenges
- Making sure that the trading-action are executed in the correct order.
- Matching/extracting actions name and argument values.
- The capability of accepting dynamic number of arguments values for trading actions.



