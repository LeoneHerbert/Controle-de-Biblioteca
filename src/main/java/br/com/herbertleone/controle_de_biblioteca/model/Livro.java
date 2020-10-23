package br.com.herbertleone.controle_de_biblioteca.model;

import lombok.Data;
import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Data
public class Livro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String autor;

    @Column
    private String titulo;

    @Column
    private boolean isEmprestado;

    @Column
    private boolean isReservado;

    @OneToMany
    private Set<Emprestimo> historicoDeEmprestimos = new LinkedHashSet<>();

}
