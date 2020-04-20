package pt.isel.leic.mpd.v1920.li41d.misc.defaultMethods;

public interface MyInterface {
    void toImplement1();

    default void implemented1() {
        System.out.println("implemented1 on MyInterface");
    }

    default void implemented2() {
        System.out.println("implemented2 on MyInterface");
    }
}


class MyInterfaceImpl1 implements MyInterface {

    @Override
    public void toImplement1() {

    }

    @Override
    public void implemented2() {
        System.out.println("implemented2 on MyIterfaceImpl1");
    }
}