package trip.wenjig.common.local;

import trip.wenjig.util.SendMailCode;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class LocalSendMailThread extends Thread {

    private ScheduledExecutorService scheduledThreadPool;

    public LocalSendMailThread() {
    }

    @Override
    public void run() {
        scheduledThreadPool = Executors.newScheduledThreadPool(20);
    }

    public void sendMail(String email, String userName, String verificationCode) {
        scheduledThreadPool.schedule(() -> {
            synchronized (SendMailCode.class) {
                try {
                    SendMailCode.sendMail(email, userName, verificationCode);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, 0, TimeUnit.SECONDS);
    }

    public void sendMail(String email, String verificationCode) {
        scheduledThreadPool.schedule(() -> {
            //synchronized (SendMailCode.class) {
                try {
                    SendMailCode.sendMail(email, verificationCode);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            //}
        }, 0, TimeUnit.SECONDS);
    }

}
