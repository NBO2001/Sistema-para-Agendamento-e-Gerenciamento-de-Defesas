package model;

import view.cadastrodefesa.CadastroDefesaVariant03;
import view.home.Home;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;

public class Defense {

    private int defenseId;
    private int typeDefense;
    private String defenseTitle;
    private Date date;
    private String local;

    private Teacher teacherAdvisor;
    private Student studentDefending;
    private double finalPontuation;
    private int status;
    private String observation;
    private ArrayList<Teacher> boardOfTeachers;

    /**
     * typeDefense, if it is a defense of pibic, tcc etc.
     * */
    public int getDefenseId() {
        return defenseId;
    }

    public void setDefenseId(int defenseId) {
        this.defenseId = defenseId;
    }

    public int getTypeDefense() {
        return typeDefense;
    }

    public void setTypeDefense(int typeDefense) {
        this.typeDefense = typeDefense;
    }

    public String getDefenseTitle() {
        return defenseTitle;
    }

    public void setDefenseTitle(String defenseTitle) {
        this.defenseTitle = defenseTitle;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setDate(String date) {
        date = date.replaceAll("\\s", "");
        DateFormat inputFormat = new SimpleDateFormat("dd/MM/yyyy");
//        DateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date1 = inputFormat.parse(date);
            setDate(date1);
        } catch (ParseException e) {
            e.printStackTrace();
        }


    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public Teacher getTeacherAdvisor() {
        return teacherAdvisor;
    }

    public void setTeacherAdvisor(Teacher teacherAdvisor) {
        this.teacherAdvisor = teacherAdvisor;
    }

    public Student getStudentDefending() {
        return studentDefending;
    }

    public void setStudentDefending(Student studentDefending) {
        this.studentDefending = studentDefending;
    }

    public double getFinalPontuation() {
        return finalPontuation;
    }

    public void setFinalPontuation(double finalPontuation) {
        this.finalPontuation = finalPontuation;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getObservation() {
        return observation;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }

    public ArrayList<Teacher> getBoardOfTeachers() {
        return boardOfTeachers;
    }

    public void setBoardOfTeachers(ArrayList<Teacher> boardOfTeachers) {
        this.boardOfTeachers = boardOfTeachers;
    }

    public static String parseTypeDefenseFormatString(int typeDefense){

        switch (typeDefense){
            case 1:
                return "Defesa de projeto final/TCC";
            case 2:
                return "Defesa de qualificação de mestrado";
            case 3:
                return "Defesa de dissertação de mestrado";
            case 4:
                return "Defesa de qualificação de doutorado";
            case 5:
                return "Defesa de tese de doutorado";
            case 6:
                return "Defesa de artigo";
            default:
                return "null";
        }
    }

    public static Double stringToDouble(String value){
        Double val = 0.0;
        try{
            val = Double.parseDouble(value);
        }catch (Exception e){
            System.out.println(e);
        }

        return val;
    }

    public static String dateToString(Date dateIn){

        String inputFormat = "yyyy-MM-dd";
        String outputFormat = "dd/MM/yyyy";

        DateFormat inputDateFormat = new SimpleDateFormat(inputFormat);
        DateFormat outputDateFormat = new SimpleDateFormat(outputFormat);

        try{
            Date date = inputDateFormat.parse(dateIn.toInstant().atZone(ZoneId.systemDefault()).toLocalDate().toString());
            // Format the Date object into the desired output format
            String outputDate = outputDateFormat.format(date);

            return outputDate;

        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

    }

    public void clone(Defense defense){


        this.setDefenseId(defense.getDefenseId());
        this.setTypeDefense(defense.getTypeDefense());
        this.setDefenseTitle(defense.getDefenseTitle());
        this.setDate(defense.getDate());
        this.setLocal(defense.getLocal());
        this.setTeacherAdvisor(defense.getTeacherAdvisor());
        this.setStudentDefending(defense.getStudentDefending());
        this.setFinalPontuation(defense.getFinalPontuation());
        this.setStatus(defense.getStatus());
        this.setObservation(defense.getObservation());

        ArrayList<Teacher> boardOfTeachers_clone = new ArrayList<>();

        if(defense.getBoardOfTeachers() != null){
            for(Teacher teacher: defense.getBoardOfTeachers()){
                boardOfTeachers_clone.add(teacher);
            }
        }

        this.setBoardOfTeachers(boardOfTeachers_clone);

    }

    public static boolean callForDefense(String filePath, Defense defense) {

        String teachesBoard ="";
        int i=0;
        for(; i < defense.getBoardOfTeachers().size()-1;i++){
            teachesBoard += defense.getBoardOfTeachers().get(i).getName() + ", ";
        }

        teachesBoard += defense.getBoardOfTeachers().get(i).getName();

        // Generate the HTML code
        String htmlCode = "<!DOCTYPE html>\n" +
                "<html lang='en'>\n" +
                "<head>\n" +
                "  <meta charset='UTF-8'>\n" +
                "  <meta name='viewport' content='width=device-width, initial-scale=1.0'>\n" +
                "  <title>Article Defense Call</title>\n" +
                "  <style>\n" +
                "    body {\n" +
                "      background-color: #FFFFF7;\n" +
                "      color: #D93A2B;\n" +
                "      font-family: Arial, sans-serif;\n" +
                "      margin: 0;\n" +
                "      padding: 0;\n" +
                "    }\n" +
                "    .container {\n" +
                "      max-width: 800px;\n" +
                "      margin: 0 auto;\n" +
                "      padding: 20px;\n" +
                "    }\n" +
                "    header, footer {\n" +
                "      background-color: #0ABFBF;\n" +
                "      color: #FFFFFF;\n" +
                "      padding: 20px;\n" +
                "      text-align: center;\n" +
                "    }\n" +
                "    h1 {\n" +
                "      color: #D93A2B;\n" +
                "      font-size: 24px;\n" +
                "    }\n" +
                "    p {\n" +
                "      font-size: 16px;\n" +
                "      margin-bottom: 10px;\n" +
                "    }\n" +
                "    .qrcode {\n" +
                "      text-align: center;\n" +
                "      margin-top: 20px;\n" +
                "    }\n" +
                "    .invitation {\n" +
                "      margin-top: 30px;\n" +
                "      font-size: 18px;\n" +
                "      text-align: center;\n" +
                "    }\n" +
                "  </style>\n" +
                "</head>\n" +
                "<body>\n" +
                "  <header>\n" +
                "    <img src='https://ufam.edu.br/images/conteudo/aniversario/114/u114a.jpg' alt='University Logo' width='310'>\n" +
                "  </header>\n" +
                "\n" +
                "  <div class='container'>\n" +
                "    <h1>Article Defense Call</h1>\n" +
                "\n" +
                "    <p><strong>Student:</strong> " + defense.getStudentDefending().getName() + "</p>\n" +
                "    <p><strong>Program:</strong> " + Defense.parseTypeDefenseFormatString(defense.getTypeDefense()) + "</p>\n" +
                "    <p><strong>Article Title:</strong> " + defense.getDefenseTitle() + "</p>\n" +
                "    <p><strong>Guiding Professor:</strong> " + defense.getTeacherAdvisor().getName() +"</p>\n" +
                "    <p><strong>Evaluation Board:</strong> " + teachesBoard + "</p>\n" +
                "    <p><strong>Date and Time:</strong> " + Utils.defenseDateExtension(defense.getDate()) + "</p>\n" +
                "\n" +
                "    <div class='qrcode'>\n" +
                "      <img src='https://api.qrserver.com/v1/create-qr-code/?size=150x150&data=" + defense.getStudentDefending().getEmail() + "' alt='Author's Email QR Code' width='150'>\n" +
                "    </div>\n" +
                "\n" +
                "    <div class='invitation'>\n" +
                "      <p>Please join us for the defense of "+defense.getStudentDefending().getName()+"'s article titled '"+ defense.getDefenseTitle() +"' on "+ Utils.defenseDateExtension(defense.getDate()) + " in the " + defense.getLocal() +".</p>\n" +
                "      <p>We look forward to your presence!</p>\n" +
                "    </div>\n" +
                "  </div>\n" +
                "\n" +
                "  <footer>\n" +
                "    <img src='https://icomp.ufam.edu.br/images/icomp.png' alt='Institute Logo' width='150'>\n" +
                "  </footer>\n" +
                "</body>\n" +
                "</html>\n";

        // Save the HTML code to the specified file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(htmlCode);
            return true;
        } catch (IOException e) {
            System.out.println("Error occurred while saving the HTML file: " + e.getMessage());
        }
        return false;
    }

}
