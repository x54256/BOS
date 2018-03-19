package cn.x5456.bos.service.impl;

import cn.x5456.bos.dao.IDecidedzoneDao;
import cn.x5456.bos.dao.INoticebillDao;
import cn.x5456.bos.dao.IStaffDao;
import cn.x5456.bos.dao.IWorkbillDao;
import cn.x5456.bos.domain.*;
import cn.x5456.bos.service.INoticebillService;
import cn.x5456.crmClient.ICustomerService;
import com.opensymphony.xwork2.ActionContext;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;

@Service
@Transactional
public class NoticebillServiceImpl implements INoticebillService {

    @Autowired
    private ICustomerService proxy;

    @Autowired
    private INoticebillDao noticebillDao;

    @Autowired
    private IDecidedzoneDao decidedzoneDao;

    @Autowired
    private IWorkbillDao workbillDao;


    /**
     * 保存业务通知单，还有尝试自动分单
     */
    @Override
    public void save(Noticebill model) {

        // 1.获取当前用户对象
        TUser user = (TUser) ActionContext.getContext().getSession().get("loginUser");
        // 2.注入model中
        model.setUser(user);
        // 3.保存到用户表
        noticebillDao.save(model);
        // 4.调用CRM中的方法获取定区id，实现自动分单
        String decidedzoneId = proxy.findDecidedzoneIdByAddress(model.getPickaddress());
        // 5.判断客户是否关联了定区，关联了 ==> 查出定区的邮递员set进去，并设置分单类型
        // 并生成取派员的 工单
        if (StringUtils.isNotBlank(decidedzoneId)) {
            // 获取取派员的信息
            Decidedzone dd = decidedzoneDao.findById(decidedzoneId);
            Staff staff = dd.getStaff();
            // 设置取派员+分单类型
            model.setStaff(staff);
            model.setOrdertype(Noticebill.ORDERTYPE_AUTO);
            // 生成工单
            Workbill workbill = new Workbill();

            // 添加元素
            workbill.setNoticebill(model);
            workbill.setStaff(staff);
            workbill.setType(Workbill.TYPE_1);
            workbill.setPickstate(Workbill.PICKSTATE_NO);
            workbill.setBuildtime(new Timestamp(System.currentTimeMillis()));
            workbill.setRemark(model.getRemark());

            // 保存
            workbillDao.save(workbill);


        } else {
            model.setOrdertype(Noticebill.ORDERTYPE_MAN);
        }
    }
}
