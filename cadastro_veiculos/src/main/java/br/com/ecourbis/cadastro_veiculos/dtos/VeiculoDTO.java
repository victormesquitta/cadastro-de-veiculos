package br.com.ecourbis.cadastro_veiculos.dtos;

import br.com.ecourbis.cadastro_veiculos.enums.TipoStatus;
import br.com.ecourbis.cadastro_veiculos.enums.TipoUnidade;
import br.com.ecourbis.cadastro_veiculos.enums.TipoVeiculo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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

    // permite apenas a leitura do atributo
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long codVeiculo;

    @NotBlank(message = "A placa não pode estar em branco.")
    @Pattern(regexp = "[A-Z]{3}\\d{3}", message = "A placa deve seguir o formato AAA123.")
    private String placa;

    @NotBlank(message = "A marca não pode estar em branco.")
    @Size(max = 100, message = "A marca deve ter no máximo 100 caracteres.")
    private String marca;

    @NotBlank(message = "O modelo não pode estar em branco.")
    @Size(max = 100, message = "O modelo deve ter no máximo 100 caracteres.")
    private String modelo;

    @Enumerated(EnumType.STRING)
    private TipoVeiculo tipo;

    @Enumerated(EnumType.STRING)
    private TipoUnidade unidade;

    @NotBlank(message = "O RENAVAM não pode estar em branco.")
    @Pattern(regexp = "\\d{11}", message = "O RENAVAM deve conter 11 dígitos numéricos.")
    private String renavam;

    @JsonIgnore
    @Enumerated(EnumType.STRING)
    private TipoStatus status;

    @Past(message = "A data de cadastro deve estar no passado.")
    private LocalDate dtCadastro;
}
