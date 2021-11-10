package dev_tp2;

import java.io.RandomAccessFile;

import java.util.Scanner;


public class class3 {
    
    public void search_employee(){

           String employee_name = "";

           String employee_number = "";

           String employee_address = "";

           String employee_SSN = "";

           boolean Found = false;
        
        Scanner scanner = new Scanner(System.in);

        System.out.println("give me the number of the employe");
            int employee_number = scanner.nextInt();
        
        try{
            RandomAccessFile RandomAccessFile = new RandomAccessFile("empdirect.dat", "rw");

            RandomAccessFile.seek(0);
             
            while (RandomAccessFile.getFilePointer() < RandomAccessFile.length()) {


                employee_name    = RandomAccessFile.readUTF();
                employee_address = RandomAccessFile.readUTF();
                employee_SSN     = RandomAccessFile.readUTF();
                employee_number  = RandomAccessFile.readUTF();
                
                if(Integer.parseInt(employee_number.split(" ")[1]) == employee_number){
                    RandomAccessFile.seek(RandomAccessFile.length());
                    Found = true;
                }
            }
             
            if(Found){
                System.out.println("Employee Found");
                System.out.println(employee_name);
                System.out.println(employee_address);
                System.out.println(employee_SSN);
                System.out.println(employee_number);
            }else {
                System.out.println("Employee Not Found");
            }
        }catch(Exception e){
            
        }
       
        
    }
}
