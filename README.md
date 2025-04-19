# FigureSkatingScorer: Sistema di Punteggio Concorrente Java

![Java](https://img.shields.io/badge/Java-17-orange)
![License](https://img.shields.io/badge/License-MIT-blue)

Un sistema multithreaded che simula l'assegnazione di punteggi in tempo reale nel pattinaggio di figura, implementando meccanismi avanzati di concorrenza in Java. Il progetto modella giudici, atleti e pannello tecnico operanti in parallelo secondo le regole ISU.

## 📋 Panoramica

Questo progetto simula il sistema di punteggio usato nelle competizioni di pattinaggio di figura, dove diversi attori (atleta, giudici, pannello tecnico) operano simultaneamente. L'applicazione utilizza tecniche di programmazione concorrente in Java per modellare questo processo in modo realistico.

## 🌟 Caratteristiche

- **Simulazione multi-attore**: Modella atleti, giudici e pannello tecnico come entità concorrenti
- **Comunicazione thread-safe**: Utilizza `BlockingQueue` per la sincronizzazione tra componenti
- **Punteggio in tempo reale**: Visualizza l'evoluzione del punteggio durante l'esecuzione
- **Valutazione ISU-compatibile**: Implementa una versione semplificata della Rule 353 ISU con valutazione di elementi tecnici e componenti artistici

## 🛠️ Tecnologie Utilizzate

- Java 17
- Programmazione concorrente (Thread, BlockingQueue, AtomicReference)
- Pattern Producer-Consumer e Publish-Subscribe

## 🏗️ Architettura

Il sistema è composto da diversi componenti che interagiscono in modo concorrente:

```
Athlete → Rink → VideoSystem → Screens → Judge/TechnicalPanel → Votes → ScoreBoard → Printer
```

### Componenti principali

- **Athlete**: Esegue un programma composto da elementi tecnici
- **VideoSystem**: Distribuisce gli elementi a giudici e pannello tecnico
- **Judge**: Valuta gli elementi con Grade of Execution (GOE)
- **TechnicalPanel**: Assegna i Base Value agli elementi
- **ScoreBoard**: Raccoglie e somma i voti
- **Printer**: Visualizza lo stato del punteggio in tempo reale

## 🚀 Come eseguire

### Prerequisiti
- Java JDK 17
- Gradle (integrato nel repository)

### Esecuzione
```bash
# Clona il repository
git clone https://github.com/username/FigureSkatingScorer.git
cd FigureSkatingScorer

# Esegui l'applicazione
./gradlew ex1
```

## 📊 Output e interpretazione

L'output del programma mostra l'evoluzione del punteggio nel tempo:

```
_      13,50
__      26,80
__       26,80
___       34,39
...
_____!!!!!___      193,13
Esercizio completato. Punteggio finale: 212.63144
```

### Legenda
- `_` (underscore): Elemento valutato positivamente
- `!` (esclamativo): Elemento in review
- `X`: Elemento valutato negativamente
- ` ` (spazio): Elemento non ancora valutato

## 🧩 Struttura del progetto

```
src/
├── main/
│   └── java/
│       └── it/
│           └── unipd/
│               └── pdp2024/
│                   └── ex1/
│                       ├── Athlete.java        # Atleta che esegue il programma
│                       ├── Element.java        # Elemento del programma
│                       ├── Judge.java          # Giudice che valuta
│                       ├── Main.java           # Punto di ingresso
│                       ├── Nation.java         # Enumerazione nazioni
│                       ├── Printer.java        # Visualizzazione punteggi
│                       ├── ScoreBoard.java     # Calcolo punteggi
│                       ├── TechnicalPanel.java # Pannello tecnico
│                       ├── VideoSystem.java    # Sistema distribuzione elementi
│                       └── Vote.java           # Sistema voti
```

## 🔍 Dettagli implementativi

### Concorrenza e sincronizzazione

Il sistema utilizza diversi meccanismi di concorrenza:

1. **Thread separati** per ogni componente autonomo del sistema
2. **BlockingQueue** per la comunicazione thread-safe tra componenti
3. **AtomicReference** per condividere risultati aggiornati in modo thread-safe

### Gestione delle race condition

Il sistema affronta e risolve diverse potenziali race condition:

1. Controllo di nullità nelle strutture dati condivise
2. Ordine di avvio appropriato dei thread per rispettare le dipendenze
3. Meccanismo di terminazione che aspetta i componenti auto-terminanti e ferma esplicitamente quelli che richiedono interruzione manuale

### Calcolo del punteggio

Il punteggio finale combina:
- **Technical Elements Score**: Base Value + Grade of Execution
- **Components Score**: Valutazioni artistiche (Skating Skills, Transitions, Performance, Composition, Interpretation)

## 📜 Licenza

Questo progetto è rilasciato sotto Licenza MIT. Vedere il file LICENSE per i dettagli.

## 🙏 Ringraziamenti

Questo progetto è stato sviluppato come parte del corso di Paradigmi di Programmazione dell'Università di Padova, ispirato alle regole di punteggio ISU per il pattinaggio di figura.