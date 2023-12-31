## Problem Statement
This project involves developing a Trading application that executes Trading Algorithms based on provided signals. The TradingApplication is expected to handle an increasing number in the future, with up to 50 new signals added monthly.

## Proposed Solution
Externalize the configuration: Utilizing a NoSQL database (such as MongoDB or ArangoDB) to store the signal specifications. This approach enables dynamic updates and eliminates the necessity for modifying code when new signals are introduced. While there are alternative solutions available, opting for this approach is primarily driven by the anticipation of efficiently managing a large volume of signals in the future.

![img.png](img.png)

**_Example of signal configuration in MongoDB._**

#### Alternative Approach
- Use polymorphism
- Implement a strategy pattern
- Use a map-based approach

## Implementation

The MethodMapBuilder provides the map of <actionName and action()> for the available methods in the external library.
Considering a large number of signals, the signal ID and corresponding actions-names are stored (as a document) in DB.
Per HTTP API request, the document (i.e., signal-ID and actions) retrieved from the database and employ the map produced by
MethodMapBuilder, enabling us to invoke each function for the given signal ID.

#### Implementation Challenges
- Making sure that the trading-action are executed in the **correct order**.
- Matching/extracting actions name and argument values.
- The capability of accepting a **dynamic number of argument** values for trading actions.

## Technical Debts (to-dos)
- The method in business logic which deals with the extraction of action name and argument from the configuration file, could be implemented in a more robust way. At the current implementation, a regular expression is employed to do this job which is maybe fragile because of the dynamic nature of action names and a number of arguments.
- More tests especially for checking the invocation of actions and order of execution are required.



