package br.com.ecourbis.cadastro_veiculos.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum TipoUnidade {
    SUL, LESTE, CTL, TSA, ETV, CMT, UTRSS;

    /*
     *  Método estático que valida valores passados nos JSONs que não correspondam
     *  a nenhum dos presentes no ENUM.
     *
     *  Mais detalhes da implementação:
     *  https://cursos.alura.com.br/forum/topico-validar-enum-290990
     */
    @JsonCreator
    public static TipoUnidade fromString(String value) {
        for (TipoUnidade tipoUnidade : TipoUnidade.values()) {
            if (tipoUnidade.name().equalsIgnoreCase(value)) {
                return tipoUnidade;
            }
        }
        throw new IllegalArgumentException("Unidade inválida - " + value);
    }

    public static boolean isValid(String unidade) {
        for (TipoUnidade unidadeEnum : TipoUnidade.values()) {
            if (unidadeEnum.name().equals(unidade)) {
                return true;
            }
        }
        return false;
    }
}
