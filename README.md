## Problem Statement
This project involves developing a Trading application that executes Trading Algorithms based on provided signals. The TradingApplication is expected to handle an increasing number in the future, with up to 50 new signals added monthly.

## Proposed Solution
Use NoSQL Database: Store the signal specifications in database (e.g., MongoDB or ArangoDB). This allows for dynamic updates and avoids the need for code changes when new signals are added.

![img.png](img.png)
**_Example of signal configuration in MongoDB._**

## Implementation
The signal configuration json stored in database. Per request, the configuration retrieved from database and compare with map of provided trading actions. When the matching process is complete, the nxt process to invoke each action based on the provided order.



