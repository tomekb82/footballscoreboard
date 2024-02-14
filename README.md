Live Football World Cup Scoreboard library that shows all the ongoing

# Overview
This application represents a football scoreboard system, which allows users to 
- start matches
- update scores
- finish matches
- retrieve match summaries for ongoing matches

# Usage

First build a jar using command:

    mvn clean package

After that add this build jar as external library and enjoy;)

See unit test to check the usage of the application.
There are following steps: 

    // 1. create scoreboard
    Scoreboard scoreboard = new Scoreboard();
    // 2. start all matches  
    scoreboard.startMatch(Team.of( "Mexico"), Team.of("Canada"))
      .startMatch(Team.of( "Spain"), Team.of("Brazil"))
      .startMatch(Team.of( "Germany"), Team.of("France"))
      .startMatch(Team.of( "Uruguay"), Team.of("Italy"))
      .startMatch(Team.of( "Argentina"), Team.of("Australia"));

     // 3. update update scores (can be many times for given match, see id 1)
     scoreboard
        .updateScore(0, Score.of(0), Score.of(5))
        .updateScore(1, Score.of(10), Score.of(2))
        .updateScore(1, Score.of(10), Score.of(5))
        .updateScore(1, Score.of(10), Score.of(10))
        .updateScore(2, Score.of(2), Score.of(2))
        .updateScore(3, Score.of(6), Score.of(6))
        .updateScore(4, Score.of(3), Score.of(1));

     // 4. get match summary report (only for ongoing matches)
     MatchSummary matchSummary = scoreboard.getMatchSummary();

     // 5. finish matches
     scoreboard
       .finishMatch(4)
       .finishMatch(3)
       .finishMatch(2)
       .finishMatch(1)
       .finishMatch(0)

Remember: matchIndex could change after removing record from the collection.
If You want to have different behaviour please go to the persistence section,

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

### Next steps
In the future, it can be changed to any different collection (like Map) 
or other persistence tool like JPA.
Currently we use dynamic index for each match using List collection for simplicity,
that could change during some collection manipulations.


 

