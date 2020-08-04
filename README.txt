-------------------------------------------------------------------------------

                --- Programare Orientata pe Obiecte ---
                            "League of OOP"

            --- Grigore Lucian-Florin 324 CD             ---
            --- Facultatea de Automatica si Calculatoare ---
            --- Universitatea Politehnica, Bucuresti     ---

-------------------------------------------------------------------------------
                                Etapa I
-------------------------------------------------------------------------------

Structura temei:
  Am considerat impartirea entitatilor importante in clase, cum ar fi:
  - punctul de plecare al jocului (Main)
  - clasa care se ocupa de desfasurarea jocului si de alte operatii
  suplimentare (Game)
  - cele pentru fiecare tip de erou (Wizard, Pyromancer, Knight, Rogue)
  si clasa abstracta din care se trag toate (GameCharacter)
  - clasa care reprezinta o abilitate cu efect prelungit (OverTimeAbility)
  - clasa pentru constante (Constants)
  - clasa care sa memoreze toate datele de input ale jocului (InputData)
  - clasa care sa se ocupe de citirea inputului si scrierea
  rezultatelor finale ale jocului (IOAssistant)
  - cate un enum pentru tipurile de eroi (CharacterType), locatii (LocationType)
  sau miscari (MovementType)


Descrierea implementarii:
  Jocul porneste din functia Main care se ocupa de apelarea functiei care sa
  citeasca datele de intrare (din clasa IOAssistant), celei care sa porneasca
  desfasurarea propriu-zisa a jocului (din clasa Game) si celei care sa se
  ocupe de scrierea rezultatelor finale ale jocului (tot din IOAssistant).

  In clasa Game, in functia startGame are loc apelarea de functii adiacente
  care sa indeplineasca anumite scopuri in joc, cum ar fi:
  - applyCurrentRoundMoves = executa miscarile din runda curenta pentru fiecare
  erou, daca acesta nu este incapacitat (in acest ultim caz, scade din durata
  de incapacitare)
  - applyOverTimeDamage = aplica damage-ul de la abilitatile overtime, daca este
  cazul, scazandu-le totodata din durata de damage
  - searchForFights = cauta posibile lupte ce ar putea avea loc pe harta pentru
  asezarea curenta a eroilor
  - manageFight = se ocupa explicit de lupta dintre doi eroi
  - roundEnding = executa o rutina specifica eroilor la final de runda

  Clasa abstracta GameCharacter reprezinta punctul de plecare pentru implemen-
  tarea claselor specifice fiecarui tip de erou (Knight, Rogue, Pyromancer
  si Wizard). Majoritatea aspectelor sunt comune fiecaruia din cele enumerate
  mai sus, cum ar fi:
  - viata curenta
  - nivelul de experienta curenta
  - nivelul curent
  - randul si linia pe care se afla
  - o anume abilitate overtime care il afecteaza pe erou
  - aplicarea unei miscari asupra unui erou
  - primirea de damage
      si alte caracteristica aplicabile oricarui tip de erou

  Aspectele caracteristice fiecarui tip de erou se leaga de:
  - viata maxima
  - atacul contra unei anumite clase (4 cazuri pentru fiecare tip)
  - atacul cu o abilitate overtime contra unei anumite clase de eroi (4 cazuri)
  - functiile computeInitialDamage si computeInitialOvertimeDamage ca punct de
  pornire pentru calcularea damage-ului corespunzator fiecarui tip de abilitate
  - functiile getAffectedBy si getAttackedBy ca punct central in aplicarea con-
  ceptului de Double Dispatch
  (functiile legate de calcularea damage-ului dat vor fi detaliate in cele
  ce urmeaza)


Unde si cum am folosit Double Dispatch:
  Conceptul de Double Dispatch si-a avut locul logic in ceea ce reprezinta
  lupta dintre doi eroi. Astfel, el se regaseste in fiecare din clasele
  Knight, Pyromancer, Wizard si Rogue, in functiile attack (4 cazuri),
  affectOvertime (4 cazuri), getAffectedBy si getAttackedBy.

  Astfel, lupta dintre doi eroi incepe de la functia getAttackedBy si este
  continuata de functia getAffectedBy (pentru abilitatile overtime).

  Din cadrul fiecarei functii getAttackedBy si getAffectedBy se apeleaza
  functia attack si, respectiv, affectOvertime a inamicului, aici
  terminandu-se apelurile care sa "desfaca" tipurile de eroi care se lupta.

  Functia attack are 4 variante overloaded in clasa fiecarui tip de erou
  pentru fiecare tip de inamic posibil. Aceasta functie calculeaza damage-ul
  total dat adversarului, inclusiv cel de la abilitatea overtime, dar pe
  care nu o aplica dusmanului. Are si un punct de pornire care calculeaza da-
  mage-ul in functie de level si de tipul de locatie (computeInitialDamage),
  iar in functiile attack se adauga bonusurile de rasa.

  Functia affectOvertime are ca punct de pornire functia
  computeInitialOvertimeDamage si 4 variante overloaded ale sale. Se comporta
  in aceeasi maniera ca sora ei mai mare (functia attack), doar ca cea curenta
  aplica abilitatea overtime cu toate atributiile ei (durata de damage,
  durata de incapacitare, capacitate de incapacitare, damage) inamicului.

  In concluzie, conceptul de Double Dispatch a fost folosit pentru a clarifica
  lupta dintre doi eroi si a face implementarea acesteia mai usor de urmatit.


