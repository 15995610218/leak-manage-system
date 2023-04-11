package com.whl.store.services.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.whl.store.entity.KsLeak;
import com.whl.store.entity.PageData;
import com.whl.store.mapper.KsleakMapper;
import com.whl.store.services.IKsleakService;
import com.whl.store.services.ex.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class IKsleakServiceImpl implements IKsleakService {

    @Autowired(required = false)
    private KsleakMapper ksleakMapper;

    /**
     * 业务层查询所有数据
     * @return
     */
    @Override
    public List<KsLeak> selectAlllesk() {
        List<KsLeak> lists = ksleakMapper.selectAll();
        if (lists.size() == 0){
            throw new LeakSelectException("暂无数据!");
        }
        return lists;
    }

    /**
     * 业务层根据分页条件查询数据
     * @param currentPage
     * @param sizePage
     * @return
     */
    @Override
    public PageData<KsLeak> selectKsleakPageService(int currentPage, int sizePage) {
        //分页查询
        int begin = (currentPage - 1) * sizePage;
        int size = sizePage;

        //调用mapper查询数据
        List<KsLeak> rows = ksleakMapper.selectKsleakPageMapper(begin,size);
        int total = ksleakMapper.selectKsleakTotalMapper();
        if (rows.size() <= 0){
            throw new LeakSelectException("分页数据查询问题!");
        }
        //封装数据
        PageData<KsLeak> pageData = new PageData<>();
        pageData.setRows(rows);
        pageData.setTotalcount(total);
        return pageData;
    }

    /**
     * 业务层添加数据
     * @param ksLeak
     */
    @Override
    public void insertksleakbyoneservice(KsLeak ksLeak) {
        Integer rows = ksleakMapper.insertKsleakByOne(ksLeak);
        if (rows != 1){
            throw new LeakInsertException("添加数据失败!");
        }
    }

    /**
     * 业务层根据ID更新数据
     * @param ksLeak
     */
    @Override
    public void updateKsleakById(KsLeak ksLeak) {
        Integer rows = ksleakMapper.updateKsleakById(ksLeak);
        if (rows != 1){
            throw new LeakUpdateException("更新数据失败!");
        }
    }

    /**
     * 业务层根据ID删除数据
     * @param ids
     */
    @Override
    public void deleteKsleakById(int[] ids) {
        Integer rows = ksleakMapper.deleteKsleakById(ids);
        if (rows == 0){
            throw new LeakDeleteException("删除数据失败!");
        }
    }

    @Override
    public List<KsLeak> exportExeclService(String beginDate, String andDate) {
        List<KsLeak> ksLeaks = ksleakMapper.exportExeclByDate(beginDate,andDate);
        if (ksLeaks.size() <= 0){
            throw new LeakExportExeclException("导出数据失败！");
        }
        return ksLeaks;
    }

    /**
     * 业务层批量导入数据库
     * @param ksLeaks
     * @return
     */
    @Override
    public int importExeclService(List<KsLeak> ksLeaks) {
        List<KsLeak> lists = new ArrayList<>();
        Integer num = 0;
        for (int i = 0;ksLeaks.size() > i;i++){
            if (ksLeaks.get(i).getBugName() != null && ksLeaks.get(i).getReleaseDate() != null && ksLeaks.get(i).getThreatLevel() != null){
                System.out.println(ksLeaks.get(i).getReleaseDate());
                lists.add(ksLeaks.get(i));
            }
        }
        if (lists.size() == 0){
            throw new LeakImportExeclException("导入成功条数为:"+lists.size()+";导入失败条数为:"+ksLeaks.size());
        }
        num = ksleakMapper.importksleakmapper(lists);
        if (ksLeaks.size() != lists.size()){
            num = ksLeaks.size()-lists.size();
            throw new LeakImportExeclException("导入成功条数为:"+lists.size()+";导入失败条数为:"+num);
        }
        return num;
    }

    /**
     * 根据漏洞名称或CVE编号搜索数据
     * @param currectPage
     * @param sizePage
     * @param bugName
     * @param bugCVE
     * @return
     */
    @Override
    public PageData<KsLeak> selectSearchService(int currectPage, int sizePage, String bugName, String bugCVE) {
        int begin = (currectPage - 1)*sizePage;
        int size = sizePage;

        List<KsLeak> rows = ksleakMapper.selectSearchDataMapper(begin,size,bugName,bugCVE);

        int total = ksleakMapper.selectSearchtotalmapper(bugName,bugCVE);

        if (total == 0){
            throw new LeakSearchException("未搜索到相关数据:"+total);
        }
        PageData<KsLeak> pageData = new PageData<>();
        pageData.setRows(rows);
        pageData.setTotalcount(total);
        return pageData;
    }
}
