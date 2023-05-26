import model.ConnectionBase;
import model.Session;
import model.SessionManager;
import utils.Utils;
import view.cadastro.Cadastro;
import view.cadastroaluno.CadastroAluno;
import view.cadastrodefesa.CadastroDefesaVariant01;
import view.cadastrodefesa.CadastroDefesaVariant02;
import view.cadastrodefesa.CadastroDefesaVariant03;
import view.cadastroprofessor.CadastroProfessor;
import view.cadastrousuario.CadastroUsuario;
import view.login.Login;

public class App {

    public static void main(String[] args) {

        SessionManager sessionManager = new SessionManager();

        Session session = sessionManager.login("admin", "admin");

        System.out.println(session);


    }
}
