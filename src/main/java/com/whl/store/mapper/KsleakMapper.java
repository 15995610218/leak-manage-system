package com.whl.store.mapper;

import com.whl.store.entity.KsLeak;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface KsleakMapper {
    /**
     * 查询所有漏洞信息
     */
    List<KsLeak> selectAll();

    /**
     * ksleak分页查询方法
     * @param begin
     * @param size
     * @return
     */
    List<KsLeak> selectKsleakPageMapper(@Param("begin")int begin, @Param("size")int size);

    /**
     * ksleak查询总计数
     * @return
     */
    int selectKsleakTotalMapper();

    /**
     * 添加一个漏洞数据
     * @param ksLeak
     * @return 返回影响行数
     */
    int insertKsleakByOne(KsLeak ksLeak);

    /**
     * 根据ID修改一条数据
     * @param ksLeak
     * @return 返回影响行数
     */
    Integer updateKsleakById(KsLeak ksLeak);

    /**
     * 根据ID删除一条数据
     * @param ids
     * @return
     */
    Integer deleteKsleakById(@Param("ids") int[] ids);

    /**
     * 根据时间范围查询数据
     * @param beginDate
     * @param andDate
     * @return
     */
    List<KsLeak> exportExeclByDate(@Param("beginDate") String beginDate,@Param("andDate") String andDate);

    /**
     * 导入文件插入数据库
     * @param ksLeaks
     * @return
     */
    int importksleakmapper(List<KsLeak> ksLeaks);

    /**
     * 可以根据漏洞名称和CVE编号搜索查询漏洞数据，由于是分页查询，需要传递页面数据
     * @param currectPage
     * @param sizePage
     * @param bugName
     * @param bugCVE
     * @return
     */
    List<KsLeak> selectSearchDataMapper(@Param("currectPage") int currectPage,@Param("sizePage") int sizePage,@Param("bugName") String bugName,@Param("bugCVE") String bugCVE);

    /**
     * 根据漏洞名称或CVE编号查询数据，获取总数
     * @param bugName
     * @param bugCVE
     * @return
     */
    int selectSearchtotalmapper(@Param("bugName") String bugName,@Param("bugCVE") String bugCVE);


}
