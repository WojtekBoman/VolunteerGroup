# VolunteerGroup

Aplikacja ułatwiająca organizację wydarzeń charytatywnych w obrębie koła wolontaryjnego Psi Patrol 
i wspomagająca komunikację między użytkownikami korzystającymi z aplikacji.

## Użytkownicy 

<ul>
  <li>Wolontariusze</li>
  <li>Przewodniczący koła</li>
  <li>Pracownicy schroniska</li>
</ul>

## Wykorzystywane technologie

<ul>
  <li>React.js</li>
  <li>Spring Boot</li>
  <li>Hibernate</li>
  <li>MySql</li>
</ul>

## Autorzy 

<ul>
  <li>Wojciech Boman</li>
  <li>Jakub Szańca</li>
</ul>


## Requesty

### Wydarzenie

| Request| Opis |
|-|-|
GET /api/wydarzenia | Pobranie wydarzeń - Możliwość pobrania wielu wydarzeń dla każdego zalogowanego użytkownika
GET /api/wydarzenia/{id} | Pobranie wydarzenia - Możliwość pobrania określonego wydarzenia dla każdego zalogowanego użytkownika
POST /api/wydarzenia | Dodanie wydarzenia - Możlowość dodania nowego wydarzenia przez przewodniczącego


### Udzial
| Request| Opis |
|-|-|
GET /api/udzial/wydarzenia-uzytkownika | Pobranie wydarzeń dla użytkownika - Możliwość pobrania wszystkich wydarzeń w których zalogowany użytkownik bierze udział
GET /api/udzial/wydarzenia-uzytkownika/{id-uzytkownika} | Pobranie wydarzeń dla użytkownika - Możliwość pobrania wszystkich wydarzeń w których wskazany użytkownik bierze udział
GET /api/udzial/uzytkownicy-wydarzenia/{id-wydarzenia} | Pobranie użytkowników dla wydarzenia - Możlowość pobrania wszystkich użytkoników przypisanych do danego wydarzenia
POST /api/udzial/wez/{id-wydarzenia} | Wzięcie udziału w wydarzeniu - Możliwość przypisania zalogowanego użytkownika do danego wydarzenia
POST /api//udzial/anuluj/{id-wydarzenia} | Anulowanie udziału w wydarzeniu - Możliwość usunięcia przypisania zalogowanego użytkownika do danego wydarzenia

### Oferta

| Request| Opis |
|-|-|
GET /api/oferty | Pobranie ofert - Możliwość pobrania wielu ofert dla każdego zalogowanego użytkownika
GET /api/oferty/{id} | Pobranie oferty - Możliwość pobrania wybranej oferty dla każdego zalogowanego użytkownika
POST /api/oferty | Dodanie oferty - Możlowość dodania nowej oferty przez pracownika schroniska

### Pracownik schroniska

| Request| Opis |
|-|-|
POST /api/nazwa-schroniska | Zmiana nazwy schroniska - Możlowość zmiany nazwy schroniska dla pracownika

