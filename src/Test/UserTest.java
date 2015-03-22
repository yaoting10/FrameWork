import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

/**
 * Created with Test
 * User : Ting.Yao
 * Date : 2014/12/11.
 */
@RunWith(MockitoJUnitRunner.class)
public class UserTest {

    ExportService exportService = new ExportServiceImpl();

    @Test
    public void testSave(){
        InputStream in = null;
        try {
            in = new FileInputStream("E:/Users.csv");
            InputStreamReader reader = new InputStreamReader(in);

            List<UserExportVo> userExportVoList = exportService.read(reader);
            System.out.println(userExportVoList);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

}
