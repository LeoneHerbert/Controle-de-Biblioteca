package br.com.herbertleone.controle_de_biblioteca.repository;

import br.com.herbertleone.controle_de_biblioteca.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LivroRepository extends JpaRepository<Livro, Integer> {
    Livro findByNome(String nome);
}
