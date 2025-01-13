package med.voll.api.DTO.consulta;

import jakarta.validation.constraints.NotNull;
import med.voll.api.enums.MotivoCancelamento;

public record DadosCancelamentoConsulta(@NotNull Long idConsulta,@NotNull MotivoCancelamento motivo) {}
