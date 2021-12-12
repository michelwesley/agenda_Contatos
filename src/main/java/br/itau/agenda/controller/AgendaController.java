package br.itau.agenda.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.itau.agenda.model.Agenda;
import br.itau.agenda.repository.AgendaRepo;

@RestController
@CrossOrigin("*")
@RequestMapping("/agenda")
public class AgendaController {
    
    @Autowired //Injeção de dependência
    private AgendaRepo repo;

    @GetMapping
    public List<Agenda> listarTodos() {
        List<Agenda> lista = (List<Agenda>) repo.findAll(); // Casting

        return lista;
    }

    @GetMapping("/codigo/{codigo}")
    public ResponseEntity<Agenda> buscarAgenda(@PathVariable int codigo) {
        Agenda agenda = repo.findById(codigo).orElse(null);

        if(agenda != null) {
            return ResponseEntity.ok(agenda);
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Agenda> inserir(@RequestBody Agenda novaAgenda) {

        Agenda agenda = repo.save(novaAgenda);

        return ResponseEntity.ok(agenda);

    }

    @DeleteMapping("/{codigo}")
    public ResponseEntity<Void> apagar(@PathVariable int codigo) {
        Agenda agenda = repo.findById(codigo).orElse(null);

        if(agenda != null) {
            repo.delete(agenda);
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }
}
