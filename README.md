# Starter Code für 2. Pratkikumsaufgabe Software-Architektur Sommer 2017 

## Maven Befehle
Befehle sind im Root Verzeichnis des Projekts auszuführen:

- Authservice: mvn clean install -pl authorizationservice -am
- Mediaservice: mvn clean install -pl mediaservice -am

Für das deployment auf Heroku:

- Authservice: mvn -pl authorizationservice heroku:deploy
- Mediaservice: mvn -pl mediaservice heroku:deploy



## Exceptions
Es wurden für die möglichen auftretenen Fehler selbstständige Exceptions erstellt, wo jede einen uniquen Errorcode besitzt.
 Folgende Exceptions existieren:
   
 | Errorcode | Name | Beschreibung |
  ----------- | -------- | ---------------
  | -100 | TITLE_MISSING | Wenn von einem Medium der Titel fehlt |
  | -110 | IDENTIFIER_ALREADY_EXISTS | Wenn bereits ein Buch (ISBN) oder eine Disc (Barcode) mit der selben Identifiernummer vorhanden ist |
  | -120 | IDENTIFIER_IMMUTABLE | Wenn bei einem Update versucht wird, den Identifier des Mediums zu ändern |
  | -130 | IDENTIFIER_MISSING | Wenn der Identifier des Mediums nicht vorhanden ist. |
  | -140 | IDENTIFIER_INVALID | Wenn der Identifier des Mediums nicht valide ist. |
  | -150 | AUTHOR_MISSING | Wenn der Autor des Buches fehlt. |
  | -160 | DIRECTOR_MISSING | Wenn der Direktor der Disc fehlt. |
 
 ## Beschreibung REST Schnittstelle
 Der Herokulink ist https://vast-shelf-77148.herokuapp.com/resource/media
 
 Folgende Methoden können aufgerufen werden:
 
 | URL | HTTP Method | URL Paramter | JSON Parameter | Beschreibung | Mögliche Fehlercodes |
  ----------- | -------- | --------------- | ---- | ---- | ---
 | /books | GET | KEINE | KEINE | Gibt alle gespeicherten Bücher als Array zurück | KEINE |
  | /books/{isbn} | GET | {isbn} = ISBN des Buches | KEINE | Gibt das Buch mit der dementsprechenden ISBN Nummer zurück | KEINE |
  | /books | POST | KEINE | Book | Speichert das mitgegebene Buch | TITLE_MISSING, IDENTIFIER_ALREADY_EXISTS, IDENTIFIER_MISSING, IDENTIFIER_INVALID, AUTHOR_MISSING |
  | /books/{isbn} | PUT | {isbn} = ISBN des Buches | Book | Updated das Buch mit der gegebenen ISBN | IDENTIFIER_MISSING, IDENTIFIER_INVALID, IDENTIFIER_IMMUTABLE |
  | /discs | GET | KEINE | KEINE | Gibt alle gespeicherten Discs als Array zurück | KEINE |
  | /discs/{barcode} | GET | {barcode} = Barcode der Disc | KEINE | Gibt die Disc mit der dementsprechenden Barcode Nummer zurück | KEINE |
  | /discs | POST | KEINE | Disc | Speichert die mitgegebene Disc | TITLE_MISSING, IDENTIFIER_ALREADY_EXISTS, IDENTIFIER_MISSING, IDENTIFIER_INVALID, DIRECTOR_MISSING |
  | /discs/{barcode} | PUT | {barcode} = Barcode der Disc | Disc | Updated die Disc mit dem gegebenen Barcode | IDENTIFIER_MISSING, IDENTIFIER_INVALID, IDENTIFIER_IMMUTABLE |
  
  ## JSON Objekte
  
  Book:
  
  {
    "title":"",
    "author":"",
    "isbn":""
  }
  
  Disc:
  
  {
    "title":"",
    "director":"",
    "barcode":"",
    "fsk":18
  }
  
  TITLE: String
  AUTHOR: String
  BARCODE: String
  ISBN: String
  FSK: Number
  
  # 3. Praktikumsaufgabe
  ## Authentifizierung
  
  ## Authenticate service
  
  https://fast-basin-46958.herokuapp.com/auth
 
 /login
 /logout
 /validate