package uts.poppyambarita.tasks;

import java.util.ArrayList;
import java.util.List;

public class AgendaRepository {
    private static AgendaRepository instance;
    private List<Agenda> agendas;

    private AgendaRepository() {
        agendas = new ArrayList<>();
    }

    public static AgendaRepository getInstance() {
        if (instance == null) {
            instance = new AgendaRepository();
        }
        return instance;
    }

    public void addAgenda(Agenda agenda) {
        agendas.add(agenda);
    }

    public List<Agenda> getAllAgendas() {
        return agendas;
    }
}
