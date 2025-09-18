import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ValidadorTest {

    @Test
    void deveValidarCPFComMascara() {
        assertTrue(Validador.validarCPF("529.982.247-25"));
    }

    @Test
    void deveValidarCPFSemMascara() {
        assertTrue(Validador.validarCPF("52998224725"));
    }

    @Test
    void deveAceitarEspacosExternos() {
        assertTrue(Validador.validarCPF("   529.982.247-25   "));
    }

    @Test
    void deveRejeitarNulo() {
        assertFalse(Validador.validarCPF(null));
    }

    @Test
    void deveRejeitarVazio() {
        assertFalse(Validador.validarCPF(""));
    }

    @Test
    void deveRejeitarTamanhoIncorreto() {
        assertFalse(Validador.validarCPF("935.411.347-8"));   // 10 dígitos
        assertFalse(Validador.validarCPF("935.411.347-800")); // 12 dígitos
    }

    @Test
    void deveRejeitarCaracteresInvalidos() {
        assertFalse(Validador.validarCPF("529.982.247-2X"));
    }

    @Test
    void deveRejeitarSequenciasRepetidas() {
        assertFalse(Validador.validarCPF("00000000000"));
        assertFalse(Validador.validarCPF("11111111111"));
    }

    @Test
    void deveRejeitarDVIncorretoMesmoCPF() {
        assertFalse(Validador.validarCPF("529.982.247-24"));
    }

    @Test
    void deveRejeitarDVIncorretoGenerico() {
        assertFalse(Validador.validarCPF("123.456.789-00"));
    }
}
