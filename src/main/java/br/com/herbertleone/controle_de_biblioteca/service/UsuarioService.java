package br.com.herbertleone.controle_de_biblioteca.service;
import br.com.herbertleone.controle_de_biblioteca.model.Usuario;
import br.com.herbertleone.controle_de_biblioteca.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;

    private final GenericService<Usuario> genericoService;

    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
        this.genericoService = new GenericService<Usuario>(usuarioRepository);
    }

    @Transactional
    public Usuario salva(Usuario usuario ) {
        return this.genericoService.salva(usuario);
    }

    @Transactional(readOnly = true)
    public Usuario buscaPor(Integer id) {
        return this.genericoService.buscaPor(id );
    }

    @Transactional(readOnly = true)
    public Optional<Usuario> buscaPor(String nome) {
        return Optional.ofNullable( usuarioRepository.findByNome(nome ) );
    }

    @Transactional(readOnly = true)
    public List<Usuario> todos() {
        return genericoService.todos();
    }

    @Transactional
    public Usuario atualiza(Usuario usuario, Integer id) {
        return this.genericoService.atualiza(usuario, id);
    }

    @Transactional
    public void excluir(Integer id) {
        this.genericoService.excluirPor(id );
    }
}