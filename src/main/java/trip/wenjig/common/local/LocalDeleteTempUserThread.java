package trip.wenjig.common.local;

import trip.wenjig.entity.TempUser;
import trip.wenjig.service.UserService;
import trip.wenjig.util.SystemDateFormat;

import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class LocalDeleteTempUserThread extends Thread {

    // spring 自己启动多线程环境下自动注入会失败，必须手动注入bean
    private UserService userService;

    private ScheduledExecutorService scheduledThreadPool;
    private ArrayList<TempUser> dbAllTempUserList;
    private ArrayList<TempUser> pastDueTempUserList;

    public LocalDeleteTempUserThread() {
        this.userService = (UserService) SpringUtil.getBean(UserService.class);
    }

    @Override
    public void run() {
        // 开启线程池
        scheduledThreadPool = Executors.newScheduledThreadPool(20);
        scheduledThreadPool.scheduleAtFixedRate(() -> {
            Thread.currentThread().setName("DELETE_TEMP_USER_定时删除任务");
            try {
                System.out.println("DELETE_TEMP_USER_定时删除任务:***********************************************************开始检查过期临时用户*****************************************************************");
                dbAllTempUserList = userService.findAll();
                if (dbAllTempUserList != null && dbAllTempUserList.size() > 0) {
                    pastDueTempUserList = new ArrayList<TempUser>();
                    for (int i = 0; i < dbAllTempUserList.size(); i++) {
                        String dbTime = dbAllTempUserList.get(i).getLoseEfficacyTime();
                        String sysTime = SystemDateFormat.getSystemPreciseDate();
                        if (SystemDateFormat.isDateLess(dbTime, sysTime)) {
                            pastDueTempUserList.add(dbAllTempUserList.get(i));
                        }
                    }
                }
                if (pastDueTempUserList != null && pastDueTempUserList.size() > 0) {
                    deleteList(pastDueTempUserList);
                    System.out.println("DELETE_TEMP_USER_定时删除任务:***********************************************************已经删除过期临时用户*****************************************************************");
                    dbAllTempUserList = null;
                    pastDueTempUserList = null;
                }
            }catch (Exception e) {
                e.printStackTrace();
            }

        }, 0, 5, TimeUnit.MINUTES);
    }

    public void addThread(TempUser tempUser) {
        scheduledThreadPool.schedule(() -> {
            Thread.currentThread().setName("DELETE_TEMP_USER_指定删除任务");
            delete(tempUser);
            System.out.println("DELETE_TEMP_USER_指定删除任务:***********************************************************已经删除过期临时用户 :" + tempUser.getUserName() + " *****************************************************************");
        },5,TimeUnit.MINUTES);
    }

    private void deleteList(ArrayList<TempUser> pastDueTempUserList) {
        userService.deleteTempUserList(pastDueTempUserList);
    }

    private synchronized void delete(TempUser tempUser) {
        userService.deleteTempUser(tempUser);
    }
}
