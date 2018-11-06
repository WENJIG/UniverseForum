package trip.wenjig.common.local;

public enum NewThread {

    DELETE_TEMP_USER, SEND_MAIL, NOVEL_AUTO_POST;

    private LocalDeleteTempUserThread localDeleteTempUserThread;
    private LocalSendMailThread localSendMailThread;
    private AutoPostNovelThreadCorePool autoPostNovelThreadCorePool;

    NewThread() {
        localDeleteTempUserThread = new LocalDeleteTempUserThread();
        localSendMailThread = new LocalSendMailThread();
        autoPostNovelThreadCorePool = new AutoPostNovelThreadCorePool();
    }

    public LocalDeleteTempUserThread getLocalDeleteTempUserThread() {
        return localDeleteTempUserThread;
    }

    public LocalSendMailThread getLocalSendMailThread() { return localSendMailThread; }

    public AutoPostNovelThreadCorePool AutoPostNovelThreadCorePool() { return autoPostNovelThreadCorePool; }

}
