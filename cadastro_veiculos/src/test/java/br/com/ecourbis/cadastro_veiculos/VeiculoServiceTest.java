package br.com.ecourbis.cadastro_veiculos;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import br.com.ecourbis.cadastro_veiculos.services.VeiculoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class VeiculoServiceTest {

    @Autowired
    private VeiculoService veiculoService;

    @Test
    public void testValidarVeiculo() {
        // Caso de teste: Veículo válido
//        Veiculo veiculoValido = new Veiculo();
//        veiculoValido.setPlaca("ABC1234");
//        veiculoValido.setRenavam("12345678901");
//        assertTrue(veiculoService.validarVeiculo(veiculoValido));
//
//        // Caso de teste: Placa inválida
//        Veiculo veiculoPlacaInvalida = new Veiculo();
//        veiculoPlacaInvalida.setPlaca("123");
//        veiculoPlacaInvalida.setRenavam("12345678901");
//        assertFalse(veiculoService.validarVeiculo(veiculoPlacaInvalida));

        // Adicione mais casos de teste conforme necessário
    }
}
