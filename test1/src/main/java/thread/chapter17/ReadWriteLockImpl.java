package thread.chapter17;

/**
 * 添加描述
 *
 * @author kekex.xu
 * @since 9:53 PM 8/4/19
 */
public class ReadWriteLockImpl implements ReadWriteLock{
    private final Object MUTEX = new Object();

private int writingWriters = 0;
private  int waitingWriters =0;
private int readingReaders =0;
private boolean preferWriter;

public ReadWriteLockImpl(){
    this(true);
}

public ReadWriteLockImpl(boolean preferWriter){
    this.preferWriter = preferWriter;
}
    @Override
    public Lock readLock() {
        return null;
    }

    @Override
    public Lock wirteLock() {
        return null;
    }

    @Override
    public int getWritingWrites() {
        return 0;
    }

    @Override
    public int getWaitingWriters() {
        return 0;
    }

    @Override
    public int getReadingReaders() {
        return 0;
    }
}
