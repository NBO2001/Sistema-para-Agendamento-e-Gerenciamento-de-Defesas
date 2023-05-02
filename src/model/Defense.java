package model;

import java.util.ArrayList;
import java.util.Date;

public class Defense {

    /**
     * typeDefense, if it is a defense of pibic, tcc etc.
     * */
    private int typeDefense;
    private String defenseTitle;
    private Date date;
    private String local;

    private Teacher teacherAdvisor;
    private Student studentDefending;
    private ArrayList<Teacher> boardOfTeachers;

    private double finalPontuation;
    private int status;
    private String observation;

}
