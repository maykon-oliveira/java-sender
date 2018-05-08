package javasender;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javasender.config.MailSender;
import javax.mail.MessagingException;
import javax.swing.JOptionPane;

/**
 *
 * @author 20171148060009
 */
public class MainController implements Initializable {
    
    @FXML private Button button;
    @FXML private TextField DestinatarioTextFiled;
    @FXML private TextField assuntoTextFiled;
    @FXML private TextArea corpoMensagem;
    
    MailSender mailSender;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        mailSender = new MailSender();
        System.out.println("b");
    }    

    @FXML
    private void handleEnviarMensagem(ActionEvent event) throws MessagingException {
        if (validarCampos()) {
            mailSender.sendMensagem(assuntoTextFiled.getText(), corpoMensagem.getText(), DestinatarioTextFiled.getText());            
            JOptionPane.showMessageDialog(null, "Mensagem enviada.");
        }
    }
    
    private boolean validarCampos() {
        return (assuntoTextFiled.getText() != null && corpoMensagem.getText() != null) && DestinatarioTextFiled.getText() != null;
    }
}
