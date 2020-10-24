package br.com.herbertleone.controle_de_biblioteca.service;

import br.com.herbertleone.controle_de_biblioteca.model.Emprestimo;
import br.com.herbertleone.controle_de_biblioteca.repository.EmprestimoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;


@Service
public class EmprestimoService {
    private final EmprestimoRepository emprestimoRepository;

    private final GenericService<Emprestimo> genericoService;

    @Autowired
    public EmprestimoService(EmprestimoRepository emprestimoRepository) {
        this.emprestimoRepository = emprestimoRepository;
        this.genericoService = new GenericService<Emprestimo>(emprestimoRepository);
    }

    @Transactional
    public Emprestimo salva(Emprestimo emprestimo ) {
        return this.genericoService.salva(emprestimo);
    }

    @Transactional(readOnly = true)
    public Emprestimo buscaPor(Integer id) {
        return this.genericoService.buscaPor(id );
    }

    @Transactional(readOnly = true)
    public List<Emprestimo> todos() {
        return genericoService.todos();
    }

    @Transactional
    public Emprestimo atualiza(Emprestimo emprestimo, Integer id) {
        return this.genericoService.atualiza(emprestimo, id);
    }

    @Transactional
    public void excluir(Integer id) {
        this.genericoService.excluirPor(id );
    }
}
