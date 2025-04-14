package br.insper.iam.evento;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class EventoService {

    @Autowired
    private EventoRepository eventoRepository;

    public List<Evento> getEventos(String acao) {
        if (acao == null) {
            return eventoRepository.findByAcao(acao);
        }
        return eventoRepository.findAll();
    }

    public Evento saveEvento(Evento evento) {
        evento.setDataEvento(LocalDateTime.now());
        return eventoRepository.save(evento);
    }
}
