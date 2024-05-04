package br.com.ecourbis.cadastro_veiculos.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum TipoVeiculo {
    CCL, CAVALO, FURGAO, MUNCK, VEICULO, LEVE, CARRETA, EQUIPAMENTO;

    /*
     *  Método estático que valida valores passados nos JSONs que não correspondam
     *  a nenhum dos presentes no ENUM.
     *
     *  Mais detalhes da implementação:
     *  https://cursos.alura.com.br/forum/topico-validar-enum-290990
     */
    @JsonCreator
    public static TipoVeiculo fromString(String value) {
        for (TipoVeiculo tipoVeiculo : TipoVeiculo.values()) {
            if (tipoVeiculo.name().equalsIgnoreCase(value)) {
                return tipoVeiculo;
            }
        }
        throw new IllegalArgumentException("Tipo de veículo inválido - " + value);
    }
    public static boolean isValid(String tipo) {
        for (TipoVeiculo tipoVeiculo : TipoVeiculo.values()) {
            if (tipoVeiculo.name().equals(tipo)) {
                return true;
            }
        }
        return false;
    }
}
