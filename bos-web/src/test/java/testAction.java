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
    private ICustomerService proxy;

    @Test
    public void func() throws Exception {

        List<Customer> customerList = proxy.findListNotAssociation();

        System.out.println(customerList);

    }

    @Test
    public void func2() throws Exception {

        List<Customer> customerList = proxy.findListHasAssociation("123");

        System.out.println(customerList);

    }
}