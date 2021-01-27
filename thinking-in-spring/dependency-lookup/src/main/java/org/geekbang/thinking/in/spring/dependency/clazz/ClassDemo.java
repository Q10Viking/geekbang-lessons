package org.geekbang.thinking.in.spring.dependency.clazz;

import java.lang.*;

public class ClassDemo {

    public static void main(String[] args) {

        try {
            Class c = ClassDemo.class;

            // class object associated with BaseClass
            Class subClass = SubClass.class;

            // checks whether BaseClass is assignable from ClassDemo
            boolean retval = subClass.isAssignableFrom(subClass);
            System.out.println("Return Value = " + retval);

            // checks whether ClassDemo is assignable from BaseClass
            retval = c.isAssignableFrom(subClass);
            System.out.println("Return Value  = " + retval);
        } catch(Exception e) {
            System.out.println(e.toString());
        }
    }
}

// base class
class SubClass extends ClassDemo {
    public SubClass() {
        // no argument constructor
    }
}
