package br.com.ecourbis.cadastro_veiculos.repositories;

import br.com.ecourbis.cadastro_veiculos.enums.TipoStatus;
import br.com.ecourbis.cadastro_veiculos.enums.TipoUnidade;
import br.com.ecourbis.cadastro_veiculos.models.Veiculo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VeiculoRepositorio extends JpaRepository<Veiculo, Integer> {
    // pesquisa os veículos pelo status
    public Integer countByStatus(TipoStatus status);

    // pesquisa os veículos pela unidade (região)
    public Integer countByUnidade(TipoUnidade unidade);

    // lista veiculos ativos
    public List<Veiculo> findByStatus(TipoStatus status);
}
