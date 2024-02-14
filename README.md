Live Football World Cup Scoreboard library that shows all the ongoing

# Overview
This application represents a football scoreboard system, which allows users to 
- start matches
- update scores
- finish matches
- retrieve match summaries.

# Details

For more details about application I encourage You so look into the unit tests;)

Briefly, before starting any match it checks whether if teams are defined correctly.
It allows updating scores many times during ongoing match, forth and back (in case of any human mistake, or VAR).
It does not allow updating scores or finishing a match that has not been already started.
Application generates the scoreboard summary in the correct order based on the match results 
and recent start dates.

# Technical information

## Persistence
For simplicity in memory storage has been used via java collection.
In the future, it can be changed to any different collection (like Map) or other persistence tool like JPA.


 

