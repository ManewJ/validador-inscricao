public class ValidadorInscricao {

    public String validar(int idade, String cpf, String categoria, boolean pagamentoConfirmado) {
        if (idade < 18) {
            return "REPROVADO: Idade mínima de 18 anos não atingida.";
        }
        // CPF não pode ser nulo nem vazio
        if (cpf == null || cpf.trim().isEmpty()) {
            return "REPROVADO: CPF obrigatório.";
        }
        if (!pagamentoConfirmado) {
            return "REPROVADO: Pagamento pendente.";
        }
        return "APROVADO: Inscrição confirmada para a categoria " + categoria;
    }
}