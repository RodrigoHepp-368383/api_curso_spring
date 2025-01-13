package med.voll.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import med.voll.api.DTO.consulta.DadosAgendamentoConsulta;
import med.voll.api.DTO.consulta.DadosCancelamentoConsulta;
import med.voll.api.DTO.consulta.DadosDetalhamentoConsulta;
import med.voll.api.service.AgendaConsultasService;

// Trecho de c?digo suprimido

@RestController
@RequestMapping("consultas")
public class ConsultaController {

    @Autowired
    private AgendaConsultasService agendaConsultas;

    @PostMapping
    public ResponseEntity agendar(@RequestBody @Valid DadosAgendamentoConsulta dados) {
        var dto = agendaConsultas.agendarConsulta(dados);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping
    public ResponseEntity cancelar(@RequestBody @Valid DadosCancelamentoConsulta dados) {
        agendaConsultas.cancelar(dados);
        return ResponseEntity.noContent().build();
    }

}