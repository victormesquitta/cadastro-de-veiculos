package br.com.ecourbis.cadastro_veiculos.repositories;

import br.com.ecourbis.cadastro_veiculos.enums.TipoStatus;
import br.com.ecourbis.cadastro_veiculos.enums.TipoUnidade;
import br.com.ecourbis.cadastro_veiculos.models.Veiculo;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface VeiculoRepositorio extends JpaRepository<Veiculo, Integer> {
    // pesquisa os veículos pelo status
    public Integer countByStatus(TipoStatus status);

    // pesquisa os veículos pela unidade (região)
    public Integer countByUnidade(TipoUnidade unidade);

    // lista veiculos ativos
    public List<Veiculo> findByStatus(TipoStatus status, Pageable pageable);


    /*
    *  findBy: Indica que estamos criando uma consulta de busca.
    *  Status: Nome do campo que queremos filtrar.
    *  And: Indica que estamos adicionando uma condição AND à consulta.
    *  Placa, Marca, Modelo: São os nomes dos campos adicionais que queremos incluir na consulta.
    *  Or: Indica que estamos adicionando uma condição OR à consulta.
    *  Containing: Indica que queremos fazer uma pesquisa que verifica se o campo contém um determinado valor.
    *  IgnoreCase: Indica que a comparação será feita sem diferenciar maiúsculas de minúsculas.
    *
    * */


    @Query("FROM Veiculo v " +
            "WHERE v.status = :status " +
            "AND (" +
            "UPPER(v.placa) LIKE CONCAT('%', UPPER(:pesquisa), '%') " +
            "OR UPPER(v.marca) LIKE CONCAT('%', UPPER(:pesquisa), '%') " +
            "OR UPPER(v.modelo) LIKE CONCAT('%', UPPER(:pesquisa), '%') " +
            "OR UPPER(v.tipo) LIKE CONCAT('%', UPPER(:pesquisa), '%') " +
            "OR UPPER(v.unidade) LIKE CONCAT('%', UPPER(:pesquisa), '%') " +
            "OR UPPER(v.renavam) LIKE CONCAT('%', UPPER(:pesquisa), '%')" +
            ")")
    List<Veiculo> pesquisarVeiculos(
            @Param("status") TipoStatus status,
            @Param("pesquisa") String pesquisa,
            Pageable pageable);
}
