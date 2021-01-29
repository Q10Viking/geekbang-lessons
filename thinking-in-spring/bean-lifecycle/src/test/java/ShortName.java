import java.beans.Introspector;

public class ShortName {
    public static void main(String[] args) {
        System.out.println(Introspector.decapitalize("ApplicationContext"));
        System.out.println(Introspector.decapitalize("APplicationContext"));
    }
}
