package br.com.herbertleone.controle_de_biblioteca.model;

import br.com.herbertleone.controle_de_biblioteca.builder.EmprestimoBuilder;
import br.com.herbertleone.controle_de_biblioteca.builder.LivroBuilder;
import br.com.herbertleone.controle_de_biblioteca.builder.UsuarioBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

class EmprestimoTest {

    @Test
    void deveRealizarEmprestimoDeUmLivroNaoReservado(){
        Livro livro = LivroBuilder.umLivro().semReserva().constroi();
        Emprestimo emprestimo = EmprestimoBuilder.umEmprestimo().constroi();

        Assertions.assertDoesNotThrow(() -> emprestimo.setLivro(livro));
    }

    @Test
    void naoDeveRealizarEmprestimoDeUmLivroReservado(){
        Livro livro = LivroBuilder.umLivro().reservado().constroi();
        Emprestimo emprestimo = EmprestimoBuilder.umEmprestimo().constroi();

        Assertions.assertThrows( IllegalArgumentException.class,
                () -> emprestimo.setLivro(livro),
                "Não pode realizar empréstimo de um livro já reservado" );
    }

    @Test
    void deveGarantirQueDataPrevistaEstejaCorretaAposLocacaoDoLivro(){
        Emprestimo emprestimo = EmprestimoBuilder.umEmprestimo().constroi();

        LocalDate dataDeEmprestimo = LocalDate.of(2020, 10, 23);
        LocalDate dataDeDevolucaoPrevista = LocalDate.of(2020,10,30);

        Assertions.assertEquals(emprestimo.getDataDeDevolucaoPrevista(), dataDeDevolucaoPrevista);

    }

    @Test
    void deveCriarEmprestimoComUsuarioSemEmprestimos(){
        Usuario usuario = UsuarioBuilder.umUsuario().constroi();
        Emprestimo emprestimo = EmprestimoBuilder.umEmprestimo().constroi();

        Assertions.assertDoesNotThrow(() -> emprestimo.setUsuario(usuario));
    }

    @Test
    void deveCriarEmprestimoComUsuarioComUmEmprestimo(){
        Usuario usuario = UsuarioBuilder.umUsuario().comUmEmprestimo().constroi();
        Emprestimo emprestimo = EmprestimoBuilder.umEmprestimo().constroi();

        Assertions.assertDoesNotThrow(() -> emprestimo.setUsuario(usuario));
    }

    @Test //Nesse caso não deveria criar o empréstimo, mas mudei o contexto por causa da atividade...
    void deveCriarEmprestimoComUsuarioComDoisEmprestimos(){
        Usuario usuario = UsuarioBuilder.umUsuario().comDoisEmprestimos().constroi();
        Emprestimo emprestimo = EmprestimoBuilder.umEmprestimo().constroi();

        Assertions.assertDoesNotThrow(() -> emprestimo.setUsuario(usuario));
    }

    @Test //Não entendi o motivo desse teste...
    void naoDeveRealizarTerceiroEmprestimoParaUsuarioComDoisEmprestimos(){
        Usuario usuario = UsuarioBuilder.umUsuario().comDoisEmprestimos().constroi();
        Emprestimo emprestimo = EmprestimoBuilder.umEmprestimo().constroi();

        Assertions.assertThrows( IllegalArgumentException.class,
                () -> emprestimo.setUsuario(usuario),
                "Não pode realizar empréstimo para um usuário com dois livros já reservados" );
    }

    @Test
    void deveRealizarDevolucaoAntesDaDataPrevista(){
        Usuario usuario = UsuarioBuilder.umUsuario().comUmEmprestimo().constroi();
        Emprestimo emprestimo = EmprestimoBuilder.umEmprestimo().constroi();

        LocalDate dataDeEmprestimo = LocalDate.of(2020, 10, 23);
        LocalDate dataDeDevolucao = LocalDate.of(2020,10,25);
        emprestimo.setDataDeDevolucao(dataDeDevolucao);

        Assertions.assertEquals(new BigDecimal(5), emprestimo.getValorDoAluguel());
    }

    @Test
    void deveRealizarDevolucaoNaDataPrevista(){
        Usuario usuario = UsuarioBuilder.umUsuario().comUmEmprestimo().constroi();
        Emprestimo emprestimo = EmprestimoBuilder.umEmprestimo().constroi();

        LocalDate dataDeEmprestimo = LocalDate.of(2020, 10, 23);
        LocalDate dataDeDevolucaoPrevista = LocalDate.of(2020,10,30);
        emprestimo.setDataDeDevolucao(dataDeDevolucaoPrevista);

        Assertions.assertEquals(5, emprestimo.getValorDoAluguel().doubleValue(), 0.001);
    }

    @Test
    void deveRealizarDevolucaoUmDiaAposADataPrevistaComMulta(){
        Usuario usuario = UsuarioBuilder.umUsuario().comUmEmprestimo().constroi();
        Emprestimo emprestimo = EmprestimoBuilder.umEmprestimo().constroi();

        LocalDate dataDeEmprestimo = LocalDate.of(2020, 10, 23);
        LocalDate dataDeDevolucaoPrevista = LocalDate.of(2020,10,31);
        emprestimo.setDataDeDevolucao(dataDeDevolucaoPrevista);

        Assertions.assertEquals(5.40, emprestimo.getValorDoAluguel().doubleValue(), 0.001);
    }

    @Test
    void deveRealizarDevolucaoTrintaDiasAposADataPrevistaComMulta(){
        Usuario usuario = UsuarioBuilder.umUsuario().comUmEmprestimo().constroi();
        Emprestimo emprestimo = EmprestimoBuilder.umEmprestimo().constroi();

        LocalDate dataDeDevolucaoPrevista = LocalDate.of(2020, 10, 23);
        LocalDate dataDeDevolucao = LocalDate.of(2020,11,22);
        emprestimo.setDataDeDevolucaoPrevista(dataDeDevolucaoPrevista);
        emprestimo.setDataDeDevolucao(dataDeDevolucao);

        Assertions.assertEquals(8.00, emprestimo.getValorDoAluguel().doubleValue(), 0.001);
    }
}
