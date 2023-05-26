import model.Person;
import model.People;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
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

        Person person;
        People people = new People();

        person = people.selectPerson(new Person(1));

//        System.out.println(person);

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date birthDate;
        try {
            birthDate = dateFormat.parse("2001-11-01");
        } catch (ParseException e) {
            // Handle the exception if the date string is invalid
            e.printStackTrace();
            return; // Or throw an exception, or handle it according to your requirements
        }

        Person person1 = new Person(
                "Natanael Bezerra", "Larissa", birthDate,
                "58245845168", "854736470", "natanael.oliveira@icomp.ufam.edu.br", "9298429962");

        System.out.println(person1);

        people.insertPerson(person1);


//        Login login = new Login();
        //CadastroProfessor cadastroProfessor = new CadastroProfessor();


//        CadastroAluno cadastroAluno = new CadastroAluno();
//        EditCadastroVariant02 editCadastroVariant02 = new EditCadastroVariant02();
//        editCadastroVariant02.setVisible(true);
    }
}
