package dev_tp2;


import java.io.ObjectOutputStream;

import java.io.RandomAccessFile;

import java.util.List;

import java.io.FileInputStream;

import java.io.FileOutputStream;

import java.io.IOException;

import java.io.ObjectInputStream;

public class class2 {
    
    
    
    public void DirectDat(){

        Employe[] employee_info ;
        
        try{

            FileInputStream fileIn = new FileInputStream("emp.dat");

            ObjectInputStream in = new ObjectInputStream(fileIn);

            employee_info = (Employe[]) in.readObject(); 

            in.close();

            fileIn.close();
            
            FileOutputStream file = new FileOutputStream("empdirect.dat");


            RandomAccessFile RandomAccessFile = new RandomAccessFile("empdirect.dat", "rw");
            
           for (int i = 0; i < employee_info.length; i++) {

                RandomAccessFile.writeUTF("employee SSN: " + employee_info[i].SSN);

                RandomAccessFile.writeUTF("employee number: " + employee_info[i].number);

                RandomAccessFile.writeUTF("employee name: " + employee_info[i].name);

                RandomAccessFile.writeUTF("employee address: " + employee_info[i].address);
                
            }
            file.close();
        }
        
        catch (IOException h) {


            h.printStackTrace();
            return;
        } 
        
        catch (ClassNotFoundException k) {

            
           System.out.println("Employee class not found");

           k.printStackTrace();

           return;
        }
    }
}
