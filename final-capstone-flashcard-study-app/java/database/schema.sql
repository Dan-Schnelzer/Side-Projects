BEGIN TRANSACTION;

DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS cards;
DROP TABLE IF EXISTS decks;
DROP TABLE IF EXISTS card_deck;
DROP TABLE IF EXISTS tags;
DROP TABLE IF EXISTS card_tag;
DROP SEQUENCE IF EXISTS seq_user_id;

CREATE SEQUENCE seq_user_id
  INCREMENT BY 1
  NO MAXVALUE
  NO MINVALUE
  CACHE 1;


CREATE TABLE users (
	user_id int DEFAULT nextval('seq_user_id'::regclass) NOT NULL,
	username varchar(50) NOT NULL,
	password_hash varchar(200) NOT NULL,
	role varchar(50) NOT NULL,
	CONSTRAINT PK_user PRIMARY KEY (user_id)
);

INSERT INTO users (username,password_hash,role) VALUES ('user','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('admin','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_ADMIN');

CREATE TABLE cards (
	card_id serial NOT NULL,
	front varchar(200) NOT NULL,
	back varchar(500) NOT NULL,
	user_id int NOT NULL,
	card_tags varchar (100) NULL,
	CONSTRAINT PK_cards PRIMARY KEY (card_id),
	CONSTRAINT FK_cards_users FOREIGN KEY (user_id) REFERENCES users(user_id)
);

CREATE TABLE decks (
	deck_id serial NOT NULL,
	deck_name varchar(100) NOT NULL,
	user_id int NOT NULL,
	isPublic boolean NOT NULL DEFAULT FALSE,
	isSubmitted boolean NOT NULL DEFAULT FALSE,
	CONSTRAINT PK_decks PRIMARY KEY (deck_id),
	CONSTRAINT FK_decks_users FOREIGN KEY (user_id) REFERENCES users(user_id)
);

CREATE TABLE card_deck (
	card_id int NOT NULL,
	deck_id int NOT NULL,
	CONSTRAINT PK_card_deck PRIMARY KEY(card_id, deck_id),
	CONSTRAINT FK_card_deck_card FOREIGN KEY (card_id) REFERENCES cards(card_id),
	CONSTRAINT FK_card_deck_deck FOREIGN KEY (deck_id) REFERENCES decks(deck_id)
);




INSERT INTO cards (front , back, user_id, card_tags) VALUES ('How long do Alligators live? ' , ' Alligators generally live between 30 & 50 years.', 1, 'Animal Facts');
INSERT INTO cards (front , back, user_id, card_tags) VALUES ('What is the scientific name of a grey wolf? ' , ' Canis lupus lupus is the scientific name for a grey wolf.', 1, 'Animal Facts');
INSERT INTO cards (front , back, user_id, card_tags) VALUES ('How many muscles do cats have in each ear? ' , ' A cat has 32 muscles in each ear.', 1, 'Animal Facts');
INSERT INTO cards (front , back, user_id, card_tags) VALUES ('How many kills a year does a lion usually make? ' , ' A lion in the wild usually makes no more than 20 kills a year.', 1, 'Animal Facts');
INSERT INTO cards (front , back, user_id, card_tags) VALUES ('What is the most poisonous fish? ' , ' The most poisonous fish in the world is the stone fish.', 1, 'Animal Facts');

-- educated guess that the card ids will be 1 - 5. using this guess to make deck



INSERT INTO decks (deck_name, user_id) VALUES ('Animal Deck' , 1);

INSERT INTO card_deck (card_id, deck_id) VALUES (1,1);
INSERT INTO card_deck (card_id, deck_id) VALUES (2,1);
INSERT INTO card_deck (card_id, deck_id) VALUES (3,1);
INSERT INTO card_deck (card_id, deck_id) VALUES (4,1);
INSERT INTO card_deck (card_id, deck_id) VALUES (5,1);

-- making a second deck of fake cards also from user id # 1

INSERT INTO cards (front , back, user_id, card_tags) VALUES ('Magnanimnous ' , ' generous in forgiving an insult or injury: free from petty resentfulness or vindictiveness', 1, 'Useless vocab definitions');
INSERT INTO cards (front , back, user_id, card_tags) VALUES ('Jackdaw ' , ' a glossy, black, European bird of the crow family, that nests in towers, ruins, ect', 1, 'Useless vocab definitions, Animal Facts');
INSERT INTO cards (front , back, user_id, card_tags) VALUES ('Wayzgoose ' , ' a works outing made annually by a printing house', 1, 'Useless vocab definitions');
INSERT INTO cards (front , back, user_id, card_tags) VALUES ('Daglock ' , ' a dangling, matted lock of wool on a sheep', 1, 'Useless vocab definitions');
INSERT INTO cards (front , back, user_id, card_tags) VALUES ('Kalanchoe ' , ' any of several cheifly African and ASian succulent plants or shrubs belonging to the genus Kalanchoe', 1, 'Useless vocab definitions');

-- educated guess that the card ids will be 6 -10. using this guess to make deck



INSERT INTO decks (deck_name, user_id) VALUES ('Random Vocab Words' , 1);

INSERT INTO card_deck (card_id, deck_id) VALUES (6,2);
INSERT INTO card_deck (card_id, deck_id) VALUES (7,2);
INSERT INTO card_deck (card_id, deck_id) VALUES (8,2);
INSERT INTO card_deck (card_id, deck_id) VALUES (9,2);
INSERT INTO card_deck (card_id, deck_id) VALUES (10,2);

-- making a third deck of fake cards also from user id # 1

INSERT INTO cards (front , back, user_id, card_tags) VALUES ('What is an array? ' , ' An array is a collection of similar data elements. Arrays is a simple data structure where each data element can be accessed directly by only using its index number. ', 1, 'Java');
INSERT INTO cards (front , back, user_id, card_tags) VALUES ('What is Encapsulation? ' , 'Encapsulation is one of the components of OOP. It is the concept of hiding variables or data within a class, limiting the points of access. ', 1, 'Java');
INSERT INTO cards (front , back, user_id, card_tags) VALUES ('What is Polymorphism? ' , ' Polymorphism is the idea that something can be assigned a different meaning or usage based on its context. This specifically allows variables and objects to take on more than one form.', 1, 'Java');
INSERT INTO cards (front , back, user_id, card_tags) VALUES ('What is an Interface? ' , 'An Interface is an abstract class with empty bodies that is used to group related methods together.Interfaces provide you with a way to say what classes can do. ', 1, 'Java');
INSERT INTO cards (front , back, user_id, card_tags) VALUES ('What is Dependency Injection? ' , ' Dependency Injection is where instances of classes that are depended on are injected into a new object rather than created by that object. ', 1, 'Java');

-- educated guess that the card ids will be 11 -15. using this guess to make deck

INSERT INTO decks (deck_name, user_id) VALUES ('Java Questions' , 1);

INSERT INTO card_deck (card_id, deck_id) VALUES (11,3);
INSERT INTO card_deck (card_id, deck_id) VALUES (12,3);
INSERT INTO card_deck (card_id, deck_id) VALUES (13,3);
INSERT INTO card_deck (card_id, deck_id) VALUES (14,3);
INSERT INTO card_deck (card_id, deck_id) VALUES (15,3);

-- making a fourth deck of fake cards also from user id # 1

INSERT INTO cards (front , back, user_id, card_tags) VALUES (' OOP ' , ' Object Oriented Programming', 1, 'Java Acronyms');
INSERT INTO cards (front , back, user_id, card_tags) VALUES (' TDD ' , ' Test Driven Developement', 1, 'Java Acronyms');
INSERT INTO cards (front , back, user_id, card_tags) VALUES (' AAA ' , ' Arrange , Act , Assert (for testing) ', 1, 'Java Acronyms');
INSERT INTO cards (front , back, user_id, card_tags) VALUES (' DAO ' , ' Data Access Object', 1, 'Java Acronyms');
INSERT INTO cards (front , back, user_id, card_tags) VALUES (' JDBC ' , ' Java Database Connectivity ', 1, 'Java Acronyms');

-- educated guess that the card ids will be 16 - 20. using this guess to make deck

INSERT INTO decks (deck_name, user_id) VALUES ('Java Acronym' , 1);

INSERT INTO card_deck (card_id, deck_id) VALUES (16,4);
INSERT INTO card_deck (card_id, deck_id) VALUES (17,4);
INSERT INTO card_deck (card_id, deck_id) VALUES (18,4);
INSERT INTO card_deck (card_id, deck_id) VALUES (19,4);
INSERT INTO card_deck (card_id, deck_id) VALUES (20,4);

-- making a fifth deck for user 1 that is just random cards 

INSERT INTO decks (deck_name, user_id) VALUES ('Random Quiz' , 1);

INSERT INTO card_deck (card_id, deck_id) VALUES (1,5);
INSERT INTO card_deck (card_id, deck_id) VALUES (5,5);
INSERT INTO card_deck (card_id, deck_id) VALUES (7,5);
INSERT INTO card_deck (card_id, deck_id) VALUES (9,5);
INSERT INTO card_deck (card_id, deck_id) VALUES (11,5);
INSERT INTO card_deck (card_id, deck_id) VALUES (13,5);
INSERT INTO card_deck (card_id, deck_id) VALUES (15,5);
INSERT INTO card_deck (card_id, deck_id) VALUES (16,5);
INSERT INTO card_deck (card_id, deck_id) VALUES (19,5);
INSERT INTO card_deck (card_id, deck_id) VALUES (20,5);


-- making a deck of fake cards  from admin id # 2

INSERT INTO cards (front , back, user_id, card_tags) VALUES (' Hola ' , ' Hello', 2, 'Basic Spanish');
INSERT INTO cards (front , back, user_id, card_tags) VALUES (' Por favor ' , ' Please', 2, 'Basic Spanish');
INSERT INTO cards (front , back, user_id, card_tags) VALUES (' Gracias ' , ' Thank you ', 2, 'Basic Spanish');
INSERT INTO cards (front , back, user_id, card_tags) VALUES (' Lo siento ' , ' Sorry', 2, 'Basic Spanish');
INSERT INTO cards (front , back, user_id, card_tags) VALUES (' Salud ' , ' Bless you (after someone sneezes) ', 2, 'Basic Spanish');

-- educated guess that the card ids will be 21 -25. using this guess to make deck

INSERT INTO decks (deck_name, user_id, ispublic) VALUES ('Spanish' , 2, 'true');

INSERT INTO card_deck (card_id, deck_id) VALUES (21,6);
INSERT INTO card_deck (card_id, deck_id) VALUES (22,6);
INSERT INTO card_deck (card_id, deck_id) VALUES (23,6);
INSERT INTO card_deck (card_id, deck_id) VALUES (24,6);
INSERT INTO card_deck (card_id, deck_id) VALUES (25,6);

-- making a second deck of fake cards  from admin id # 2

INSERT INTO cards (front , back, user_id, card_tags) VALUES (' Bonjour ' , ' Hello, Good morning.', 2, 'Basic French');
INSERT INTO cards (front , back, user_id, card_tags) VALUES (' Au revior ' , ' Goodbye', 2, 'Basic French');
INSERT INTO cards (front , back, user_id, card_tags) VALUES (' Merci ' , ' Thank you ', 2, 'Basic French');
INSERT INTO cards (front , back, user_id, card_tags) VALUES (' Oui ' , ' Yes', 2, 'Basic French');
INSERT INTO cards (front , back, user_id, card_tags) VALUES (' Merci beaucoup ' , ' Thank you very much ', 2, 'Basic French');

-- educated guess that the card ids will be 26 -30. using this guess to make deck

INSERT INTO decks (deck_name, user_id, ispublic) VALUES ('French' , 2, 'true');

INSERT INTO card_deck (card_id, deck_id) VALUES (26,7);
INSERT INTO card_deck (card_id, deck_id) VALUES (27,7);
INSERT INTO card_deck (card_id, deck_id) VALUES (28,7);
INSERT INTO card_deck (card_id, deck_id) VALUES (29,7);
INSERT INTO card_deck (card_id, deck_id) VALUES (30,7);

COMMIT TRANSACTION;
