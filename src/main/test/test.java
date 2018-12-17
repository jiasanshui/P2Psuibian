import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * className:test
 * discription:
 * author:fhm
 * createTime:2018-12-16 16:00
 */
public class test {

    @Test
    public void test(){
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH,1);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String format = simpleDateFormat.format(calendar.getTime());
        System.out.println(format);
    }
}
