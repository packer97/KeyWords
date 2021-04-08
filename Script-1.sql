CREATE TABLE Requests (
id integer UNIQUE NOT NULL PRIMARY KEY autoincrement,
url VARCHAR(60),
dateTime INTEGER);
CREATE TABLE Statistic (
id integer UNIQUE NOT NULL PRIMARY KEY autoincrement,
request_id integer NOT NULL, 
keyword VARCHAR(60),
quantity INTEGER,
FOREIGN KEY(request_id) REFERENCES Requests(id));