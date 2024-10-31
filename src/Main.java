import java.sql.*;

public class Main {
    public static void main(String[] args)throws Exception {

        String url="jdbc:mysql://localhost:3306/test";
        String uname="root";
        String pass="Raj@mysql1";
        //////////////////////////////////
        // scheme : test
        // example table: students
        //  userid      name
        //  1           Raj
        //  2           Narayanan
        //  3           Kathik
        //  4           Arun
        //////////////////////////////////

        String Query1="select name from students where userid=3";
        String Query2="select * from students";
        String Query3="insert into students (userid,name) values(6,'Ram');";
        String Query4="insert into students values(?,?)";//?-parameter

        Class.forName("com.mysql.jdbc.Driver");
        Connection con= DriverManager.getConnection(url,uname,pass);
        Statement st = con.createStatement();
        ///////////////////////////////////////////
        //st.executeQuery(Query1); -> this will give result in tabular structure so use ResultSet to seperate the reql data from the table
        //ResultSet rs= st.executeQuery(Query1);
        //rs.next();//in the beginning pointer will be at the top pointing to null, so step down the pointer
        //String value= rs.getString("name");
        //System.out.println(value);
        ////////////////////////////////////////////
        //st.executeUpdate(Query3);//this return no of affected rows

        ResultSet rs=st.executeQuery(Query2);

        //PreparedStatement pst=con.prepareStatement(Query4);
        //pst.setInt(1,7);//1-parameter index
        //pst.setString(2,"Bharath");
        //pst.executeUpdate();

        while(rs.next()){
            System.out.print(rs.getInt("userid"));
            System.out.print(" : ");
            System.out.println(rs.getString("name"));
        }

        st.close();
        con.close();
    }
}
