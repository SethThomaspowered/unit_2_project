# Ultimate playlist 
### By Juelan Brown and Seth Thomas

## Project Description 
You are listening to your favorite podcast when you hear about a new book or album that you need to checkout. How would you like to instantly be able to add that music or audiobook to your playlist? With this project, we seek to tackle this problem to create the ULTIMATE PLAYLIST.

## User Stories
- As a user, I want to be able to find music by artist or song title.
- As a user, I want to be able to create a playlist that includes songs, podcasts, and audiobooks.
- As a user, I want to be able to search by length of playlist.
- As a user, I want to be able to make changes to my playlists.
- As a user, I want to be able to delete a playlist that I no longer want.
- As  a content curator, I would like to be able to share my playlists.
- As an Admin User, I want to be able to create, edit, and delete songs, podcasts, and audiobooks.
- As an Admin User, I want to be able to search all users.


## ERD
![Media Library ERD (4)](https://user-images.githubusercontent.com/72534273/148461142-6dae3c54-5397-475f-a710-546c7b5ea617.png)

MVP Models (Goal Met)
- Users
- Playlists
- Music

Nice to Have Models ( Goal Met)
- Podcast 
- Audiobooks 
- Artists 
- Media ypes 

## EndPoints

Request Type	|Action	|URL	|Request Body	|Request Header	|Access
------------ |------------ | ------------- | ------------- | ------------- | -------------
|GET	|get single User	|/auth/user/...	|None	|Authorization Bearer TOKEN	|PRIVATE
|POST	|create single User	|/auth/users/register	|User Details	|None	|PUBLIC
|POST	|login User	|/auth/users/login	|User login Info	|None	|PUBLIC
|GET	|gets all Playlist	|/api/playlists	|None	|Authorization Bearer TOKEN	|PRIVATE
|GET	|get single Playlist	|/api/playlists/{playlistId}	|None	|Authorization Bearer TOKEN	|PRIVATE
|POST	|create single Playlist	|/api/playlists	|Playlist info	|Authorization Bearer TOKEN	|PRIVATE
|PUT	|update Playlist	|/api/playlists/{playlistId}	|Playlist info	|Authorization Bearer TOKEN	|PRIVATE
|DELETE	|delete Playlist	|/api/library/{mediaId}/podcast/{podcastId}	|None	|Authorization Bearer TOKEN	|PRIVATE
|GET	|get all Podcasts	|/api/library/1/podcast	|None	|None	|PUBLIC
|GET	|get single Podcast	|/api/library/1/podcast/{podcastId}	|None	|None	|PUBLIC
|POST	|create single Podcast	|/api/library/{mediaId}/podcast	|Podcast info	|None	|ADMIN
|DELETE	|delete Podcast	|/api/library/{mediaId}/podcast/{podcastId}	|None	|Authorization Bearer TOKEN	|ADMIN
|GET	|get all Music from playlist	|/api/artists/{artistId}/songs	|None	|None	|PUBLIC
|GET	|get single Music track from playlist	|/api/playlists/{playlistId}/music/{musicId} |None	|None	|PUBLIC
|POST	|add Music track to playlist	|/api/playlists/{playlistId}/music	|Music Track info	|Authorization Bearer TOKEN	|ADMIN
|PUT	|update Music track	|/api/playlists/{playlistId}/music/{musicId}	|Music Track info	|Authorization Bearer TOKEN	|ADMIN
|DELETE	|delete Music track	|/api/playlists/{playlistId}/music/{musicId}	|None	|Authorization Bearer TOKEN	|ADMIN
|GET	|get all Audiobooks	|/api/artists/{artistId}/book	|None	|None	|PUBLIC
|GET	|get single Audiobook	|/api/artists/{artistId}/book/{bookId}	|None	|None	|PUBLIC
|POST	|create Audiobook	|/auth/user/admin/...	|Audiobook info	|Authorization Bearer TOKEN	|ADMIN
|PUT	|update Audiobook	|/auth/user/admin/...	|Audiobook info	|Authorization Bearer TOKEN	|ADMIN
|DELETE	|delete Audiobook	|/auth/user/admin/...	|None	|Authorization Bearer TOKEN	|ADMIN
|GET	|get all Artists	|/api/artists	|None	|None	|PUBLIC
|GET	|get single Artist	|/api/artists/{artistId}	|None	|None	|PUBLIC
|POST	|create Artists	|/api/artists	|Artist Details	|Authorization Bearer TOKEN	|ADMIN
|GET	|get all Media Types	|/api/library	|None	|Authorization Bearer TOKEN	|ADMIN
|GET	|get by single Media type	|/api/library/{mediaType}	|None	|Authorization Bearer TOKEN	|ADMIN
|POST	|create Media Type	|/api/library	|Media Type Info	|Authorization Bearer TOKEN	|ADMIN
|GET	|create music track for overall music library 	|/api/library/{mediaId}/music	|None	|Authorization Bearer TOKEN	|ADMIN


## Project Challeneges
- Type definition error 
- - When adding additional media models, an error we faced was an Serilization on empyty bean error. Debugging for this error took up almost a full day. The solution to this problem was to add this code to our Application's dev properties: `spring.jackson.serialization.FAIL_ON_EMPTY_BEANS=false`
- Mapping many media files to many playlists.
- - Our first attempt was to use the `@ManyToMany` coding in our models, however, this spurred to many bugs downstream. So we pivoted and built out this utility by building an extisize repository folder and controller folder.

## Take Aways
