package sample;

public class StudentRecord {
    private String SID;
    private float Midterm;
    private float Assignments;
    private float finalExam;
    private float finalMark;
    private String letterGrade;

    public StudentRecord(String SID, float Assignments, float Midterm, float finalExam) {
        this.SID = SID;
        this.Assignments = Assignments;
        this.Midterm = Midterm;
        this.finalExam = finalExam;
    }

    public String getSID() {
        return this.SID;
    }

    public float getAssignments() {
         return this.Assignments;
    }

    public float getMidterm() {
        return this.Midterm;
    }

    public float getFinalExam() {
        return this.finalExam;
    }

    public float getFinalMark() {
        this.finalMark = this.Assignments*(0.2f) + this.Midterm*(0.3f) + this.finalExam*(0.5f);
        return this.finalMark;
    }

    public String getLetterGrade() {
        if (this.finalMark >= 80 && this.finalMark <= 100){
            return "A";
        }

        else if (this.finalMark >= 70 && this.finalMark <= 79){
            return "B";
        }

        else if (this.finalMark >= 60 && this.finalMark <= 69){
            return "C";
        }

        else if (this.finalMark >= 50 && this.finalMark <= 59){
            return "D";
        }

        else{
            return "F";
        }
    }
}
