package br.com.ecourbis.cadastro_veiculos.viewcontrollers;

import br.com.ecourbis.cadastro_veiculos.dtos.VeiculoDTO;
import br.com.ecourbis.cadastro_veiculos.models.Veiculo;
import br.com.ecourbis.cadastro_veiculos.services.VeiculoService;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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

    @GetMapping("/")
    public String listarVeiculos(Model model,
                                 @RequestParam(value = "pesquisa", defaultValue = "") String pesquisa,
                                 @RequestParam(defaultValue = "0") int pagina,
                                 @RequestParam(defaultValue = "5") int itens) {
        System.out.println("pesquisa: "+ pesquisa);
        Integer contagemVeiculos = veiculoService.contaVeiculosAtivos();
        Integer contagemSul = veiculoService.contaVeiculosSul();
        Integer contagemLeste = veiculoService.contaVeiculosLeste();

        model.addAttribute("contagemVeiculos", contagemVeiculos);
        model.addAttribute("contagemSul", contagemSul);
        model.addAttribute("contagemLeste", contagemLeste);


        List<VeiculoDTO> veiculosAtivos;
        int totalRegistros;

        if (pesquisa.isEmpty()) {
            veiculosAtivos = veiculoService.listarVeiculosAtivos(pagina, itens);
            // Buscar o número total de registros
            totalRegistros = veiculoService.contaVeiculosAtivos();
            System.out.println("Size veiculosAtivos: " + veiculosAtivos.size());
        } else {
            veiculosAtivos = veiculoService.pesquisarVeiculos(pesquisa, pagina, itens);
            // Buscar o número total de registros
            totalRegistros = veiculoService.contaVeiculosPesquisa(pesquisa);
            System.out.println("Size veiculosAtivos: " + veiculosAtivos.size());
        }

        model.addAttribute("veiculosAtivos", veiculosAtivos);


        // Calcular o número total de páginas
        int totalPaginas = (int) Math.ceil((double) totalRegistros / itens);
        model.addAttribute("totalPaginas", totalPaginas);
        System.out.println("pagina: " + pagina);
        int paginaAnterior = pagina > 0 ? pagina - 1 : 0;
        int proximaPagina = pagina < totalPaginas - 1 ? pagina + 1 : totalPaginas - 1;
        model.addAttribute("paginaAnterior", paginaAnterior);
        model.addAttribute("proximaPagina", proximaPagina);

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
        return "redirect:/";

    }

    @GetMapping("/edicao/{id}")
    public String mostrarFormularioEdicao(@PathVariable Integer id, Model model) {
        VeiculoDTO veiculoDTO = veiculoService.obterVeiculoPeloID(id);
        model.addAttribute("veiculoDTO", veiculoDTO);

        return "editar"; // Retorna o nome da página HTML do formulário de adição
    }

    @PostMapping("/editar/{id}")
    public String editarVeiculo(@PathVariable Integer id, @Valid VeiculoDTO veiculoDTO, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult.getAllErrors());
            return "formulario";
        }
        veiculoService.atualizarVeiculo(veiculoDTO, id);
        return "redirect:/";

    }

    @GetMapping("/deletar/{id}")
    public String deletarVeiculo(@PathVariable Integer id) {
        System.out.println("codVeiculo do parametro: " + id);
        veiculoService.deletarVeiculo(id);
        return "redirect:/";
    }

    @GetMapping("/pesquisar/{pesquisa}")
    public String pesquisarVeiculo(Model model, @RequestParam(name = "pesquisa", required = false) String pesquisa) {
        veiculoService.pesquisarVeiculos(pesquisa, 0, 5);
        return "redirect:/";
    }

}
