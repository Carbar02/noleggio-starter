# Epicode Rent — starter per la mattina del giorno 2

Progetto Maven (Java 17, JUnit 5) per il gestionale di noleggio.

```bash
mvn test                                        # 16 test, tutti verdi
mvn -q compile exec:java -Dexec.mainClass=it.epicode.noleggio.app.Main   # oppure Run di Main dall'IDE
```

Attenzione: i test sono verdi, ma l'ufficio ha segnalato dei problemi.
Le segnalazioni sono nelle slide dell'esercitazione: trasformatele in issue,
riproducetele con un test che fallisce, correggetele con una pull request.

Struttura:

```
src/main/java/it/epicode/noleggio/
  model/      Noleggiabile, Ricaricabile, Mezzo, Auto, AutoElettrica,
              Scooter, ScooterElettrico, Furgone, Cliente, Noleggio
  exception/  MezzoNonDisponibileException
  service/    AgenziaNoleggio
  app/        Main
```
