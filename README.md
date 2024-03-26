# Ospedale San Duziano
## Indice 
1. [Introduzione](#1-introduzione) <br>
    1.1 [Descrizione caso di studio](#11-caso-di-studio) <br>

2. [Diagramma delle classi](#2-diagramma-delle-classi) <br>
    2.1 [Diagramma delle classi completo](#21-diagramma-delle-classi-completo) <br>
    2.2 [Avvio del gioco](#22-avvio-del-gioco) <br>
3. [Specifica algebrica](#3-specifica-algebrica)
4. [Argomenti del corso](#4-argomenti-del-corso) <br>
    4.1 [Collection](#42-collection) <br>
    4.2 [Eccezioni](#43-eccezioni) <br>
    4.2 [File](#44-file) <br>
    4.3 [Database](#45-database) <br>
        4.3.1 [Salvataggio di una partita](#451-salvataggio-di-una-partita) <br>
         4.3.2 [Caricamento di una partita esistente](#452-caricamento-di-una-partita-esistente) <br>
    4.4 [Thread](#46-thread) <br>
    4.5 [API RESTful](#47-socket) <br>
    4.6 [Swing](#48-swing) <br>
    4.7 [Lambda Expressions](#49-lambda-expressions) <br>

<br>

# **1. Introduzione**
## **1.1 Descrizione caso di studio**
Il caso di studio presentato e descritto all'interno di questo progetto prende il nome di : "Ospedale San Duziano" proprio in riferimento a dove l'avventura testuale avrà effettivamente luogo.<br> 
La trama del gioco si sviluppa attraverso una serie di situazioni comiche, incontri bizzarri e ostacoli da superare durante la ricerca dell'ingrediente segreto.

L'obbiettivo dell'utente sarà quello di esplorare il luogo, raccogliere indizi e informazioni utili, risolvere enigmi e superare sfide per avanzare nella trama e portare a termine con successo la sua missione. Pertanto ogni stanza potrebbe nascondere segreti e risorse utili per il progresso di quest'ultimo, che dovrà esplorare attentamente e interagire con gli oggetti e i personaggi presenti.


## **1.2 Funzionalità implementate**
Numerose sono le funzionalità che presenta questa avventura testuale, tra queste, sicuramente una delle scelte implementative più significative è stata quella di rendere l'esperienza più coinvolgente introducendo una rappresentazione grafica del gioco. Offrendo al giocatore una rappresentazione visiva dell'ambientazione e delle situazioni che incontra lungo il percorso, coinvolgendo maggiormente l'utente. <br>
- **Grafica**: L'aggiunta di una grafica suddivisa in pannelli, in base allo stato attuale del gioco, ha reso l'interfaccia più intuitiva e facile da comprendere. Ogni pannello corrisponde a una determinata situazione o ambiente all'interno del gioco, consentendo al giocatore di visualizzare in modo chiaro e immediato le opzioni disponibili e il contesto in cui si trova. <br><br>
- **Salvataggio**: Un'altra significativa funzionalità, è la possibilità di salvare la partita in qualsiasi momento tramite il comando "salva", permettendo ai giocatori, tramite il nickname di questi ultimi, di riprendere l'avventura dal punto esatto in cui l'hanno lasciata in un secondo momento.<br><br>
- **Varie meccaniche di gioco**: Per rendere l'esperienza di gioco più fluida e intuitiva, il sistema di interpretazione dei comandi è progettato per accettare anche termini simili e sinonimi, consentendo ai giocatori di esprimersi in modo naturale e ottenere risposte coerenti e significative dall'ambiente di gioco.
Tra i comandi principali figura sicuramente "osserva", che permette di analizzare dettagliatamente l'ambiente, e "raccogli" insieme ad altri comandi, che consentono di interagire con gli oggetti e raccoglierli per l'uso futuro.

    <br> 
    Inoltre, i giocatori hanno la possibilità di interagire con personaggi non giocanti (NPC) presenti nell'ospedale, scambiando battute, chiedendo informazioni o ottenendo suggerimenti utili per proseguire nell'avventura.

    <br>

    In conclusione, la combinazione di queste scelte implementative ha reso possibile la creazione di una avventura testuale dinamica, aggiungendo un tocco di modernità rispetto alle tradizionali avventure testuali.
    
    




