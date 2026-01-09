# WineFinder
Android
WineFinder – Android alkalmazás
Projekt leírása:

A WineFinder egy Android alkalmazás, amely különböző borokat jelenít meg egy nyilvános REST API segítségével.
Az alkalmazás lehetőséget ad a borok böngészésére, részleteik megtekintésére, valamint kedvenc borok elmentésére.
A projekt az Android szoftverfejlesztés tárgy beadandó feladataként készült.

Felhasznált technológiák:

Java

Android SDK

RecyclerView

Fragment alapú navigáció

Retrofit + Gson (REST API kommunikáció)

Room adatbázis (perzisztens adattárolás)

Glide (képek betöltése)

Adatforrás

Az alkalmazás a következő nyilvános API-t használja:
https://api.sampleapis.com/wines

Funkciók

Borok listázása
Vörös és fehér borok megjelenítése
Lista megjelenítése RecyclerView segítségével

Részletek megtekintése

Bor neve

Pincészet

Származási hely (ha elérhető)

Kép (placeholder jelenik meg hiány esetén)

Kedvencek (Favorites)
Borok megjelölése kedvencként
Kedvencek perzisztens tárolása Room adatbázisban
Kedvencek megjelenítése külön képernyőn
Az állapot alkalmazás újraindítása után is megmarad

Alkalmazás felépítése

UI réteg: Activity-k és Fragment-ek

Adatmodell: WineDto

Adattárolás: Room (Entity, DAO, Database)

Hálózati réteg: Retrofit + API interface

Adapter: RecyclerView adapter egyedi eseménykezeléssel 

Készítette: 

Németh Ildikó
Android szoftverfejlesztés beadandó
