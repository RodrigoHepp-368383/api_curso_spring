package med.voll.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import med.voll.api.model.Paciente;
import med.voll.api.repository.PacienteRepository;

@Service
public class PacienteService {

    @Autowired
    private PacienteRepository pacienteRepository;

    public Paciente ConsultaPacientePorId(Long id) {
        return pacienteRepository.findById(id).orElse(null);
    }
}