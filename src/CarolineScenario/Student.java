package CarolineScenario;

public class Student implements Comparable
{
    private String studentName;
    private int xPos;
    private int yPos;

    public Student(String name, int x, int y)
    {
        studentName = name;
        xPos = x;
        yPos = y;
    }

    public String getStudentName()
    {
        return studentName;

    }

    public int getxPos()
    {
        return xPos;
    }

    public int getyPos() {

        return yPos;
    }

    @Override
    public int compareTo(Object o)
    {
        if (o.getClass() == Student.class)
        {
            Student student = (Student)o;
            return this.studentName.compareToIgnoreCase(student.studentName);
        }
        return this.studentName.compareToIgnoreCase(o.toString());
    }
}
