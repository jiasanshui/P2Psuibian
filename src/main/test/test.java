import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * className:test
 * discription:
 * author:jiasanshui
 * createTime:2018-12-15 18:47
 */
public class test {

    /**
     * 当前月份添加1；
     */
    @Test
    public void test(){
        Calendar calendar = Calendar.getInstance();

        calendar.add(Calendar.MONTH, 1);//增加一个月
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String format = simpleDateFormat.format(calendar.getTime());
        System.out.println("增加月份后的日期："+format);
    }
}
