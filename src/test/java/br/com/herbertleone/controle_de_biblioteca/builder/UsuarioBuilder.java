package br.com.herbertleone.controle_de_biblioteca.builder;

import br.com.herbertleone.controle_de_biblioteca.model.Emprestimo;
import br.com.herbertleone.controle_de_biblioteca.model.Usuario;
import java.util.LinkedHashSet;
import java.util.Set;

public class UsuarioBuilder {
    private Usuario usuario;
    private UsuarioBuilder(){

    }

    public static UsuarioBuilder umUsuario(){
        UsuarioBuilder builder = new UsuarioBuilder();
        builder.usuario = new Usuario();
        builder.usuario.setNome("Herbert");
        builder.usuario.setMatricula("20171SI0170");

        return builder;
    }

    public UsuarioBuilder comNome(String nome){
        this.usuario.setNome(nome);
        return this;
    }

    public UsuarioBuilder comMatricula(String matricula){
        this.usuario.setMatricula(matricula);
        return this;
    }

    public UsuarioBuilder comUmEmprestimo(){
        Set<Emprestimo> emprestimos = new LinkedHashSet<>();
        Emprestimo emprestimo = new Emprestimo();
        emprestimos.add(emprestimo);
        this.usuario.setEmprestimos(emprestimos);
        return this;
    }

    public UsuarioBuilder comDoisEmprestimos(){
        Set<Emprestimo> emprestimos = new LinkedHashSet<>();
        Emprestimo emprestimo1 = new Emprestimo();
        Emprestimo emprestimo2 = new Emprestimo();
        emprestimos.add(emprestimo1);
        emprestimos.add(emprestimo2);
        this.usuario.setEmprestimos(emprestimos);
        return this;
    }


    public Usuario constroi(){
        return this.usuario;
    }
}
