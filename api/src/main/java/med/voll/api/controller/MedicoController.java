package med.voll.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import med.voll.api.DTO.medico.DadosCadastroMedico;
import med.voll.api.model.Medico;
import med.voll.api.service.MedicoService;


@RestController
@RequestMapping("/medicos")
public class MedicoController {

    @Autowired
    private MedicoService medicoService;

    @PostMapping("/cadastrar")
    public void cadastrar (@RequestBody @Valid DadosCadastroMedico json) {
        medicoService.salvarMedico(new Medico(json));
    }
    
}
