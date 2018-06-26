package com.example.claud.myapplication.thread;

        import java.util.ArrayList;
        import java.util.List;

public class RecurrentThread implements Runnable {


    private List<ActionListener> listeners;
    private boolean running;
    private long millisSleep;

    public RecurrentThread(long millisSleep){
        running = true;
        this.millisSleep = millisSleep;
    }


    public void addListener(ActionListener l){
        if(listeners == null){
            listeners = new ArrayList<>();
        }
        listeners.add(l);
    }

    public void stop(){
        running = false;
    }

    private void fireEvent(){
        for(ActionListener a: listeners){
            a.actionPerformed();
        }
    }


    @Override
    public void run() {
        while(running){
            try {
                Thread.sleep(millisSleep);
                fireEvent();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
