package cocinaAlRescate.cocina.assemblers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import cocinaAlRescate.cocina.Controller.UsuarioControllerV2;
import cocinaAlRescate.cocina.Model.Usuario;

@Component
public class UsuarioModelAssembler implements RepresentationModelAssembler<Usuario, EntityModel<Usuario>> {

    @Override
    @org.springframework.lang.NonNull
    public EntityModel<Usuario> toModel(@org.springframework.lang.NonNull Usuario usuario) {
        return EntityModel.of(usuario,
                linkTo(methodOn(UsuarioControllerV2.class).getUsuarioByidUsuario((long) usuario.getIdUsuario())).withSelfRel(),
                linkTo(methodOn(UsuarioControllerV2.class).getAllUsuarios()).withRel("usuarios"));
    }
}
