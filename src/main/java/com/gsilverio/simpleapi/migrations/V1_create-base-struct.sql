CREATE TABLE Book (
	Id int IDENTITY(1,1) NOT NULL,
	Title varchar(255) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL,
	Author varchar(255) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL,
	Isbn varchar(13) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL,
	PublicationYear smallint NOT NULL,
	AvailableUnits int DEFAULT 0 NOT NULL,
	Category varchar(255) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
	CreatedAt datetime NOT NULL,
	UpdatedAt datetime NOT NULL,
	CONSTRAINT PK__Book__3214EC072EB197D9 PRIMARY KEY (Id)
);


CREATE TABLE [User] (
	Id int IDENTITY(1,1) NOT NULL,
	Name varchar(255) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL,
	Age int NOT NULL,
	Email varchar(255) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL,
	Password varchar(500) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL,
	CreatedAt datetime NOT NULL,
	UpdatedAt datetime NOT NULL,
	CONSTRAINT PK__User__3214EC079030C59B PRIMARY KEY (Id),
	CONSTRAINT UQ__User__A9D105342CE472BB UNIQUE (Email)
);


CREATE TABLE Loan (
	Id int IDENTITY(1,1) NOT NULL,
	BookId int NOT NULL,
	UserId int NOT NULL,
	ExpectedReturnDate date NOT NULL,
	ActualReturnDate datetime NULL,
	CreatedAt datetime NOT NULL,
	UpdatedAt datetime NOT NULL,
	CONSTRAINT PK__Loan__3214EC0786130C86 PRIMARY KEY (Id),
	CONSTRAINT FK_Book FOREIGN KEY (BookId) REFERENCES Book(Id),
	CONSTRAINT FK_User FOREIGN KEY (UserId) REFERENCES [User](Id)
);