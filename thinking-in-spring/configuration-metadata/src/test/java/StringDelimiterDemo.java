import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class StringDelimiterDemo {
    public static void main(String[] args) {
        //  Spring StringUtils.tokenizeToStringArray
//        String[] nameArr = StringUtils.tokenizeToStringArray("hzz;Q10Viking", ",;");
//        System.out.println(nameArr);

        StringTokenizer st = new StringTokenizer("hzz,Q10Viking", ",;");
        List<String> tokens = new ArrayList<>();
        while(st.hasMoreTokens()){
            tokens.add(st.nextToken().trim());
        }
        //  返回String类型数组
        final String[] EMPTY_STRING = {};
        String[] res = tokens.toArray(EMPTY_STRING);
        //  [hzz, Q10Viking]
        System.out.println(Arrays.toString(res));
    }
}
