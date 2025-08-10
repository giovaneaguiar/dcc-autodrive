package br.ufjf.autodriveapi.service;

import br.ufjf.autodriveapi.exception.RegraNegocioException;
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
// titulo, descricao, valor
    public void validar(Notificacao notificacao) {
        if (notificacao.getTitulo() == null || notificacao.getTitulo().trim().isEmpty()) {
            throw new RegraNegocioException("O título da notificação é obrigatória.");
        }

        if (notificacao.getDescricao() == null || notificacao.getDescricao().trim().isEmpty()) {
            throw new RegraNegocioException("A descrição da notificação é obrigatória.");
        }

        if (notificacao.getValor() == null || notificacao.getValor() <= 0) {
            throw new RegraNegocioException("O valor do favorito é obrigatório e deve ser maior que zero.");
        }

        if (notificacao.getDataCriacao() == null) {
            throw new RegraNegocioException("A data de criação é obrigatória.");
        }

        if (notificacao.getUsuario() == null || notificacao.getUsuario().getId() == null) {
            throw new RegraNegocioException("A notificação deve estar associado a um usuário.");
        }

    }
}
