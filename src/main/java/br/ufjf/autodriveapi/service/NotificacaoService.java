package br.ufjf.autodriveapi.service;

import br.ufjf.autodriveapi.model.repository.NotificacaoRepository;
import br.ufjf.autodriveapi.model.entity.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class NotificacaoService {
    private NotificacaoRepository repository;

    public NotificacaoService(NotificacaoRepository notificacao) {
        this.repository = notificacao;
    }

    public List<Notificacao> getNotificacao() {
        return repository.findAll();
    }

    public Optional<Notificacao> getNotificacaoById(Long id) {
        return repository.findById(id);
    }

    @Transactional
    public Notificacao salvar(Notificacao notificacao) {
        validar(notificacao);
        return repository.save(notificacao);
    }

    @Transactional
    public void excluir(Notificacao notificacao) {
        Objects.requireNonNull(notificacao.getId());
        repository.delete(notificacao);
    }

    public void validar(Notificacao notificacao) {}
}
