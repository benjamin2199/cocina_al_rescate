package cocinaAlRescate.cocina.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import cocinaAlRescate.cocina.Model.Usuario;
import cocinaAlRescate.cocina.Repository.UsuarioRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Usuario> list() {
        return usuarioRepository.findAll();
    }           

    public Usuario buscarporidUsuario(long idUsuario) {
        return usuarioRepository.findById(idUsuario).get();
    }

    public Usuario guardaUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public void eliminarUsuario(long idUsuario) {
        usuarioRepository.deleteById(idUsuario);
    }

    




}
