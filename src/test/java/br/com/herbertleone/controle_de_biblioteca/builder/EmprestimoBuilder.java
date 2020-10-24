package br.com.herbertleone.controle_de_biblioteca.builder;

import br.com.herbertleone.controle_de_biblioteca.model.Emprestimo;
import br.com.herbertleone.controle_de_biblioteca.model.Livro;
import br.com.herbertleone.controle_de_biblioteca.model.Usuario;

import java.time.LocalDate;

public class EmprestimoBuilder {
    private Emprestimo emprestimo;
    private EmprestimoBuilder(){

    }

    public static EmprestimoBuilder umEmprestimo(){
        LocalDate dataDoEmprestimo = LocalDate.of(2020, 10, 23);
        LocalDate dataDeDevolucao = LocalDate.of(2020, 10, 30);

        EmprestimoBuilder builder = new EmprestimoBuilder();
        builder.emprestimo = new Emprestimo(dataDoEmprestimo);
        builder.emprestimo.setDataDeDevolucao(dataDeDevolucao);

        return builder;
    }

    public EmprestimoBuilder comLivro(Livro livro){
        this.emprestimo.setLivro(livro);
        return this;
    }

    public EmprestimoBuilder comUsuario(Usuario usuario){
        this.emprestimo.setUsuario(usuario);
        return this;
    }

    public EmprestimoBuilder comDataDeEmprestimo(LocalDate dataDeEmprestimo){
        this.emprestimo.setDataDeEmprestimo(dataDeEmprestimo);
        return this;
    }

    public EmprestimoBuilder comDataDeDevolucao(LocalDate dataDeDevolucao){
        this.emprestimo.setDataDeDevolucao(dataDeDevolucao);
        return this;
    }

    public Emprestimo constroi(){
        return this.emprestimo;
    }
}
