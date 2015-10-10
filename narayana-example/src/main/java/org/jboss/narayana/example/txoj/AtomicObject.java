package org.jboss.narayana.example.txoj;

import com.arjuna.ats.arjuna.AtomicAction;
import com.arjuna.ats.txoj.Lock;
import com.arjuna.ats.txoj.LockManager;
import com.arjuna.ats.txoj.LockMode;
import com.arjuna.ats.txoj.LockResult;

public class AtomicObject extends LockManager {
    
    static {
        System.setProperty("java.util.logging.manager", "org.jboss.logmanager.LogManager");
    }
    
    private int state;
    
    public AtomicObject() throws Exception {
        super();
        
        state = 0;
        
        if (setlock(new Lock(LockMode.WRITE), 0) == LockResult.GRANTED)  {
            System.out.println("Created persistent object " + get_uid());
        } else {
            throw new Exception("setlock error.");
        }
    }
    
    public void incr(int value) throws Exception {

        if (setlock(new Lock(LockMode.WRITE), 0) == LockResult.GRANTED) {
            state += value;
            return;
        } else {
            throw new Exception("Write lock error.");
        }
    }
    
    public void set(int value) throws Exception {
        if (setlock(new Lock(LockMode.WRITE), 0) == LockResult.GRANTED) {
            state = value;
            return;
        } else {
            throw new Exception("Write lock error.");
        }
    }
    
    public int get() throws Exception {
        if (setlock(new Lock(LockMode.READ), 0) == LockResult.GRANTED) {
            return state;
        } else {
            throw new Exception("Read lock error.");
        }
    }
    
    public static void main(String[] args) throws Exception {
        
        AtomicAction a = new AtomicAction();
        a.begin();
        
        AtomicObject obj = new AtomicObject();
        obj.set(1234);
        a.commit();
        
        a = new AtomicAction();
        a.begin();
        try {
            if (obj.get() != 1234) {
                throw new RuntimeException("The object was not set to 1234");
            }
        } finally {
            a.commit();
        }
        
        a = new AtomicAction();
        a.begin();
        obj.incr(1);
        a.abort();
    }

}
