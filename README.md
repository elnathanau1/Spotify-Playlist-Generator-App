# Spotify Playlist Generator App

Interacts with Spotify API to generate custom playlists given a root Track. Gives user more control over how recommendations are generated, uses basic AI techniques to generate recommended tracks. Swing GUI implemented to display playlists to user and allow user to add/remove tracks.

## Getting Started

Import the folder into Eclipse as existing project. Inside the main package, run Main.java.

## GUI

When GUI loads, there will be fields for user to enter input. Get Spotify URI from Spotify. The 'Advanced Settings' tab holds options for further user control over heuristic function. After creating playlist, can view the tracks added and other possible tracks in the 'Added Tracks' and 'Recommended Tracks' tab. In those tabs is also a playback panel to play/pause tracks, in addition to adding/removing tracks from the playlist. 

## Testing

JUnit test files can be found in the test package. 

## Future Plans
* implement 'Statistics' page, calculate stats


## Implementations

* [Spotify API] - https://developer.spotify.com/web-api/
* [Jackson] - https://github.com/FasterXML/jackson
* [Apache] - https://projects.apache.org/project.html?httpcomponents-commons_httpclient
* Java Swing Library

## Purpose
UIUC CS 242 Final Project

## Author
Elnathan Au (eau3)

