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



## ERD
![Media Library ERD (1)](https://user-images.githubusercontent.com/72534273/147978728-57681628-b286-4b43-952e-b466d921f45c.png)

## EndPoints
Request Type	|Action	|URL	|Request Body	|Request Header	|Access
------------ |------------ | ------------- | ------------- | ------------- | -------------

GET	| gets all Users	|/auth/user/admin/getusers	|None	|None	|ADMIN
GET	|get single User	|/auth/user/...	|None	|Authorization Bearer TOKEN	|PRIVATE
POST	|create single User	|/api/register	User Details	|None	|PUBLIC
POST	|login User	|/api/login	User login Info	None	PUBLIC
PUT	|update User	|/auth/user/...	User Details	|Authorization Bearer TOKEN	|PRIVATE
DELETE	|delete User	|/auth/user/...	None	|Authorization Bearer TOKEN	|PRIVATE
GET	|gets all Playlist	|/auth/user/...	|None	|Authorization Bearer TOKEN	|PRIVATE
GET	|get single Playlist	|/auth/user/...	|None	|Authorization Bearer TOKEN	|PRIVATE
POST	|create single Playlist	|/auth/user/...	|Playlist info	|Authorization Bearer TOKEN	|PRIVATE
PUT	|update Playlist	|/auth/user/...	|Playlist info	|Authorization Bearer TOKEN	|PRIVATE
DELETE	|delete Playlist	|/auth/user/...	|None	|Authorization Bearer TOKEN	|PRIVATE
GET	|get all Podcasts	|/api/artists/{artistId}/podcasts	|None	|None	|PUBLIC
GET	|get single Podcast	|/api/artists/{artistId}/pidcasts/{podcastId}	|None	|None	|PUBLIC
POST	|create single Podcast	|/auth/user/admin/...	|Podcast info	|None	A|DMIN
PUT	|update Podcast	|/auth/user/admin/...	|Podcast info	|Authorization Bearer TOKEN	|ADMIN
DELETE	|delete Podcast	|/auth/user/admin/...	|None	|Authorization Bearer TOKEN	|ADMIN
GET	|get all Music	|/api/artists/{artistId}/songs	|None	|None	|PUBLIC
GET	|get single Music track	|/api/artists/{artistId}/songs/{songId}	|None	|None	|PUBLIC
POST	|create  Music track	|/auth/user/admin/...	|Music Track info	|Authorization Bearer TOKEN	|ADMIN
PUT	|update Music track	|/auth/user/admin/...	|Music Track info	|Authorization Bearer TOKEN	|ADMIN
DELETE	|delete Music track	|/auth/user/admin/...	|None	|Authorization Bearer TOKEN	|ADMIN
GET	|get all Audiobooks	|/api/artists/{artistId}/book	|None	|None	|PUBLIC
GET	|get single Audiobook	|/api/artists/{artistId}/book/{bookId}	|None	|None	|PUBLIC
POST	|create Audiobook	|/auth/user/admin/...	|Audiobook info	|Authorization Bearer TOKEN	|ADMIN
PUT	|update Audiobook	|/auth/user/admin/...	|Audiobook info	|Authorization Bearer TOKEN	|ADMIN
DELETE	|delete Audiobook	|/auth/user/admin/...	|None	|Authorization Bearer TOKEN	|ADMIN
GET	|get all Artists	|/api/artists/	|None	|None	|PUBLIC
GET	|get single Artist	|/api/artists/{artistId}	|None	|None	|PUBLIC
POST	|create Artists	|/auth/user/admin/...	|Artist Details	|Authorization Bearer TOKEN	|ADMIN
DELETE	|delete Artists	|/auth/user/admin/...	|None	Authorization Bearer TOKEN	|ADMIN
GET	|get all Media Types	|/auth/user/admin/...	|None	Authorization Bearer TOKEN	|ADMIN
GET	|get single Media type	|/auth/user/admin/...	|None	Authorization Bearer TOKEN	|ADMIN
POST	|create Media Type	|/auth/user/admin/...	|Media Type Info	Authorization Bearer TOKEN	|ADMIN
DELETE	|delete Media Type	|/auth/user/admin/...	|None	Authorization Bearer TOKEN	|ADMIN
------------ |------------ | ------------- | ------------- | ------------- | -------------


[Ultimate Playlist EndPoints (Draft) - Sheet1.pdf](https://github.com/SethThomaspowered/unit_2_project/files/7804107/Ultimate.Playlist.EndPoints.Draft.-.Sheet1.pdf)

