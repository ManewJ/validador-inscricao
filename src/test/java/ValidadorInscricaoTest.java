import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ValidadorInscricaoTest {

    ValidadorInscricao validador = new ValidadorInscricao();

    @Test
    void deveAprovarInscricaoQuandoTudoEstiverCorreto() {
        // Cenário: 25 anos, CPF válido, Categoria 21km, Pagamento OK
        String resultado = validador.validar(25, "123.456.789-00", "21km", true);

        // Se este falhar, significa que o sistema barrou alguém que deveria estar aprovado
        assertTrue(resultado.contains("APROVADO"), "ERRO: A inscrição deveria ter sido APROVADA, mas não foi.");
    }

    @Test
    void deveReprovarInscricaoQuandoForMenorDeIdade() {
        // Cenário: 16 anos (Regra: mínimo 18)
        String resultado = validador.validar(16, "123.456.789-00", "5km", true);

        // Se falhar, o sistema deixou um menor se inscrever
        assertTrue(resultado.contains("REPROVADO"), "ERRO: O sistema deveria ter REPROVADO o menor de idade.");
    }
    @Test
    void deveReprovarQuandoPagamentoNaoForConfirmado() {
        // Cenário: Tudo certo, mas pagamento é FALSE
        String resultado = validador.validar(20, "123.456.789-00", "10km", false);

        // Se falhar, o sistema ignorou a falta de pagamento
        assertTrue(resultado.contains("REPROVADO"), "ERRO: O sistema deveria ter REPROVADO a falta de pagamento.");
    }

    @Test
    void deveReprovarQuandoCpfEstiverVazio() {
        // Cenário: Idade OK, Pagamento OK, mas CPF vazio ("")
        String resultado = validador.validar(30, "", "21km", true);

        // Se falhar, o sistema aceitou um cadastro sem documento
        assertTrue(resultado.contains("REPROVADO"), "ERRO: O sistema deveria ter REPROVADO o CPF vazio.");
    }
}