package Lab3;

import java.text.DecimalFormat;

public class GraduateStudent extends Student{
    private String supervisor;
    private boolean isPHD;
    private String undergraduateSchool;

    //----------------------------Constructor, set, get, and to string functions---------------------------------
    //constructor
    public GraduateStudent(String programOfStudent, int yearOfStudent, float studentAverageGrade, String supervisorOfStudent, boolean studentPHD, String sudentUndergraduateSchool){
        super(programOfStudent, yearOfStudent, studentAverageGrade);
        this.setSupervisor(supervisorOfStudent);
        this.setIsPHD(studentPHD);
        this.setUndergraduateSchool(sudentUndergraduateSchool);
    }
    
    //set and get for supervisor
    public String getSupervisor() {
        return supervisor;
    }
    public void setSupervisor(String supervisor) {
        this.supervisor = supervisor;
    }
    //set and get for isPHD
    public boolean getIsPHD() {
        return isPHD;
    }
    public void setIsPHD(boolean isPHD) {
        this.isPHD = isPHD;
    }
    //set and get for undergraduateSchool
    public String getUndergraduateSchool() {
        return undergraduateSchool;
    }
    public void setUndergraduateSchool(String undergraduateSchool) {
        this.undergraduateSchool = undergraduateSchool;
    }

    //toString for GraduateStudent class
    @Override
    public String toString() {
        DecimalFormat oneDecimal = new DecimalFormat();
        oneDecimal.setMaximumFractionDigits(1);
        if(isPHD == true){
            return "GradStudent Info: \nprogram: " + getProgram() + "\nYear: " + getYear() + "\nAverage Grade: " + oneDecimal.format(getAverageGrade()) + "%" 
            + "\nSupervisor: " + supervisor + "\nPhD \nUndergraduate School: " + undergraduateSchool;
        }
        else{
            return "GradStudent Info: \nprogram: " + getProgram() + "\nYear: " + getYear() + "\nAverage Grade: " + oneDecimal.format(getAverageGrade()) + "%" 
            + "\nSupervisor: " + supervisor + "\nMasters \nUndergraduate School: " + undergraduateSchool;
        }
        
    }
    //-------------------------End of constructor, set, get, and toString Functions------------------------------

    // //to string for writing to file in specified format
    // public static String writeGradStudent(GraduateStudent temp){
    //     DecimalFormat oneDecimal = new DecimalFormat();
    //     oneDecimal.setMaximumFractionDigits(1);
    //     return temp.getProgram() + " " + temp.getYear() + " " + oneDecimal.format(temp.getAverageGrade()) 
    //     + " " + temp.getSupervisor() + " " + temp.getIsPHD() + " " + temp.getUndergraduateSchool();
    // }
}
