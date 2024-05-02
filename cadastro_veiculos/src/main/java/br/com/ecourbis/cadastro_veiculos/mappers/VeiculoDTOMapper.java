package br.com.ecourbis.cadastro_veiculos.mappers;

import br.com.ecourbis.cadastro_veiculos.dtos.VeiculoDTO;
import br.com.ecourbis.cadastro_veiculos.models.Veiculo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class VeiculoDTOMapper {

    @Autowired
    private final ModelMapper modelMapper;

    public VeiculoDTOMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public Veiculo toEntity(VeiculoDTO veiculoDTO){
        return modelMapper.map(veiculoDTO, Veiculo.class);
    }

    public Veiculo toEntity(VeiculoDTO veiculoDTO, Integer id) {
        Veiculo veiculo = modelMapper.map(veiculoDTO, Veiculo.class);
        veiculo.setCodVeiculo(id);
        return veiculo;
    }


    public VeiculoDTO toDTO(Veiculo veiculo) {
        return modelMapper.map(veiculo, VeiculoDTO.class);
    }


}
