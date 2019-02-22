package work.interview.design;

public class SingleTon {

    private volatile static SingleTon instance;

    private SingleTon() {

    }

    public static SingleTon getInstance() {
        if (instance == null) {
            synchronized (SingleTon.class) {
                if (instance == null) {
                    instance = new SingleTon();
                }
            }
        }
        return instance;
    }
}
