package br.com.ecourbis.cadastro_veiculos.viewcontrollers;

import br.com.ecourbis.cadastro_veiculos.dtos.VeiculoDTO;
import br.com.ecourbis.cadastro_veiculos.services.VeiculoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/")
public class VeiculoViewController {
    private final VeiculoService veiculoService;

    @Autowired
    public VeiculoViewController(VeiculoService veiculoService) {
        this.veiculoService = veiculoService;
    }

    @GetMapping()
    public String listarVeiculos(Model model) {
        List<VeiculoDTO> veiculosAtivos = veiculoService.listarVeiculosAtivos(0,5);
        model.addAttribute("veiculosAtivos", veiculosAtivos);
        return "index";
    }

    @GetMapping("/formulario")
    public String mostrarFormulario(Model model) {
        VeiculoDTO veiculoDTO = new VeiculoDTO();
        model.addAttribute("veiculoDTO", veiculoDTO);
        return "formulario"; // Retorna o nome da página HTML do formulário de adição
    }

    @PostMapping("/cadastrar")
    public String cadastrarVeiculo(@Valid VeiculoDTO veiculoDTO, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult.getAllErrors());
            return "formulario";
        }
        veiculoService.cadastrarVeiculo(veiculoDTO);
        return "redirect:/formulario";

    }

    @GetMapping("/editar/{id}")
    public String editarVeiculo(@PathVariable Integer id) {

        return "formulario"; // Retorna o nome da página HTML do formulário de adição
    }

    @GetMapping("/deletar/{id}")
    public String deletarVeiculo(@PathVariable Integer id) {
        System.out.println("codVeiculo do parametro: " + id);
        veiculoService.deletarVeiculo(id);
        return "redirect:/";
    }
    // Outros métodos de navegação...

}