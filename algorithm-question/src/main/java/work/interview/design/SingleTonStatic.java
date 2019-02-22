package work.interview.design;

public class SingleTonStatic {


    private SingleTon() {

    }

    public static SingleTonStatic getSingleTon() {
        return InnerSingleTon.instance;
    }

    private static class InnerSingleTon {
        private static final SingleTonStatic instance = new SingleTonStatic();
    }
}
