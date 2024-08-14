public class Module {
    private double marks[] = new double[3];
    static double average;



    public String calculateModuleGrade() {
        if (average >= 80) {
             return  "Distinction";
        } else if (average >= 70) {
            return "Merit";
        } else if (average >= 40) {
            return "pass";
        } else {
            return "Fail";
        }
    }

    public double calculateTotal() {

        double total = 0;
        for (int j = 0; j < marks.length; j++) {
            total += marks[j];
        }
        return total;
    }

    public double calculateAverage() {
        double total = 0;
        for (int j = 0; j < marks.length; j++) {
            total += marks[j];
        }
        average = total / marks.length;
        return average;

    }

    public void setMarks(double marks1,double marks2,double marks3){
        this.marks[0]=marks1;
        this.marks[1]=marks2;
        this.marks[2]=marks3;
    }


    public double[] getMarks() {
        return marks;
    }




}
