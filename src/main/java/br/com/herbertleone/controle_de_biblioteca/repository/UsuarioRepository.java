package br.com.herbertleone.controle_de_biblioteca.repository;

import br.com.herbertleone.controle_de_biblioteca.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    Usuario findByNome(String nome);
}
