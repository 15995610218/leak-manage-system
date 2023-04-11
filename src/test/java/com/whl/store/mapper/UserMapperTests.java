package com.whl.store.mapper;

import com.whl.store.entity.KsLeak;
import com.whl.store.entity.PageData;
import com.whl.store.entity.User;
import com.whl.store.services.IKsleakService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@SpringBootTest
@RunWith(SpringRunner.class)
public class UserMapperTests {

    @Autowired(required = false)
    private UserMapper userMapper;

    @Autowired(required = false)
    private KsleakMapper ksleakMapper;

    @Autowired(required = false)
    private IKsleakService iKsleakService;

    @Test
    public void insert(){
        User user = new User();
        user.setUsername("zhangsan");
        user.setPassword("123456");
        Integer rows = userMapper.insert(user);
        System.out.println(rows);
    }

    @Test
    public void findselect(){
        User user = userMapper.findByUsername("maliu");
        System.out.println(user);
    }

    @Test
    public void exportText(){
        String beingDate = "2022-12-22";
        String andDate = "2022-12-27";

        List<KsLeak> ksLeaks = ksleakMapper.exportExeclByDate(beingDate,andDate);
        System.out.println(ksLeaks.toString());

    }

    @Test
    public void exportTextService(){
        String beingDate = "2022-12-22";
        String andDate = "2022-12-27";

        List<KsLeak> ksLeaks = iKsleakService.exportExeclService(beingDate,andDate);
        System.out.println(ksLeaks.size());
    }

    /**
     * 根据漏洞名称或CVE编号查询漏洞数据
     */
    @Test
    public void selectSearchDataText(){
        String bugName = "";
        String bugCVE = "";
        int currectPage = 0;
        int sizePage = 5;
        List<KsLeak> ksLeaks = ksleakMapper.selectSearchDataMapper(currectPage,sizePage,bugName,bugCVE);
        System.out.println(ksLeaks.size());
    }

    /**
     * 根据漏洞名称或CVE编号查询总数
     */
    @Test
    public void selectSearchCountText(){
        String bugName = null;
        String bugCVE = null;
        Integer row = ksleakMapper.selectSearchtotalmapper(bugName,bugCVE);
        System.out.println(row);
    }

    @Test
    public void selectSearchServiceText(){
        String bugName = null;
        String bugCVE = "CVE-2023-0001";
        int currectPage = 1;
        int sizePage = 5;
        PageData<KsLeak> pageData =iKsleakService.selectSearchService(currectPage,sizePage,bugName,bugCVE);
        System.out.println(pageData.getRows().toString());
    }

}
