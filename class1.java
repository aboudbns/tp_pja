package dev_tp2;

import java.io.*;

import java.util.Scanner;

import java.io.*;


public class class1 {
    
    public void Add_employee(){
        
    
        Scanner scan = new Scanner(System.in);
        
        System.out.println("give me the number of the employe:");
        
         int number_of_the_employes = scan.nextInt();
        
         Employe [] employee_info = new Employe[number_of_the_employes];
        
        for(int i = 0;i< employee_info.length ;i++){

                Scanner s = new Scanner(System.in);

                employee_info[i] = new Employe();

            System.out.println("please donnee information de employee " + (i++));


            
               System.out.println("give me the number of the employe:");
                   employee_info[i].number = s.nextInt();

               System.out.println("give me the name of the employe:");
                   employee_info[i].name = s.nextLine();

               System.out.println("give me the adresse of the employe:");
                   employee_info[i].address = s.nextLine();

               System.out.println("give me the ssn of the employe:");
                   employee_info[i].SSN = s.nextInt();

        
        }
        try{

            FileOutputStream fileOut = new FileOutputStream("emp.dat");

            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            
            out.writeObject(employee_info);
            
            out.close();
            
            fileOut.close();
         
            
        }catch(Exception e){
            System.out.println("Error build ficher");
        }
    }
}
