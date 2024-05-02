package br.com.ecourbis.cadastro_veiculos.dtos;

import br.com.ecourbis.cadastro_veiculos.enums.TipoStatus;
import br.com.ecourbis.cadastro_veiculos.enums.TipoUnidade;
import br.com.ecourbis.cadastro_veiculos.enums.TipoVeiculo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VeiculoDTO {

    @JsonIgnore
    private Long codVeiculo;

    @NotBlank(message = "A placa não pode estar em branco")
    @Pattern(regexp = "[A-Z]{3}-\\d{3}", message = "A placa deve seguir o formato AAA-123")
    private String placa;

    @NotBlank(message = "A marca não pode estar em branco")
    @Size(max = 100, message = "A marca deve ter no máximo 100 caracteres")
    private String marca;

    @NotBlank(message = "O modelo não pode estar em branco")
    @Size(max = 100, message = "O modelo deve ter no máximo 100 caracteres")
    private String modelo;

    private TipoVeiculo tipo;
    private TipoUnidade unidade;

    @NotBlank(message = "O RENAVAM não pode estar em branco")
    @Pattern(regexp = "\\d{11}", message = "O RENAVAM deve conter 11 dígitos numéricos")
    private String renavam;

    @JsonIgnore
    private TipoStatus status;

    @Past(message = "A data de cadastro deve estar no passado")
    private LocalDate dtCadastro;
}
