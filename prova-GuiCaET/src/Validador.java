// Validador.java
public class Validador {
    public static boolean validarCPF(String cpf) {
        if (cpf == null || cpf.trim().isEmpty()) return false;

        // Sanitização: remove espaços, pontos e traços
        String limpo = sanitizar(cpf);

        // Checa formato: deve ter exatamente 11 dígitos
        if (!limpo.matches("\\d{11}")) return false;

        // Rejeita sequências repetidas (ex.: 00000000000)
        if (todosDigitosIguais(limpo)) return false;

        // Calcula e checa dígitos verificadores
        return checarDigitos(limpo);
    }

    /** Remove espaços e caracteres de mscara */
    private static String sanitizar(String cpf) {
        return cpf.trim().replaceAll("[.-]", "");
    }

    /** Retorvnna true se tods os dígitos forem iguais */
    private static boolean todosDigitosIguais(String cpf) {
        return cpf.chars().distinct().count() == 1;
    }

    /** Checa os dígitos verificadores DV1 e DV2 */
    private static boolean checarDigitos(String cpf) {
        int[] digitos = cpf.chars().map(c -> c - '0').toArray();

        // DV1
        int dv1 = calcularDV(digitos, 9, 10);
        if (digitos[9] != dv1) return false;

        // DV2
        int dv2 = calcularDV(digitos, 10, 11);
        return digitos[10] == dv2;
    }
    private static int calcularDV(int[] digitos, int comprimento, int pesoInicial) {
        int soma = 0;
        int peso = pesoInicial;
        for (int i = 0; i < comprimento; i++) {
            soma += digitos[i] * peso--;
        }
        int resto = soma % 11;
        return (resto < 2) ? 0 : 11 - resto;
    }
}
