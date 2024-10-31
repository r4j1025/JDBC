import java.sql.*;

public class JDBC_DAO {
    public static void main(String[] args) {
        StudentDAO dao=new StudentDAO();
        dao.connect();
        dao.printTable();
    }
}
//Student data structure
 class Student{
    int rollno;
    String name;
}
//DAO for student
class  StudentDAO{
    Connection con=null;

    public void connect() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con= DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","Raj@mysql1");
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public void printTable(){
        String query="select * from students2";
        try{
            Statement st=con.createStatement();
            ResultSet rs= st.executeQuery(query);
            while (rs.next()){
                System.out.println(rs.getInt("rollno")+" : "+rs.getString("name"));
            }
        }catch (Exception e){
            System.out.println(e);
        }
    }

    Student getStudentByRollno(int rollno){
        String query="select name from students2 where rollno="+rollno;
        Student s=new Student();
        s.rollno=rollno;
        try {
            Statement st= con.createStatement();
            ResultSet rs= st.executeQuery(query);
            rs.next();
            s.name=rs.getString("name");
            return s;
        }catch (Exception e){
            System.out.println(e);
        }
        return s;
    }
    void insertStudent(int rollno,String name){
        String query = "insert into students2 values (?,?)";
        try {
            PreparedStatement pst=con.prepareStatement(query);
            pst.setInt(1,rollno);
            pst.setString(2,name);
            pst.executeUpdate();
        }catch (Exception e){
            System.out.println(e);
        }
    }
}
