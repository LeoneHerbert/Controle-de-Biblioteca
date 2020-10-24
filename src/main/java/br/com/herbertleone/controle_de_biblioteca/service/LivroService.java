package br.com.herbertleone.controle_de_biblioteca.service;

import br.com.herbertleone.controle_de_biblioteca.model.Livro;
import br.com.herbertleone.controle_de_biblioteca.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class LivroService {
    private final LivroRepository livroRepository;

    private final GenericService<Livro> genericoService;

    @Autowired
    public LivroService(LivroRepository livroRepository) {
        this.livroRepository = livroRepository;
        this.genericoService = new GenericService<Livro>(livroRepository);
    }

    @Transactional
    public Livro salva(Livro livro ) {
        return this.genericoService.salva(livro);
    }

    @Transactional(readOnly = true)
    public Livro buscaPor(Integer id) {
        return this.genericoService.buscaPor(id );
    }

    @Transactional(readOnly = true)
    public Optional<Livro> buscaPor(String nome) {
        return Optional.ofNullable( livroRepository.findByNome(nome ) );
    }

    @Transactional(readOnly = true)
    public List<Livro> todos() {
        return genericoService.todos();
    }

    @Transactional
    public Livro atualiza(Livro livro, Integer id) {
        return this.genericoService.atualiza(livro, id);
    }

    @Transactional
    public void excluir(Integer id) {
        this.genericoService.excluirPor(id );
    }
}
