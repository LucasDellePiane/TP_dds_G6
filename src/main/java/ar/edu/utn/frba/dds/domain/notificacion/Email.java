package ar.edu.utn.frba.dds.domain.notificacion;

import ar.edu.utn.frba.dds.domain.usuario.Usuario;

import java.util.List;

public class Email implements MedioComunicacion{
    private List<Usuario> usuariosSuscriptos;
    @Override
    public void suscribirUsuario(Usuario usuario) {
        usuariosSuscriptos.add(usuario);
    }

    @Override
    public void enviarNotifiacion(List<Usuario> usuarios) {

    }

}
