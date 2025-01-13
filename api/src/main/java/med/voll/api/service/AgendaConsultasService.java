package med.voll.api.service;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.validation.ValidationException;
import med.voll.api.DTO.consulta.DadosAgendamentoConsulta;
import med.voll.api.DTO.consulta.DadosCancelamentoConsulta;
import med.voll.api.DTO.consulta.DadosDetalhamentoConsulta;
import med.voll.api.model.Consulta;
import med.voll.api.model.Medico;
import med.voll.api.model.Paciente;
import med.voll.api.repository.ConsultaRepository;
import med.voll.api.repository.MedicoRepository;
import med.voll.api.repository.PacienteRepository;

@Service
public class AgendaConsultasService {

    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private MedicoService medicoService;

    @Transactional
    public DadosDetalhamentoConsulta agendarConsulta(DadosAgendamentoConsulta dados) {
        if (!medicoRepository.existsById(dados.idMedico())) {
            throw new ValidationException("M�dico n�o encontrado");
        }
        if (!pacienteRepository.existsById(dados.idPaciente())) {
            throw new ValidationException("Paciente n�o encontrado");
        }
        medicoService.isMedicoAtivo(dados.idMedico());
        validarHorarioFuncionamento(dados);
        validarHorarioAntecedencia(dados);
        isMedicoDisponivel(dados);

        Medico medico = escolherMedico(dados);

        if (medico == null) {
            throw new ValidationException(
                    "N�o foi poss�vel encontrar um m�dico dispon�vel para a especialidade informada");
        }

        Paciente paciente = pacienteRepository.getReferenceById(dados.idPaciente());
        Consulta consulta = new Consulta(medico, paciente, dados.data());
        consultaRepository.save(consulta);
        return new DadosDetalhamentoConsulta(consulta);
    }

    private Medico escolherMedico(DadosAgendamentoConsulta dados) {
        if (dados.idMedico() != null) {
            return medicoRepository.getReferenceById(dados.idMedico());
        }
        if (dados.especialidade() == null) {
            throw new ValidationException("Especialidade � obrigat�ria quando m�dico n�o � informado");
        }
        return medicoRepository.escolherMedicoAleatorioLivreNaData(dados.especialidade(), dados.data());
    }

    @Transactional
    public void cancelar(DadosCancelamentoConsulta dados) {
        if (!consultaRepository.existsById(dados.idConsulta())) {
            throw new ValidationException("Id da consulta informado n�o existe!");
        }
        validar(dados);
        var consulta = consultaRepository.getReferenceById(dados.idConsulta());
        consulta.cancelar(dados.motivo());
    }

    public void validarHorarioFuncionamento(DadosAgendamentoConsulta dados) {
        if (dados.data().getDayOfWeek().equals(DayOfWeek.SUNDAY)) {
            throw new ValidationException("N�o � poss�vel agendar consultas aos domingos");
        }
        if (dados.data().getHour() < 7 || dados.data().getHour() > 18) {
            throw new ValidationException("Hor�rio de funcionamento: 07:00 �s 18:00");
        }
    }

    public void validarHorarioAntecedencia(DadosAgendamentoConsulta dados) {
        if (dados.data().isBefore(dados.data().now().plusMinutes(30))) {
            throw new ValidationException("A consulta deve ser agendada com pelo menos 30 minutos de anteced�ncia");
        }
    }

    public void isMedicoDisponivel(DadosAgendamentoConsulta dados) {

        if (consultaRepository.existsByMedicoIdAndData(medicoRepository.getReferenceById(dados.idMedico()),
                dados.data())) {
            throw new ValidationException("M�dico j� possui consulta agendada para o hor�rio informado");
        }
    }

    public void validar(DadosCancelamentoConsulta dados) {
        var consulta = consultaRepository.getReferenceById(dados.idConsulta());
        var agora = LocalDateTime.now();
        var diferencaEmHoras = Duration.between(agora, consulta.getData()).toHours();

        if (diferencaEmHoras < 24) {
            throw new ValidationException("Consulta somente pode ser cancelada com anteced�ncia m�nima de 24h!");
        }
    }
}
