package br.com.ecourbis.cadastro_veiculos.repositories;

import br.com.ecourbis.cadastro_veiculos.enums.TipoStatus;
import br.com.ecourbis.cadastro_veiculos.enums.TipoUnidade;
import br.com.ecourbis.cadastro_veiculos.models.Veiculo;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface VeiculoRepositorio extends JpaRepository<Veiculo, Integer> {
    // pesquisa os veículos pelo status
    public Integer countByStatus(TipoStatus status);

    // pesquisa os veículos pela unidade (região)
    public Integer countByUnidade(TipoUnidade unidade);

    // lista veiculos ativos
    public List<Veiculo> findByStatus(TipoStatus status, Pageable pageable);

    @Query("SELECT v FROM Veiculo v WHERE " +
            "UPPER(v.placa) LIKE UPPER(concat('%', :pesquisa, '%')) OR " +
            "UPPER(v.marca) LIKE UPPER(concat('%', :pesquisa, '%')) OR " +
            "UPPER(v.modelo) LIKE UPPER(concat('%', :pesquisa, '%')) OR " +
            "UPPER(v.tipo) LIKE UPPER(concat('%', :pesquisa, '%')) OR " +
            "UPPER(v.unidade) LIKE UPPER(concat('%', :pesquisa, '%')) OR " +
            "UPPER(v.status) LIKE UPPER(concat('%', :pesquisa, '%'))")
    List<Veiculo> pesquisar(@Param("pesquisa") String pesquisa);
}
