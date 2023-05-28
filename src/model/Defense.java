package model;

import view.cadastrodefesa.CadastroDefesaVariant03;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
}
