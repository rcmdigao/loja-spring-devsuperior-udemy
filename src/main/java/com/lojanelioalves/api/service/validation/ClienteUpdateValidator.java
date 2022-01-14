package com.lojanelioalves.api.service.validation;

import com.lojanelioalves.api.dto.ClienteDTO;
import com.lojanelioalves.api.entities.Cliente;
import com.lojanelioalves.api.entities.enums.TipoCliente;
import com.lojanelioalves.api.repositories.ClienteRepository;
import com.lojanelioalves.api.resources.exceptions.FieldMessage;
import com.lojanelioalves.api.service.validation.utils.BR;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ClienteUpdateValidator implements ConstraintValidator<ClienteUpdate, ClienteDTO> {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private ClienteRepository repo;

    @Override
    public void initialize(ClienteUpdate ann) {
    }

    @Override
    public boolean isValid(ClienteDTO objDto, ConstraintValidatorContext context) {

        // Recuperando o ID na URI
        @SuppressWarnings("unchecked")
        Map<String, String> map = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
        Integer uriID = Integer.parseInt(map.get("id"));

        List<FieldMessage> list = new ArrayList<>();

        Cliente cliente = repo.findByEmail(objDto.getEmail());
        if (cliente != null && !cliente.getId().equals(uriID)) {
            list.add(new FieldMessage("email", "Email já existente"));
        }

        // Erros personalizados
        for (FieldMessage e : list) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
                    .addConstraintViolation();
        }
        return list.isEmpty();
    }
}
