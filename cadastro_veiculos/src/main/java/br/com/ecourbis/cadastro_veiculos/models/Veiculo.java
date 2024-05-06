package br.com.ecourbis.cadastro_veiculos.models;

import br.com.ecourbis.cadastro_veiculos.enums.TipoStatus;
import br.com.ecourbis.cadastro_veiculos.enums.TipoUnidade;
import br.com.ecourbis.cadastro_veiculos.enums.TipoVeiculo;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Table(name="t_veiculo", uniqueConstraints = {
        @UniqueConstraint(columnNames = "placa", name = "uk_placa_veiculo"),
        @UniqueConstraint(columnNames = "renavam", name = "uk_renavam_veiculo")
})
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Veiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="codveiculo")
    private Integer codVeiculo;

    @Column(name="placa", nullable = false, unique = true, length = 6)
    private String placa;

    @Column(name="marca", nullable = false)
    private String marca;

    @Column(name="modelo", nullable = false)
    private String modelo;

    @Enumerated(EnumType.STRING)
    @Column(name="tipo", nullable = false)
    private TipoVeiculo tipo;

    @Enumerated(EnumType.STRING)
    @Column(name="unidade", nullable = false)
    private TipoUnidade unidade;

    @Column(name="renavam", unique = true, length = 11)
    private String renavam;

    @Enumerated(EnumType.STRING)
    @Column(name="status")
    private TipoStatus status;

    @Column(name="dtcadastro")
    private LocalDate dtCadastro;
}
