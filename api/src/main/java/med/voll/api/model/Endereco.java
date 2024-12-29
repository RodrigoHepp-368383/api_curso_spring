package med.voll.api.model;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import med.voll.api.DTO.endereco.DadosEndereco;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
public class Endereco {

    private String logradouro;
    private String bairro;
    private String cep;
    private String cidade;
    private String uf;
    private String complemento;
    private String numero;

    public Endereco(DadosEndereco dadosEndereco){
        this.logradouro = dadosEndereco.logradouro();
        this.bairro = dadosEndereco.bairro();
        this.cep = dadosEndereco.cep();
        this.cidade = dadosEndereco.cidade();
        this.uf = dadosEndereco.uf();
        this.complemento = dadosEndereco.complemento();
        this.numero = dadosEndereco.numero();
    }
}
