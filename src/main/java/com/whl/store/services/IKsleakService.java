package com.whl.store.services;

import com.whl.store.entity.KsLeak;
import com.whl.store.entity.PageData;

import java.util.Date;
import java.util.List;

public interface IKsleakService {

    /**
     * 漏洞查询业务层接口
     * @return 返回漏洞列表
     */
    List<KsLeak> selectAlllesk();

    /**
     * ksleak分页查询的业务层
     * @param currentPage
     * @param sizePage
     * @return
     */
    PageData<KsLeak> selectKsleakPageService(int currentPage,int sizePage);

    /**
     * 添加一条漏洞数据服务接口
     * @param ksLeak
     */
    void insertksleakbyoneservice(KsLeak ksLeak);

    /**
     * 根据ID更新一条漏洞数据
     * @param ksLeak
     */
    void updateKsleakById(KsLeak ksLeak);

    /**
     * 根据ID删除漏洞数据
     * @param ids
     */
    void deleteKsleakById(int[] ids);

    /**
     * 根据时间范围导出execl数据
     * @param beginDate
     * @param andDate
     * @return
     */
    List<KsLeak> exportExeclService(String beginDate,String andDate);

    /**
     * 导入execl数据
     * @param ksLeaks
     */
    int importExeclService(List<KsLeak> ksLeaks);

    /**
     * 根据漏洞名称或CVE编号搜索数据
     * @param currectPage
     * @param sizePage
     * @param bugName
     * @param bugCVE
     * @return
     */
    PageData<KsLeak> selectSearchService(int currectPage,int sizePage,String bugName,String bugCVE);

}
