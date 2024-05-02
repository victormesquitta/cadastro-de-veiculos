package br.com.ecourbis.cadastro_veiculos.restcontrollers;

import br.com.ecourbis.cadastro_veiculos.dtos.VeiculoDTO;
import br.com.ecourbis.cadastro_veiculos.services.VeiculoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/veiculos")
public class VeiculoController {

    @Autowired
    private final VeiculoService veiculoService;
    public VeiculoController(VeiculoService veiculoService) {
        this.veiculoService = veiculoService;
    }


    @GetMapping()
    public ResponseEntity<Object> listarTodosVeiculos(){
        List<VeiculoDTO> veiculosDTO = veiculoService.listarTodosVeiculos();
        return ResponseEntity.ok(veiculosDTO);
    }

    @GetMapping("/ativos")
    public ResponseEntity<Object> listarVeiculosAtivos(){
        List<VeiculoDTO> veiculosDTO = veiculoService.listarVeiculosAtivos();
        return ResponseEntity.ok(veiculosDTO);
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<Object> obterVeiculoPeloID(@PathVariable Integer id){
        VeiculoDTO veiculoDTO = veiculoService.obterVeiculoPeloID(id);
        return ResponseEntity.ok(veiculoDTO);
    }

    @GetMapping("/contagem-ativos")
    public ResponseEntity<Object> obterContagemAtivos(){
        Integer contagemVeiculos = veiculoService.contaVeiculosAtivos();
        return ResponseEntity.ok(contagemVeiculos);
    }

    @GetMapping("/contagem-sul")
    public ResponseEntity<Object> obterContagemSul(){
        Integer contagemVeiculos = veiculoService.contaVeiculosSul();
        return ResponseEntity.ok(contagemVeiculos);
    }

    @GetMapping("/contagem-leste")
    public ResponseEntity<Object> obterContagemLeste(){
        Integer contagemVeiculos = veiculoService.contaVeiculosLeste();
        return ResponseEntity.ok(contagemVeiculos);
    }

    @PostMapping("/cadastrar-veiculo")
    public ResponseEntity<Object> cadastrarVeiculo(@RequestBody @Valid VeiculoDTO veiculoDTO){
        veiculoService.cadastrarVeiculo(veiculoDTO);
        return new ResponseEntity<>("Veículo cadastrado com sucesso.", HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> atualizarVeiculo(@PathVariable  Integer id, @RequestBody @Valid VeiculoDTO veiculoDTO ){
        veiculoService.atualizarVeiculo(veiculoDTO, id);
        return new ResponseEntity<>("Veículo atualizado com sucesso.", HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public  ResponseEntity<Object> deletarVeiculo(@PathVariable Integer id){
        veiculoService.deletarVeiculo(id);
        return new ResponseEntity<>("Veículo atualizado com sucesso.", HttpStatus.OK);
    }
}
