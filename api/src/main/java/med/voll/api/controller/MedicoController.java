package med.voll.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import med.voll.api.DTO.medico.DadosAtualizarMedico;
import med.voll.api.DTO.medico.DadosCadastroMedico;
import med.voll.api.DTO.medico.DadosDetalhamentoMedico;
import med.voll.api.DTO.medico.DadosListagemMedico;
import med.voll.api.model.Medico;
import med.voll.api.service.MedicoService;

@RestController
@RequestMapping("/medicos")
@SecurityRequirement(name = "bearer-key")
public class MedicoController {

    @Autowired
    private MedicoService medicoService;

    @PostMapping("/cadastrar")
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroMedico json, UriComponentsBuilder uriBuilder) {
        var medico = new Medico(json);
       medicoService.salvarMedico(medico);
        var uri = uriBuilder.path("/medicos/{id}").buildAndExpand(medico.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosDetalhamentoMedico(medico));
    }

    @GetMapping("/listar")
    public ResponseEntity<Page<DadosListagemMedico>> listaMedicos(
            @PageableDefault(size = 10, sort = { "nome" }) Pageable paginacao) {
        return ResponseEntity.ok(medicoService.listarMedicos(paginacao));
    }

    @PutMapping("/atualizar")
    public ResponseEntity atualizarMedico(@RequestBody @Valid DadosAtualizarMedico json) {
        DadosDetalhamentoMedico dados = medicoService.atualizarMedico(json);
        return ResponseEntity.ok(dados);
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity deletarMedico(@PathVariable Long id) {
        medicoService.deletarMedico(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/consultar/{id}")
    public ResponseEntity consultarMedico(@PathVariable Long id) {
        return ResponseEntity.ok(medicoService.consultarMedico(id));
    }
}
