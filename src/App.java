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

        Login login = new Login();
        //CadastroProfessor cadastroProfessor = new CadastroProfessor();

        //CadastroUsuario cadastroUsuario = new CadastroUsuario();
        // login.setVisible(true);

//        CadastroAluno cadastroAluno = new CadastroAluno();
        CadastroDefesaVariant03 cadastroDefesaVariant03 = new CadastroDefesaVariant03();
        cadastroDefesaVariant03.setVisible(true);
    }
}
