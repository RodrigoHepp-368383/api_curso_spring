package med.voll.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import med.voll.api.DTO.medico.DadosAtualizarMedico;
import med.voll.api.DTO.medico.DadosCadastroMedico;
import med.voll.api.DTO.medico.DadosListagemMedico;
import med.voll.api.model.Medico;
import med.voll.api.service.MedicoService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/medicos")
public class MedicoController {

    @Autowired
    private MedicoService medicoService;

    @PostMapping("/cadastrar")
    public void cadastrar (@RequestBody @Valid DadosCadastroMedico json) {
        medicoService.salvarMedico(new Medico(json));
    }

    @GetMapping("/listar")
    public List<DadosListagemMedico> listaMedicos(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
        return medicoService.listarMedicos(paginacao);
    }

    @PutMapping("/atualizar")
    public void atualizarMedico(@RequestBody @Valid DadosAtualizarMedico json) {
        medicoService.atualizarMedico(json);
    }

    @DeleteMapping("/deletar/{id}")
    public void deletarMedico(@PathVariable Long id) {
        medicoService.deletarMedico(id);
    }
}
