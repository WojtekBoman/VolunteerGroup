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

<ul>
  <li>GET /api/wydarzenia       Pobranie wydarzeń - Możliwość pobrania wielu wydarzeń dla każdego zalogowanego użytkownika</li>
  <li>GET /api/wydarzenia/{id}  Pobranie wydarzenia - Możliwość pobrania określonego wydarzenia dla każdego zalogowanego użytkownika</li>
  <li>POST /api/wydarzenia      Dodanie wydarzenia - Możlowość dodania nowego wydarzenia przez przewodniczącego</li>
</ul>

### Udzial

<ul>
  <li>GET /api/udzial/wydarzenia-uzytkownika        Pobranie wydarzeń dla użytkownika - Możliwość pobrania wszystkich wydarzeń w których zalogowany użytkownik bierze udział</li>
  <li>GET /api/udzial/wydarzenia-uzytkownika/{id-uzytkownika}       Pobranie wydarzeń dla użytkownika - Możliwość pobrania wszystkich wydarzeń w których wskazany użytkownik bierze udział</li>
  <li>GET /api/udzial/uzytkownicy-wydarzenia/{id-wydarzenia}       Pobranie użytkowników dla wydarzenia - Możlowość pobrania wszystkich użytkoników przypisanych do danego wydarzenia</li>
  <li>POST /api/udzial/wez/{id-wydarzenia}        Wzięcie udziału w wydarzeniu - Możliwość przypisania zalogowanego użytkownika do danego wydarzenia</li>
  <li>POST /api//udzial/anuluj/{id-wydarzenia}       Anulowanie udziału w wydarzeniu - Możliwość usunięcia przypisania zalogowanego użytkownika do danego wydarzenia </li>
</ul>

