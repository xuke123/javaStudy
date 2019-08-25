package thread.chapter17;

/**
 * 添加描述
 *
 * @author kekex.xu
 * @since 9:48 PM 8/4/19
 */
public interface ReadWriteLock {
    Lock readLock();

    Lock wirteLock();

    int getWritingWrites();

    int getWaitingWriters();

    int getReadingReaders();

    static ReadWriteLock readWriteLock(){
        return new ReadWriteLockImpl();
    }
    static ReadWriteLock readWriteLock(boolean preferWriter){
        return new ReadWriteLockImpl(preferWriter);
    }
}
