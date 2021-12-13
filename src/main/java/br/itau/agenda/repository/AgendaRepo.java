package br.itau.agenda.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.itau.agenda.model.Agenda;

@Repository
public interface AgendaRepo extends CrudRepository <Agenda, Integer> {
    public Agenda findByNome(String nome);
}
