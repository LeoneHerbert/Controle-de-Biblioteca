package br.com.herbertleone.controle_de_biblioteca.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;

@Entity
public class Emprestimo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    private Usuario usuario;

    @Column
    private LocalDate dataDeEmprestimo;

    @Column
    private LocalDate dataDeDevolucaoPrevista;

    @Column
    private LocalDate dataDeDevolucao;

    @ManyToOne
    private Livro livro;

    @Column
    private BigDecimal valorDoAluguel;

    public Emprestimo() {
        this.dataDeDevolucaoPrevista = getDataDeDevolucao().plusDays(7);
        this.valorDoAluguel = new BigDecimal('5');
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        if (usuario.getEmprestimos().size() > 2 ) {
            throw new IllegalArgumentException("O usuário só pode ter no máximo 2 empréstimos");
        }
        this.usuario = usuario;
    }

    public LocalDate getDataDeEmprestimo() {
        return dataDeEmprestimo;
    }

    public void setDataDeEmprestimo(LocalDate dataDeEmprestimo) {
        this.dataDeEmprestimo = dataDeEmprestimo;
    }

    public LocalDate getDataDeDevolucao() {
        return dataDeDevolucao;
    }

    public void setDataDeDevolucao(LocalDate dataDeDevolucao) {
        this.dataDeDevolucao = dataDeDevolucao;
    }

    public Livro getLivro() {
        return livro;
    }

    public void setLivro(Livro livro) {
        if (livro.isReservado()) {
            throw new IllegalArgumentException("Não é possível realizar o empréstimo de um livro já reservado");
        }
        this.livro = livro;
    }

    public LocalDate getDataDeDevolucaoPrevista() {
        return dataDeDevolucaoPrevista;
    }

    public BigDecimal getValorDoAluguel(LocalDate dataAtual) {
        Period periodo = Period.between(getDataDeDevolucao(), dataAtual);
        BigDecimal multa = new BigDecimal(0.4);

        if(periodo.getDays() <= 7){
            multa.multiply(new BigDecimal(periodo.getDays()));
        }else{
            multa.multiply(new BigDecimal(7));
        }
        return this.valorDoAluguel.add(multa);
    }
}
