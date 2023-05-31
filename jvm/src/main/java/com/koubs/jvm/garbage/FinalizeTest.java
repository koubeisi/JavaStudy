package com.koubs.jvm.garbage;

/**
 * @author KouBeisi
 * @since 2021/10/21
 */
public class FinalizeTest {


    private static FinalizeTest ft;

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        ft = this;
    }

    public static void main(String[] args) throws InterruptedException {

        ft = new FinalizeTest();

        ft = null;
        System.gc();

        Thread.sleep(2000);

        if (ft == null){
            System.out.println("obj is dead!");
        } else {
            System.out.println("obj is alive");
        }

        ft = null;
        System.gc();

        Thread.sleep(2000);

        if (ft == null){
            System.out.println("obj is dead!");
        } else {
            System.out.println("obj is alive");
        }

    }
}
