package javasender;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javasender.config.MailSender;
import javax.swing.JOptionPane;
import org.apache.commons.mail.EmailException;

/**
 *
 * @author 20171148060009
 */
public class MainController implements Initializable {

    @FXML
    private TextField DestinatarioTextFiled;
    @FXML
    private TextField assuntoTextFiled;
    @FXML
    private TextArea corpoMensagem;
    @FXML
    private Label nomeAnexoLabel;
    private File anexo;

    MailSender mailSender;
    FileChooser fileChooser;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        mailSender = new MailSender();
        iniciarFileChoose();
    }

    private void iniciarFileChoose() {
        fileChooser = new FileChooser();
        fileChooser.setTitle("Escolher arquivo");
    }

    @FXML
    private void handleEnviarMensagem(ActionEvent event) {
        try {
            if (validarCampos()) {
                mailSender.sendMensagem(assuntoTextFiled.getText(), corpoMensagem.getText(), DestinatarioTextFiled.getText(), anexo);
                limparCampos();
            }

        } catch (EmailException e) {
            System.out.println("Erro ao enviar mensagem");
            e.printStackTrace();
        }
    }

    private boolean validarCampos() {
        return (assuntoTextFiled.getText() != null && corpoMensagem.getText() != null) && DestinatarioTextFiled.getText() != null;
    }

    private void limparCampos() {
        assuntoTextFiled.clear();
        DestinatarioTextFiled.clear();
        corpoMensagem.clear();
    }

    @FXML
    private void handleAnexarArquivo(ActionEvent event) {
        anexo = fileChooser.showOpenDialog(new Stage());
        
        if (anexo != null) {
            nomeAnexoLabel.setText(anexo.getName());
        }
    }
}
