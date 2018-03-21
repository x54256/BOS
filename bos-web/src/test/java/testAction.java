import cn.x5456.bos.dao.IFunctionDao;
import cn.x5456.bos.domain.Function;
import cn.x5456.crmClient.Customer;
import cn.x5456.crmClient.ICustomerService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;


@RunWith(SpringJUnit4ClassRunner.class)     // 创建容器
@ContextConfiguration("classpath:applicationContext.xml")    // 通过类加载形式，读取配置文件，创建对象
public class testAction {

    @Autowired
    private IFunctionDao functionDao;

    @Test
    public void func() throws Exception {

        List<Function> list = functionDao.findMenuByUserId("ff80818162432f2b01624334ade60000");
        for (Function f : list) {
            System.out.println(f.getName());
        }

    }
}