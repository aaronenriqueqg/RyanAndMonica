/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ryanandmonicajob;

/**
 *
 * @author aaron
 */
public class RyanAndMonicaJob implements Runnable{

    /**
     * @param args the command line arguments
     */
    private  BankAccount account = new BankAccount();
    public static void main(String[] args) {
        // TODO code application logic here
        RyanAndMonicaJob theJob = new RyanAndMonicaJob();
        Thread one = new Thread(theJob);
        Thread two = new Thread(theJob);
        one.setName("Ryan");
        two.setName("Monica");
        one.start();
        two.start();
    }
    
    @Override
    public void run() {
        for(int i=0;i<10;i++){
            makeWithdrawl(10);
            if(account.getBalanced()<0){
                System.out.println("Overdrawn!");
            }
        }
    
    
    }
    public synchronized void  makeWithdrawl(int amount){
        if(account.getBalanced()>=amount){
            System.out.println(Thread.currentThread().getName()+ "  is about to make a withdraw");
            
            try{
                System.out.println(Thread.currentThread().getName()+ " is going to sleep");
                Thread.sleep(500);
            }catch(InterruptedException ex){
                    ex.printStackTrace();}
            System.out.println(Thread.currentThread().getName()+ " woke up");
            account.withdraw(amount);
            System.out.println(Thread.currentThread().getName()+ " completes the withdrawl");
        }else{
            System.out.println("Sorry, not enough for "+ Thread.currentThread().getName());
        }
    }
}
    class BankAccount{
    private int balance =100;
     public int getBalanced(){
         return balance;
     }
     public void withdraw(int amount){
         balance = balance-amount;
     }
    }
