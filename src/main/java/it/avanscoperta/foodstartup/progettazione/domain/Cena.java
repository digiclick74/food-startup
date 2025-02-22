package it.avanscoperta.foodstartup.progettazione.domain;


import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

import java.util.Arrays;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

/**
 * Il nostro aggregato per la cena che progettiamo.
 */
@Aggregate
public class Cena {

    @AggregateIdentifier
    private CenaId cenaId;

    private Cena() {}


    @CommandHandler
    public Cena(ProgettaDaRicetta command) {
        // Guard Conditions?

        apply(new CenaProgettata(
                command.getCenaId(),
                command.getRicetta(),
                command.getCommensali(),
                Arrays.asList(new Piatto(command.getRicetta(), command.getCommensali())),
                command.getRicetta().getTempiDiPreparazione(),
                command.getRicetta().getNome(),
                command.getRicetta().getCalorie()
        ));
    }

    @EventSourcingHandler
    public void handle(CenaProgettata event) {
        // Qui copio lo stato ricevuto e non faccio domande.
        this.cenaId = event.getCenaId();
    }

}
