package br.com.herbertleone.controle_de_biblioteca.repository;

import br.com.herbertleone.controle_de_biblioteca.model.Emprestimo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmprestimoRepository extends JpaRepository<Emprestimo, Integer> {
}
