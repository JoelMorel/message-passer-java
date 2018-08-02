import java.io.*;

class ThreadC extends Thread
{
    PipedOutputStream toA;

    PipedInputStream fromA;
    PipedInputStream fromB;

    ObjectOutputStream writerC;

    ThreadC() throws IOException {
        toA = new PipedOutputStream();

        fromA = new PipedInputStream();
        fromB = new PipedInputStream();
    }

    ThreadC(PipedOutputStream outA,
       PipedInputStream inA, PipedInputStream inB,
       ObjectOutputStream write) {
        toA = outA;
        fromA = inA;
        fromB = inB;
        writerC = write;
    }

    public void run() {
        System.out.println("C- Started");
        try {
            //OUTGOING
            //TC sends objects to TA
            Message m = new Message();
            m.theMessage = "Hi A!";
            String[] arr = {"It's", "C!"};
            m.someLines = arr;
            m.someNumber = 33;

            System.out.println("C- Sending object to A { " + '\n' + m.toString());
            writerC = new ObjectOutputStream(toA);//conversion
            writerC.writeObject(m);
            writerC.flush();
            writerC.close();


            //INCOMING
            //TC receives primitive data from TA
            System.out.println("C- Receiving primitive from A: " + fromA.read());
            fromA.close();

            //TC receives primitive data from TB
            System.out.println("C- Receiving primitive from B: " + fromB.read());
            fromB.close();
        }

        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}