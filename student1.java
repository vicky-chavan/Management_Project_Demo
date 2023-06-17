package student_1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class student1 {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/studentdb","root","vikram143");
		
		Scanner sc=new Scanner(System.in);
		
		while(true)
		{
			System.out.println("1.Insert students 2.Update student data 3.Delete Student data 4.Get student data 5.Exit");
			
			int a=sc.nextInt();
			
			switch(a)
			{
			case 1:
			{
				System.out.println("How many students you want to add :");
				
				int start=1;
				
				int num=sc.nextInt();
				if(num<=0)
				{
					System.out.println("enter valid number");
				}
				while(start<=num)
				{
					System.out.println("enter the student "+num+" id number : ");
					String id=sc.next();
					System.out.println("name : ");
					String name=sc.next();
					
					PreparedStatement ps=con.prepareStatement("Insert into student1 values(?,?)");
					ps.setString(1,id);
					ps.setString(2,name );
					ps.executeUpdate();
					start++;
				}
				System.out.println("Data added successfully");
				break;
			}

			
			case 2:
			{
				System.out.println("which stud data you want to update please enter id :");
				String id=sc.next();
				System.out.println("enter the new name of student");
				String name=sc.next();
				
				PreparedStatement ps= con.prepareStatement("update student1 set name=? where id=?");
				ps.setString(1, name);
				ps.setString(2,id);
				
				ps.executeUpdate();
				break;
			}
			case 3:
			{
				System.out.println("enter the student id that you want to remove : ");
				String id=sc.next();
				PreparedStatement ps=con.prepareStatement("delete from student1 where id=?");
				ps.setString(1, id);
				ps.executeUpdate();
				System.out.println("removed...");
				break;
			}
			
			case 4:
			{
				PreparedStatement ps= con.prepareStatement("select * from student1");
				ResultSet rs=ps.executeQuery();
				
				while(rs.next())
				{
					
					System.out.println(rs.getString("id"));
					System.out.println(rs.getString("name"));
					
					String s=rs.getString("name");
					if(rs.getString("name").equals("vikram"))
					{
						System.out.println("JDBC Done.. ");
					}
					
				}
				break;
				
				
			}
			case 5:
			{
				for(;;)
				{
				System.out.println("if you want to exit press y if dont press n");
				char c=sc.next().charAt(0);
				if(c=='y'||c=='Y')
				{
					System.out.println("thank you...");
					System.exit(0);
				}
				else if(c=='n'||c=='N')
				{
					con.close();
					break;
				}
				else
					System.out.println("enter valid input");
				}
				
			}
				
			}
		}		
		
	}

	
}
