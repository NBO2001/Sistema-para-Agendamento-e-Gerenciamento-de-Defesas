package model;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {

    public static boolean isValidDate(String date){

        date = date.replaceAll("\\s", "");

        if( !date.contains("/") ) return false;

        String[] content = date.split("/");

        if(content.length < 3) return false;

        for(String atom: content){

            try{
                int atomValue = Integer.parseInt(atom);
            }catch (NumberFormatException e){
                return false;
            }
        }

        return true;

    }

    public static Date strToDateBr(String date){
        date = date.replaceAll("\\s", "");
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        try {
            return dateFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return null; // or handle the exception in an appropriate way
        }
    }

    public static String dateBrForDateEua(String date){
        date = date.replaceAll("\\s", "");
        DateFormat inputFormat = new SimpleDateFormat("dd/MM/yyyy");
        DateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd");

        try {
            Date brDate = inputFormat.parse(date);
            String usDate = outputFormat.format(brDate);
            return usDate;
        } catch (ParseException e) {
            e.printStackTrace();
            return null; // or handle the exception in an appropriate way
        }

    }

    public static String dateBrForDateEua(Date date){

        DateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd");

        String usDate = outputFormat.format(date);
        return usDate;
    }

    public static String dateForBr(Date date){

        DateFormat outputFormat = new SimpleDateFormat("dd/MM/yyyy");

        String usDate = outputFormat.format(date);
        return usDate;
    }

    public static String dateForStringEUAWithHour(Date date){

        DateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        String usDate = outputFormat.format(date);
        return usDate;
    }

    public static Date strToDateBrWithHour(String date){
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        try {
            return dateFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String dateForExtension(Date date){
        DateFormat dateFormat = new SimpleDateFormat("MMMM dd, yyyy");
        return dateFormat.format(date);
    }

    public static boolean isValidTime(String time) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
        dateFormat.setLenient(false);

        try {
            // Parse the input time string
            dateFormat.parse(time);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }
    public static boolean isValidCpf(String cpf) {
        // Remove any non-digit characters from the CPF
        cpf = cpf.replaceAll("\\D", "");

        // CPF must have 11 digits
        if (cpf.length() != 11) {
            return false;
        }

        // Calculate the first verification digit
        int sum = 0;
        for (int i = 0; i < 9; i++) {
            sum += (cpf.charAt(i) - '0') * (10 - i);
        }
        int digit1 = 11 - (sum % 11);
        if (digit1 > 9) {
            digit1 = 0;
        }

        // Calculate the second verification digit
        sum = 0;
        for (int i = 0; i < 10; i++) {
            sum += (cpf.charAt(i) - '0') * (11 - i);
        }
        int digit2 = 11 - (sum % 11);
        if (digit2 > 9) {
            digit2 = 0;
        }

        // Compare the calculated digits with the provided digits
        return (cpf.charAt(9) - '0') == digit1 && (cpf.charAt(10) - '0') == digit2;
    }

    public static boolean isValidEmail(String email){

        if(email == null) return  false;
        if(email.isEmpty()) return false;

        String EMAIL_PATTERN = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);

        Matcher matcher = pattern.matcher(email);
        return matcher.matches();

    }

    public static String encryptTolkienName(String name) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            byte[] hashBytes = md.digest(name.getBytes(StandardCharsets.UTF_8));

            StringBuilder hexString = new StringBuilder();
            for (byte b : hashBytes) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1)
                    hexString.append('0');
                hexString.append(hex);
            }

            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }


}
