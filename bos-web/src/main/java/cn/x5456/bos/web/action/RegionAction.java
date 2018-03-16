package cn.x5456.bos.web.action;

import cn.x5456.bos.PinYin4jUtils;
import cn.x5456.bos.domain.Region;
import cn.x5456.bos.service.IRegionService;
import cn.x5456.bos.web.action.base.BaseAction;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

@Controller
@Scope(scopeName = "prototype")
public class RegionAction extends BaseAction<Region> {

    @Autowired
    private IRegionService regionService;

    private File regionFile;

    public void setRegionFile(File regionFile) {
        this.regionFile = regionFile;
    }

    public String importXls() throws Exception {

        List<Region> regions = new ArrayList<>();

        // 包装一个Excel文件对象
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook(new FileInputStream(regionFile));
        // 读取文件中第一个Sheet标签页（两种方式）
        // HSSFSheet sheet = hssfWorkbook.getSheetAt(0);
        HSSFSheet sheet = hssfWorkbook.getSheet("sheet1");

        for (Row row : sheet) {

            // 1.第一行为标题行；如果行号为1，跳出本次循环
            if (row.getRowNum() == 0) {
                continue;
            }

            // 2.获取每行的数据
            String id = row.getCell(0).getStringCellValue();
            String province = row.getCell(1).getStringCellValue();
            String city = row.getCell(2).getStringCellValue();
            String district = row.getCell(3).getStringCellValue();
            String postcode = row.getCell(4).getStringCellValue();
            // 3.封装到region对象中
            Region region = new Region(id, province, city, district, postcode, null, null, null);

            // 简码：河北省 石家庄市 新华区 ==> HBSJZXH
            // 1）将省 市 区 去掉
            province = province.substring(0, province.length() - 1);
            city = city.substring(0, city.length() - 1);
            district = district.substring(0, district.length() - 1);
            // 2）拼接成一个字符串，转成简码
            String info = province + city + district;
            String[] headByString = PinYin4jUtils.getHeadByString(info);
            String shortcode = StringUtils.join(headByString);

            // 城市编码：石家庄 ==> shijiazhuang
            String citycode = PinYin4jUtils.hanziToPinyin(city, "");

            // 4.封装简码和城市编码
            region.setShortcode(shortcode);
            region.setCitycode(citycode);

            // 5.将region放入集合中；如果包装一个调用一次service方法，就会开很多事务（开事务也要时间的）
            regions.add(region);
        }

        // 6.调用service层的方法，批量保存
        regionService.saveBatch(regions);

        return "none";  // easyui上传文件是模拟的ajax
    }


    public String pageQuery() throws Exception {

        // 3.调用service层方法执行查询
        regionService.pageQuery(pageBean);

        super.writeJson(pageBean, new String[]{"currentPage", "detachedCriteria", "pageSize"});

        return "none";
    }


    private String q;

    public void setQ(String q) {
        this.q = q;
    }

    public String listajax() throws Exception {

        List<Region> list = null;

        if (StringUtils.isNotBlank(q)) {
            list = regionService.listajaxByQ(q);
        } else {
            list = regionService.listajax();
        }

        super.writeJson(list, new String[]{"subareas"});

        return "none";
    }
}
