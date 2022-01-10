package com.lojanelioalves.api.resources;

import com.lojanelioalves.api.entities.Categoria;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/categorias")
public class CategoriaResource {

    @RequestMapping(method = RequestMethod.GET)
    public List<Categoria> listar(){
        Categoria categoria1 = new Categoria(1, "Informatica");
        Categoria categoria2 = new Categoria(2, "Escritorio");
        List<Categoria> lista = new ArrayList<>();
        lista.add(categoria2);
        lista.add(categoria2);

        return lista;
    }


}
