package toystopinventorymanagementsystem;

import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import javax.swing.JOptionPane;

/**
 *
 * @author Fahad Satti
 */
public class ToyStopInventoryManagementSystem {
    ToyStopService tsService = new ToyStopService();
    public void init(){
        tsService.initEmployees();
        tsService.initStores();
        tsService.initToys();
        System.out.println("Init complete");
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in); 
        int choice = 0;
        ToyStopInventoryManagementSystem tsims = new ToyStopInventoryManagementSystem();
        for(int i = 0; i < 60; i++){
            if(i == 0){
                //load previous data if present
                tsims.loadData();
                tsims.init();
            }
            /*
            //after a week initializing the ToyStopInverntory with additional resources for profiling
            if(i%7 == 0 ){
                tsims.init();
                tsims.saveData();//after initializing saving the searilized data into a file
            }
            */
            tsims.showMenu();
            choice = in.nextInt();
            if(choice == 1){
                tsims.printAll();
            }
            else if(choice == 2){
                //add new store
                tsims.addStore();
            }
            else if(choice == 3){
                //add new Employee
                tsims.addEmp();
            }
            else if(choice == 4){
                //add new Toy
                tsims.addToy();
            }
            else if(choice == 0){
                //save the state to file
                tsims.saveData();
            }
            else if(choice == -1){
                break;
            }
            else{
                System.out.println("Invalid Choice!\n");
            }
        }
    }

    private void loadData() {
        //throw new UnsupportedOperationException("Not supported yet."); 
        //To change body of generated methods, choose Tools | Templates.
        try {
            System.out.println("Loading Searilized Data............!\n");
            FileInputStream fileIn = new FileInputStream("back.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            tsService = (ToyStopService) in.readObject();
            in.close();
            fileIn.close();
            System.out.println("Loading done.\n");
        }catch (FileNotFoundException e){
            System.out.println("Serilization file not found!");
        }catch(IOException i) {
            i.printStackTrace();
            return;
        }catch(ClassNotFoundException c) {
            System.out.println("ToyStopService class not found\n");
            c.printStackTrace();
            return;
        }
    }
    private void saveData(){
        //throw new UnsupportedOperationException("Not supported yet.");
        try {
            System.out.println("Storing Data.............");
            FileOutputStream fileOut = new FileOutputStream("back.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(tsService);
            out.close();
            fileOut.close();
            System.out.printf("Serialized data is saved in back.ser\n");
        }catch(IOException i) {
            i.printStackTrace();
        }
    }
    private void showMenu() {
        System.out.println("\nWelcome to Toy Stop Inventory Management System");
        System.out.println("Enter 1 to show all data");
        System.out.println("Enter 2 to add a new Store");
        System.out.println("Enter 3 to add a new Employee");
        System.out.println("Enter 4 to add a new Toy");
        System.out.println("Enter 0 to save state");
        System.out.println("--------------------------------------------------\n");
    }

    private void printAll() {
        System.out.println(this.tsService.stores);
    }
    
    private void addStore(){
        int id = tsService.addStore();
        System.out.println("Store added at: "+id+"\n");
    }
    
    private void addEmp(){
        int id = tsService.addEmployee();
        System.out.println("Employee added at: "+id+"\n");
    }
    
    private void addToy(){
        int id = tsService.addToy();
        System.out.println("Toy added at: "+id+"\n");
    }
}
