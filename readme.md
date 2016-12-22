# Posse Android Coding Challenge

#### Charles Drews

- charlesdrews@gmail.com
- (847) 269-0691
- [charlesdrews.com](http://www.charlesdrews.com/)

#### Requirements

1. Create a simple app that displays the information contained in the attached JSON.  

1. If you are displaying more than one user on the screen, just show the first name.  Clicking on the name should show more detailed information about him.  

1. Create appropriate models for the JSON data. You can use a third-party library.

1. The app should work on your average phone and a tablet with roughly the Nexus 9 specs. (Emulated device is fine)

1. It should support landscape and portrait orientation. 

#### Features

- User can view a list of Posse programmers
  - Entries show basic info: name, location, platform (Android/iOS/Ruby)
  - Entries have placeholder for photo; currently filled with that programmer's favorite color

- User can select a person and view all detail about that person in a separate pane

- On a large enough screen (width >= 820 dp) the list and detail panes will appear side by side. On smaller devices, each pane will be on a separate screen.

- User can search through people by providing a search query
  - Search returns users whose name, location, platform, and/or favorite color match the search query
  - Searches persist across orientation changes, and when returning from the detail screen on a small device

- User can filter people by platform and location
  - Use can select filter values via a pop-up window
  - Once applied, filters appear in distinctive green boxes above the list of people
  - User can remove filters simply by tapping their green box, or by resetting their filter selections in the pop-up window
  - Filters persist across orientation changes, and when returning from the detail screen on a small device

#### Implementation

- Follows the Model, View, Presenter architecture pattern
  - Contract interfaces enforce division of responsibilities for each main feature (people list, person detail)
  - Threading is handled by presenters using AsyncTask; not by view or model layers

- Presenters are cached in memory in order to preserve state across orientation changes and navigation between activities

- Master detail flow was implemented using fragments; filter pop-up dialog via a DialogFragment

- Data repository constructed as though it were pulling data from a web API
  - Separate local (SQLite) and remote (in this case, reading from local JSON file, but typically would be from HTTP request) repositories both implement the same data source interface
  - The "remote" data source parses the JSON file using GSON and corresponding model classes. These are used to create instances of the primary model, the Person class, which is stored in the local database and used throughout the app.
  - A third, presenter-facing data repository also implements the data source interface, utilizes both the local and remote sources, and allows the presenter to be agnostic (and unaware) as to whether data is coming from local or remote source
