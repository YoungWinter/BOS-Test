package cn.itcast.bos.web.action;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.itcast.bos.domain.Region;
import cn.itcast.bos.service.RegionService;
import cn.itcast.bos.utils.PinYin4jUtils;
import cn.itcast.bos.web.action.base.BaseAction;

@Controller
@Scope("prototype")
public class RegionAction extends BaseAction<Region> {

	private static final long serialVersionUID = 1L;

	@Autowired
	private RegionService regionService;

	// 属性驱动(文件)
	private File regionFile;

	// 文件上传
	@SuppressWarnings("resource")
	public String importXls() throws Exception {
		List<Region> regionList = new ArrayList<Region>();
		HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(regionFile));
		HSSFSheet hssfSheet = workbook.getSheet("Sheet1");
		for (Row row : hssfSheet) {
			if (0 == row.getRowNum()) {
				continue;
			}
			String codeId = row.getCell(0).getStringCellValue();
			String province = row.getCell(1).getStringCellValue();
			String city = row.getCell(2).getStringCellValue();
			String district = row.getCell(3).getStringCellValue();
			String postcode = row.getCell(4).getStringCellValue();

			province = province.substring(0, province.length() - 1);
			city = city.substring(0, city.length() - 1);
			district = district.substring(0, district.length() - 1);

			String info = province + city + district;

			String shortcode = StringUtils.join(PinYin4jUtils.getHeadByString(info));
			String citycode = PinYin4jUtils.hanziToPinyin(city, "");

			Region region = new Region(codeId, province, city, district, postcode, shortcode, citycode, null);
			regionList.add(region);
		}
		regionService.saveBatch(regionList);
		return NONE;
	}

	// 区域分页查询
	public String pageQuery() {
		// 查询数据
		regionService.pageQuery(pageBean);
		Java2Json(pageBean, new String[] { "currentPage", "detachedCriteria", "pageSize", "subareas" });
		return NONE;
	}

	// 异步加载区域列表数据
	public String listajax() {
		List<Region> regionList = null;
		regionList = regionService.findAll();
		Java2Json(regionList, new String[] { "subareas" });
		return NONE;
	}

	public void setRegionFile(File regionFile) {
		this.regionFile = regionFile;
	}

}
