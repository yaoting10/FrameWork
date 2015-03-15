import com.my.Utils.JDBCUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * Created with Test
 * User : Ting.Yao
 * Date : 2014/12/11.
 */
@RunWith(MockitoJUnitRunner.class)
public class UserTest {

    @Test
    public void testSave(){
        String sql = "insert into t_user values (1, '1111', '测试', '1234', 0, 1, '5122')";
        JDBCUtils.execute(JDBCUtils.getConnection(), sql);
    }

}
