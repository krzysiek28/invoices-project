# invoices project

FUNKCJONALNOŚĆ:

* rodzaje dokumentów(pojedyncze/cykliczne):
- faktura vat, proforma, zaliczka, rachunek, uproszczona, marża
* historia: faktury wystawione, zaplanowane
* baza klientów
* ustawienia:
- dane własnej firmy
- dodawanie użytowników 
- numeracja dokumentów
- rachunki bankowe
- produkty
- jednostki

oprócz tego:
tworzenie pdfów, pobieranie danych z gus, 


Bazy: faktur, firm, produktów, jednostek, rachunki bankowe, 

faktura ma: 
- numeracja (różna)
- sprzedawca nabywca 
- data, miejsce, 
- pozycje
- zapłata

KLASY:
user, facture, product, client(implementuje firm), seller(implementuje firm), myfirm(implementuje firm), bank account
INTERFEJSY:
firm

ZALEŻNOŚCI: 
- user HAS MANY facture, client, product, bank accounnt
- user HAS ONE firm
-facture HAS ONE seller, client
-facture HAS MANY product

TECHNOLOGIE:

*http://freemarker.apache.org/
*https://www.slf4j.org/
*https://github.com/svenkubiak/jBCrypt
*http://sparkjava.com/