Observatii:
  - In clasa Wizard, functiile care se ocupa de aplicarea unei abilitati cu
  efect prelungit contra Rogue, Pyromancer ori Knight au rolul de a calcula
  damage-ul de la Deflect. Chit ca aceasta nu este o abilitate overtime, m-am
  gandit sa ma folosesc de structura prezenta in celelalte clase de acelasi fel
  si sa incerc sa pastrez o uniformitate a codului.

  - Exista si anumite functii care indeplinesc roluri adiacente, in functie de
  fiecare tip de erou, cum ar fi functia checkExecutePossibility (care verifica
  posibilitatea aplicarii unui execute) din clasa Knight sau functia
  hasAppliedBackstab din clasa Rogue (care memoreaza daca in runda care a
  trecut eroul in cauza a aplicat vreun backstab).

  - In clasele Wizard, Rogue, Knight si Pyromancer, in fiecare din functiile
  attack sau affectOvertime este un anumit numar de linii de cod (aprox. 10)
  care se repeta in functiile care au acelasi rol. Am incercat sa creez functii
  care sa elimine aceasta duplicare, dar si acelea trebuiau sa tine cont de ti-
  pul adversarului si ajungeam intr-un final sa creez inca un rand de functii
  care ar fi avut acelasi bloc de cod.



--------------------------------------------------------------------------------
                                  Etapa II
--------------------------------------------------------------------------------

  Datorita structurii incepute la prima etapa, nu au fost necesare multe modifi-
  cari pentru a indeplini cerintele din a doua etapa. Astfel, am adaugat trei
  mari pachete: angels, factories si strategies care sa contina clasele
  corespondente, iar clasele singulare, cum ar fi GreatSorcerer, Map,
  AngelFactory sau interfetele Visitor/Visitable le-am impartit in pachetele
  deja existente.

  Pachetele temei in forma finala sunt sunt:
    - angels: contine clasa abstracta Angel si fiecare din clasele copil care o
    extind, cate una pentru fiecare tip de inger din program
    - data: contine enum-uri, clasa pentru constante, template-ul pentru o abi-
    litate overtime si interfete folosite la interactiunea cu ingerii (Visitor
    si Visitable)
    - factories: contine cele doua factory-uri ale programului: HeroFactory si
    AngelFactory
    - gameplay: contine clase care au legatura cu desfasurarea jocului, cum ar
    fi: clasa principala Game, clasa care simbolizeaza Marele Magician
    (GreatSorcerer), clasa care citeste inputul si scrie rezultatul final al
    jocului (IOAssistant) sau clasa care simbolizeaza harta jocului (Map)
    - heroes: contine clasa abstracta Hero si cele 4 extinderi ale sale:
    Wizard, Rogue, Pyromancer si Knight
    - strategies: contine clasa abstracta Strategy si cele 8 clase care o
    extind: 4 pentru numarul de tipuri de eroi si 2 pentru fiecare tip de erou

  Factory:
    Am creat cate un factory pentru crearea eroilor si a ingerilor. Am decis sa
    nu le fac Singleton pentru ca deja in cod erau mai multe clase care se folo-
    seau de acest concept, plus ca nu era tocmai necesar, in abordarea mea.

  Visitor/Visitable:
    Am folosit acest concept in interactiunea dintre ingeri si eroi. Am creat
    de asemenea si cate o interfata pentru fiecare din cele doua roluri.

  Observer:
    Acest concept este utilizat cu ajutorul clasei GreatSorcerer, acesta fiind
    observatorul. Subiectii sunt ingerii si eroii din joc. In ambele cazuri,
    observatorul este setat in constructor, el fiind mereu acelasi.

  Singleton:
    Am considerat utilizarea Singleton in cazul claselor GreatSorcerer, Map si
    Game.

  Strategy:
    Am considerat crearea a 8 clase corespunzatoare numarului de strategi care
    pot fi adoptate in joc, de orice tip de erou. De asemenea, am creat o clasa
    abstracta Strategy, care sa serveasca rol de template.

  Double Dispatch:
    In completarea folosirii acestui concept la prima etapa, am mai folosit
    Double Dispatch si in cadrul interactiunii dintre ingeri si eroi. Abor-
    darea este foarte similara cu cea existenta deja, singura diferenta fiind
    cel care aplica actiunea. Nu mai este inamicul eroului din perspectiva
    caruia privim interactiunea, ci este un inger care il influenteaza din
    orice punct de vedere.

  Interactiunea cu ingerii:
    Am adaugat metode suplimentare in clasa Hero care sa se ocupe de interactiu-
    nea dintre un erou si un inger, cum ar fi: increaseHp -LifeGiver-,
    increaseXp -XPAngel- sau revive -Spawner.

  Observatii:
    - am utilizat clasele deprecate Observable si Observer din java.lang pentru
    ca nu am considerat necesare crearea altor interfete/clase pe care sa le
    implementez, avand in vedere ca acestea exista deja si sunt usor de folosit.
    De asemenea, folosirea unor clase deprecate nu am considerat ca poate fi o
    depunctate, avand in vedere ca au fost utilizate si la laborator.
    - am redenumit clasa GameCharacter -> Hero
    - am mutat anumite clase de dinainte in alte pachete, pentru o organizare
    mai logica

  In urma feedback-ului de la prima etapa:
    - am eliminat commentariile C-like ('//') care descriau unele functii
    inainte, pastrand pe cele de acest fel doar pentru corpul functiilor.
    Am utilizat pentru functii doar comentarii de genul /* []...] */ sau
    /** [doc] */.
    - am eliminat apelurile de getteri si setteri din clasele unde erau redun-
    dante
    - am eliminat functiile care nu aveau utilizare
    - am eliminat parametrii din functii care nu erau folositi
    - am adaugat spatii in cod, pentru a delimita zonele care au roluri diferite
