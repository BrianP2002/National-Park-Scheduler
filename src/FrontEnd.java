// --== CS400 File Header Information ==--
// Name: Lin Ha
// Email: lha2@wisc.edu
// Notes to Grader:

import java.util.Scanner;

public class FrontEnd {

    private Scanner readIn;
    protected BackEnd backEnd;
    private int counter;

    protected enum OutputStatus{
        ABLED, DISABLED
    }

    protected OutputStatus outputStatus;

    /**
     * The method that create a new instantiate of FrontEnd class;
     */
    public FrontEnd(String status) {
        if(status.equals("ABLED")){
            this.outputStatus = OutputStatus.ABLED;
        }
        else{
            this.outputStatus = OutputStatus.DISABLED;
        }
        this.readIn = new Scanner(System.in);
        this.backEnd = new BackEnd(this);
        this.counter = 0;
    }

    /**
     * Print out the given message with the line counter.
     * 
     * @param msg the given message
     */
    public void printNextLine() {
        if(this.outputStatus == OutputStatus.DISABLED){
            return;
        }
        System.out.println();
        this.counter += 1;
        this.printCounter();
    }

    /**
     * Print the given message.
     * 
     * @param msg the given message.
     */
    public void printSystemMessage(String msg) {
        if(this.outputStatus == OutputStatus.DISABLED){
            return;
        }
	    System.out.print(msg);
    }

    private void printCounter() {
        if(this.outputStatus == OutputStatus.DISABLED){
            return;
        }
	    System.out.print("[" + this.counter + "]");
    }

    private void recieveInput() {
        String readInData;
        while (this.backEnd.systemStatus != BackEnd.SystemStatus.TERMINATED) {
            if (this.backEnd.systemStatus != BackEnd.SystemStatus.RUNNING) {
            continue;
            }
            this.printNextLine();
            readInData = this.readIn.nextLine();
            this.backEnd.processInput(readInData);
        }
    }

    public static void main(String args[]) {
        FrontEnd mainEnd = new FrontEnd("ABLED");
        mainEnd.recieveInput();
        mainEnd.printSystemMessage(Text.END_WORDS);
    }
}
