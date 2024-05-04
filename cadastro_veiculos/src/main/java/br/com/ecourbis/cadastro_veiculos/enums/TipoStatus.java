package br.com.ecourbis.cadastro_veiculos.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum TipoStatus {
    ATIVO, DESATIVADO;


    /*
    *  Método estático que valida valores passados nos JSONs que não correspondam
    *  a nenhum dos presentes no ENUM.
    *
    *  Mais detalhes da implementação:
    *  https://cursos.alura.com.br/forum/topico-validar-enum-290990
    */
    @JsonCreator
    public static TipoStatus fromString(String value) {
        for (TipoStatus tipoStatus : TipoStatus.values()) {
            if (tipoStatus.name().equalsIgnoreCase(value)) {
                return tipoStatus;
            }
        }
        throw new IllegalArgumentException("Status inválido - " + value);
    }
    public static boolean isValid(String status) {
        for (TipoStatus unidadeEnum : TipoStatus.values()) {
            if (unidadeEnum.name().equals(status)) {
                return true;
            }
        }
        return false;
    }
}
