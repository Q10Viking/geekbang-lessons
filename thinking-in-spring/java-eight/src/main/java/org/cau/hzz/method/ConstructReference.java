package org.cau.hzz.method;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class ConstructReference {

    Supplier<Map> obj1 = () -> new HashMap();
    Supplier<Map> obj2 = HashMap::new;
}
