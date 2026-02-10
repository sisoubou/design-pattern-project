## Report by Serena Fadda and Laure Massemin

We identified the problems that this application had, then we made a merdmaid class diagram to visualize our new idea of the architecture.

The class GameCollection had too many responsibilities, including managing the collection of games, handling user interactions, and generating reports.
We decided to add a new interface to separate the reporting functionality from the GameCollection class.
This interface is implemented by two classes JsonGameRepository and CsvGameRepository that handle the generation of reports in JSON and CSV formats, respectively.

We removed the static methods from all classes and replaced them with instance methods.

Then we tested the new implemented ideas with mocks to ensure that they work as expected.


## Week 2

This week, we refactored the codebase to separate each command into its own class and introduced the Command and InteractiveCommand abstractions. 
We updated the GameCollection logic to improve testability and added comprehensive unit tests for all repositories and fonctionnalities. 
We also implemented three new features: prevention of duplicate games in the collection, a recommend game function based on player count, and a summary feature that displays random games only on weekends.


## Week 3

This week, we implemented two new features: undoing the last action and listing games for a specific number of players.
We also created new factories, including CommandFactory and DataFactory.
Additionally, we improved the code structure by organizing it into new packages.

