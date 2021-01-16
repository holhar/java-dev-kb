package de.holhar.java_dev_kb.training.ocp8.ch01_advanced_class_design.sec07_nested_classes.subsec01_member_inner_classes;

import static de.holhar.java_dev_kb.training.ocp8.utils.OcpPrepUtils.println;

public class A {
    private int x = 10;

    public static void main(String[] args) {
        A a = new A();
        A.B b = a.new B();
        A.B.C c = b.new C();
        c.allTheX();
    }

    class B {
        private int x = 20;

        class C {
            private int x = 30;

            public void allTheX() {
                println(x);
                println(this.x);
                println(B.this.x);
                println(A.this.x);
                // This does not work:
                //print(B.x);
            }
        }
    }
}
