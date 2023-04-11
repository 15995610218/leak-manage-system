package com.whl.store.controller;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.annotation.ExcelCollection;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import com.whl.store.entity.ExportDate;
import com.whl.store.entity.KsLeak;
import com.whl.store.entity.PageData;
import com.whl.store.services.IKsleakService;
import com.whl.store.util.AllException;
import com.whl.store.util.JsonResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("leak")
@Slf4j
public class KsleakController extends AllException {
    /**
     * author：weihailong
     * 方法: 漏洞管理controller接口
     */

    @Autowired
    private IKsleakService iKsleakService;

    private final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @RequiresPermissions("auth:kunshanloudong")
    @RequestMapping("ksleak")
    public JsonResult<PageData> ksleakcontroller(int currentPage, int sizePage){
        PageData<KsLeak> pageData = iKsleakService.selectKsleakPageService(currentPage,sizePage);
        return new JsonResult<>(ok,pageData);
    }

    /**
     * 添加一条漏洞数据控制层
     * @param ksLeak
     * @return
     */
    @RequiresPermissions("auth:add")
    @RequestMapping("ksleakinsert")
    public JsonResult<Void> ksleakinsertcontroller(@RequestBody KsLeak ksLeak) {
        iKsleakService.insertksleakbyoneservice(ksLeak);
        return new JsonResult<>(ok);
    }

    /**
     * 根据ID修改一条漏洞数据
     * @param ksLeak
     * @return
     */
    @RequiresPermissions("auth:update")
    @RequestMapping("ksleakupdate")
    public JsonResult<Void> ksleakupdatecontroller(@RequestBody KsLeak ksLeak){
        iKsleakService.updateKsleakById(ksLeak);
        return new JsonResult<>(ok);
    }

    /**
     * 根据ID删除漏洞数据
     * @param ids
     * @return
     */
    @RequiresPermissions("auth:ksdelete")
    @RequestMapping("ksleakdelete")
    public JsonResult<Void> ksleakdeletecontroller(@RequestBody int[] ids){
        iKsleakService.deleteKsleakById(ids);
        return new JsonResult<>(ok);
    }

    /**
     *根据时间范围导出execl文件
     * @param
     * @param
     * @return
     */
    @RequiresPermissions("auth:export")
    @RequestMapping("ksleakexport")
    public void ksleakEexportExecl (HttpServletResponse response,@RequestBody ExportDate exportDate) throws IOException{
        //获取时间范围参数
        String beginDate = exportDate.getBeginDate();
        String andDate = exportDate.getAndDate();
        //根据时间范围查询数据
        List<KsLeak> ksLeaks = iKsleakService.exportExeclService(beginDate,andDate);
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("漏洞报告","昆山"),KsLeak.class,ksLeaks);
        response.setHeader("content-disposition","attachment;filename="+ URLEncoder.encode("昆山.xls","utf8"));
        response.setCharacterEncoding("utf-8");
        response.setStatus(200);
        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        outputStream.close();
        workbook.close();
    }

    /**
     * 导入execl文件，并插入数据库
     * @param file
     * @return
     * @throws Exception
     */
    @RequiresPermissions("auth:import")
    @RequestMapping(value = "ksleakimport")
    public JsonResult<Integer> ksleakImportExecl(@RequestParam("file") MultipartFile file) throws Exception{
        //设置配置信息，execl的台头和列头
        ImportParams params = new ImportParams();
        params.setTitleRows(1);
        params.setHeadRows(1);
        //获取execl内的数据
        List<KsLeak> ksLeaks = ExcelImportUtil.importExcel(file.getInputStream(),KsLeak.class,params);
        //调用业务层写入数据库
        Integer number = 0;
        number = iKsleakService.importExeclService(ksLeaks);
        return new JsonResult<>(ok,number);
    }
    /**
     * 搜索接口
     * @param currentPage
     * @param sizePage
     * @param inputBugname
     * @param inputCVE
     * @return
     */
    @RequiresPermissions("auth:select")
    @RequestMapping("ksleaksearch")
    public JsonResult<PageData> ksleakSearchController(int currentPage,int sizePage,String inputBugname,String inputCVE){
        PageData<KsLeak> pageData = iKsleakService.selectSearchService(currentPage,sizePage,inputBugname,inputCVE);
        return new JsonResult<>(ok,pageData);
    }
}


