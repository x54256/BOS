import cn.x5456.bos.domain.Staff;
import cn.x5456.bos.service.IStaffService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)     // 创建容器
@ContextConfiguration("classpath:applicationContext.xml")    // 通过类加载形式，读取配置文件，创建对象
public class testAction {

    @Autowired
    private IStaffService staffService;

    @Test
    public void execute() throws Exception {

        Staff staff = new Staff();

        staff.setName("9652");

        staffService.save(staff);

    }
}