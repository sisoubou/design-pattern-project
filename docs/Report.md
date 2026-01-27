## Report by Serena Fadda and Laure Massemin

We identified the problems that this application had, then we made a merdmaid class diagram to visualize our new idea of the architecture.

The class GameCollection had too many responsibilities, including managing the collection of games, handling user interactions, and generating reports.
We decided to add a new interface to separate the reporting functionality from the GameCollection class.
This interface is implemented by two classes JsonGameRepository and CsvGameRepository that handle the generation of reports in JSON and CSV formats, respectively.

We removed the static methods from all classes and replaced them with instance methods.

Then we tested the new implemented ideas with mocks to ensure that they work as expected.

