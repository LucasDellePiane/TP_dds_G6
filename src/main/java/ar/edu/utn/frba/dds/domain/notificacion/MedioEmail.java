package ar.edu.utn.frba.dds.domain.notificacion;

import ar.edu.utn.frba.dds.domain.usuario.Usuario;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import ar.edu.utn.frba.dds.exceptions.SeEnvioEmailException;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Arrays;
import java.util.Properties;



public class MedioEmail implements MedioComunicacion{

    private List<Usuario> usuariosSuscriptos;     //ELIMINAR - NO DEBERÍA ESTAR MÁS
    private ScheduledExecutorService scheduler;
    private final String username = "emailRemitente";
    private final String accessToken = "tokenAcceso";

    @Override   //ELIMINAR - NO DEBERÍA ESTAR MÁS
    public void suscribirUsuario(Usuario usuario) {
        usuariosSuscriptos.add(usuario);
    }

    //LA LÓGICA DE LA PROGRAMACIÓN DE ENVIOS DE EMAIL NO DEBE ESTAR EN ESTA CLASE POR COHESIÓN
    //NO SE DEBE PROGRAMAR UNA TAREA POR CADA NOTIFICACIÓN DE CADA USUARIO
    @Override
    public void enviarNotificacion(List<Usuario> usuarios) {
        List<Usuario> usuariosANotificar = usuarios.stream()
            .filter(unUsuario -> usuariosSuscriptos.contains(unUsuario))
            .toList();

        throw new SeEnvioEmailException("Mensaje enviado al usuario");

        /*

        // Configurar el scheduler para el envío de correos electrónicos
        scheduler = Executors.newScheduledThreadPool(1);

        // Enviar mensajes a los usuarios utilizando la API de Email
        for (Usuario usuario : usuariosANotificar) {
            //int horaEnvio = usuario.getHoraEnvio();
            int horaEnvio = usuario.proximoHorarioNotificaxion();
            int minutoEnvio = 0;  //Minutos siempre en 0

            // Calcular el retraso hasta el próximo horario de envío
            long retrasoInicial = calcularRetrasoInicial(horaEnvio,minutoEnvio);

            // Programar el envío del correo electrónico en el próximo horario de envío
            scheduler.schedule(() -> enviarEmail(usuario.getEmail(), "Nuevo incidente reportado"),
                retrasoInicial, TimeUnit.MILLISECONDS);
        }
        */

    }

    private long calcularRetrasoInicial(int horaEnvio, int minutoEnvio) {
        // Obtener la fecha y hora actuales
        LocalDateTime now = LocalDateTime.now();
        int horaActual = now.getHour();
        int minutoActual = now.getMinute();

        // Crea un objeto LocalDateTime que representa la fecha y la hora del próximo horario de envío
        LocalDateTime proximoHorarioEnvio = LocalDateTime.of(now.toLocalDate(), LocalTime.of(horaEnvio, minutoEnvio));

        if (proximoHorarioEnvio.isBefore(now) || proximoHorarioEnvio.isEqual(now)) {
            // El próximo horario de envío está en el día siguiente
            proximoHorarioEnvio = proximoHorarioEnvio.plusDays(1);
        }

        // Calcular el retraso inicial en milisegundos hasta el próximo horario de envío
        return now.until(proximoHorarioEnvio, ChronoUnit.MILLIS);
    }


    public void Email() {
        List<Usuario> usuarios = new ArrayList<>(Arrays.asList());
        this.usuariosSuscriptos = usuarios;
    }

    @Override  //ELIMINAR - NO DEBERÍA ESTAR MÁS
    public List<Usuario> getUsuariosSuscriptos() {
        return usuariosSuscriptos;
    }

    public MedioEmail() {
        List<Usuario> usuariosSuscriptos = new ArrayList<>();
        this.usuariosSuscriptos = usuariosSuscriptos;
    }


    //LO ÚNICO QUE QUEDARÍA EN ESTA CLASE ES enviarEmail(String emailUsuario, String mensaje)
    private void enviarEmail(String emailUsuario, String mensaje) {
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, accessToken);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(emailUsuario));
            message.setSubject("Notificación de incidente");
            message.setText(mensaje);

            Transport.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException("Error al enviar el correo electrónico: " + e.getMessage());
        }


    }

}
