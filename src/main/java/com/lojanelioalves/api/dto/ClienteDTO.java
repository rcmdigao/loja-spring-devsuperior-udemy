package com.lojanelioalves.api.dto;

import com.lojanelioalves.api.entities.Cliente;
import com.lojanelioalves.api.service.validation.ClienteUpdate;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@ClienteUpdate
public class ClienteDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    @NotEmpty(message = "Preenchimento Obrigatório")
    @Length(min = 5, max = 80, message = "O tamanho deve ser entre 5 e 120 caracteres")
    private String nome;

    @NotEmpty(message = "Preenchimento Obrigatório")
    @Email(message = "E-mail inválido")
    private String email;

    public ClienteDTO() {
    }

    public ClienteDTO(Cliente obj) {
       id = obj.getId();
       nome = obj.getNome();
       email = obj.getEmail();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
