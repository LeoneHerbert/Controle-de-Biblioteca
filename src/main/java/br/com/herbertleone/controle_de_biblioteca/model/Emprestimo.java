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
    }

    public Emprestimo(LocalDate dataDeEmprestimo) {
        this.dataDeEmprestimo = dataDeEmprestimo;
        this.dataDeDevolucaoPrevista = dataDeEmprestimo.plusDays(7);
        this.valorDoAluguel = new BigDecimal(5.0);
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
        if (usuario.getEmprestimos().size() > 2) {
            throw new IllegalArgumentException("O usuário só pode ter no máximo 2 empréstimos");
        }
        this.usuario = usuario;
    }

    public LocalDate getDataDeEmprestimo() {
        return dataDeEmprestimo;
    }

    public void setDataDeEmprestimo(LocalDate dataDeEmprestimo) {
        this.dataDeEmprestimo = dataDeEmprestimo;
        this.setDataDeDevolucaoPrevista(dataDeEmprestimo.plusDays(7));
    }

    public LocalDate getDataDeDevolucao() {
        return dataDeDevolucao;
    }

    public void setDataDeDevolucao(LocalDate dataDeDevolucao) {
        if (dataDeDevolucao.isBefore(getDataDeEmprestimo())) {
            throw new IllegalArgumentException("A data de devolução deve ser após a data do empréstimo");
        }
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

    public void setDataDeDevolucaoPrevista(LocalDate dataDeDevolucaoPrevista) {
        this.dataDeDevolucaoPrevista = dataDeDevolucaoPrevista;
    }

    public BigDecimal getValorDoAluguel() {
        Period periodo = Period.between(getDataDeDevolucaoPrevista(), getDataDeDevolucao());
        BigDecimal multa = new BigDecimal(0.4);

        if(periodo.getDays() > 0 && periodo.getDays() <= 7){
            BigDecimal valorAPagar = multa.multiply(new BigDecimal(periodo.getDays()));
            return this.valorDoAluguel = this.valorDoAluguel.add(valorAPagar);
        } else if (periodo.getDays() > 7) {
            BigDecimal valorAPagar = new BigDecimal(3);
            return this.valorDoAluguel = this.valorDoAluguel.add(valorAPagar);
        } else {
            return this.valorDoAluguel;
        }
    }
}
