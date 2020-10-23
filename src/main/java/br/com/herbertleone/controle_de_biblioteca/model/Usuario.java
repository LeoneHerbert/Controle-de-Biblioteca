package br.com.herbertleone.controle_de_biblioteca.model;

import lombok.Data;
import javax.validation.constraints.Size;
import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Data
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String nome;

    @Column
    private String matricula;

    @Size(max=2)
    private Set<Emprestimo> emprestimos = new LinkedHashSet<>();
}
