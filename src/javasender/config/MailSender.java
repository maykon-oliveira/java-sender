package javasender.config;

import java.io.File;
import java.io.IOException;
import java.util.Properties;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

/**
 * givanaldoifrn@hotmail.com
 * @author 20171148060009
 */
public class MailSender {
    Properties credenciasUsario = new Properties();

    public MailSender() {
        loadPreperties();
    }    
    private void loadPreperties() {
        try {
            credenciasUsario.load(ClassLoader.getSystemClassLoader().getResourceAsStream("javasender/config/credencias.properties"));
            
        } catch (IOException ex) {
            System.out.println("Erro ao carregar as propriedades");
        }
    }
    
    public void sendMensagem(String assunto, String mensagem, String destinatario, File anexo) throws EmailException {
        System.out.println("Enviando mensagem...");
        String username = credenciasUsario.getProperty("hotmail.email");
        String password = credenciasUsario.getProperty("hotmail.password");
        
        
        HtmlEmail email = new HtmlEmail();
        email.setHostName("smtp.live.com");
        email.setSmtpPort(587);
        email.setStartTLSRequired(true);
        email.setAuthenticator(new DefaultAuthenticator(username, password));
        email.setFrom(credenciasUsario.getProperty("hotmail.email"));
        email.setSubject(assunto);
        email.setMsg(mensagem);
        email.attach(anexo);
        email.addTo(destinatario);
        email.setDebug(true);
        email.send();
        
        System.out.println("Mensagem enviada");
    }
    
    private EmailAttachment makeAttachment(File anexo) {
        if (anexo != null) {
            EmailAttachment emailAnexo = new EmailAttachment();
            emailAnexo.setPath(anexo.getPath());
            emailAnexo.setDescription(EmailAttachment.ATTACHMENT);
            return emailAnexo;
        }
        return null;
    }
}
