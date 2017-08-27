package cn.itcast.bos.web.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletOutputStream;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.itcast.bos.domain.Region;
import cn.itcast.bos.domain.Subarea;
import cn.itcast.bos.service.SubareaService;
import cn.itcast.bos.utils.FileUtils;
import cn.itcast.bos.web.action.base.BaseAction;

@Controller
@Scope("prototype")
public class SubareaAction extends BaseAction<Subarea> {
	private static final long serialVersionUID = 1L;

	@Autowired
	private SubareaService subareaService;

	// 保存分区
	public String addSubarea() {
		subareaService.save(model);
		return LIST;
	}

	// 分区下拉列表(未关联定区)
	public String listajax() {
		List<Subarea> subareaList = subareaService.findListNotAssociation();
		Java2Json(subareaList, new String[] { "region", "decidedzone" });
		return NONE;
	}

	// 区域分页查询
	public String pageQuery() {
		DetachedCriteria dc = pageBean.getDetachedCriteria();
		String addresskey = model.getAddresskey();
		if (StringUtils.isNotBlank(addresskey)) {
			dc.add(Restrictions.like("addresskey", "%" + addresskey + "%"));
		}
		Region region = model.getRegion();
		if (region != null) {
			String province = region.getProvince();
			String city = region.getCity();
			String district = region.getDistrict();
			// 多表查询,设置别名
			dc.createAlias("region", "r");
			if (StringUtils.isNotBlank(province)) {
				dc.add(Restrictions.like("r.province", "%" + province + "%"));
			}
			if (StringUtils.isNotBlank(city)) {
				dc.add(Restrictions.like("r.city", "%" + city + "%"));
			}
			if (StringUtils.isNotBlank(district)) {
				dc.add(Restrictions.like("r.district", "%" + district + "%"));
			}
		}

		subareaService.pageQuery(pageBean);
		Java2Json(pageBean, new String[] { "currentPage", "detachedCriteria", "pageSize", "decidedzone", "subareas" });
		return NONE;
	}

	// 导出数据(Excel格式)
	@SuppressWarnings("resource")
	public String exportXls() throws IOException {
		// 查询要导出的数据
		List<Subarea> subareas = subareaService.findAll();
		// 创建Excel表格对象并封装数据
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet("分区数据");
		HSSFRow headRow = sheet.createRow(0);
		headRow.createCell(0).setCellValue("分区编号");
		headRow.createCell(1).setCellValue("开始编号");
		headRow.createCell(2).setCellValue("结束编号");
		headRow.createCell(3).setCellValue("位置信息");
		headRow.createCell(4).setCellValue("省市区");

		for (Subarea subarea : subareas) {
			HSSFRow dataRow = sheet.createRow(sheet.getLastRowNum() + 1);
			dataRow.createCell(0).setCellValue(subarea.getId());
			dataRow.createCell(1).setCellValue(subarea.getStartnum());
			dataRow.createCell(2).setCellValue(subarea.getEndnum());
			dataRow.createCell(3).setCellValue(subarea.getPosition());
			dataRow.createCell(4).setCellValue(subarea.getRegion().getName());
		}

		// 通过流对象回传页面下载
		String filename = "分区数据.xls";
		// 设置相应头信息并解决文件名中文不显示问题
		String header = ServletActionContext.getRequest().getHeader("User-Agent");
		filename = FileUtils.encodeDownloadFilename(filename, header);
		ServletActionContext.getResponse().setHeader("content-disposition", "attachment;filename=" + filename);
		// 设置响应数据类型为Excel文件类型
		String contentType = ServletActionContext.getServletContext().getMimeType(filename);
		ServletActionContext.getResponse().setContentType(contentType);
		// 写入流对象中
		ServletOutputStream os = ServletActionContext.getResponse().getOutputStream();
		workbook.write(os);
		return NONE;
	}

	private Integer decidedzoneId;

	// 根据定区ID查询关联的分区数据
	public String findListByDecidedzoneId() {
		List<Subarea> subarealist = subareaService.findListByDecidedzoneId(decidedzoneId);
		Java2Json(subarealist, new String[] { "decidedzone", "subareas" });
		return NONE;
	}

	public void setDecidedzoneId(Integer decidedzoneId) {
		this.decidedzoneId = decidedzoneId;
	}
}
