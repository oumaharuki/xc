package com.xuecheng.manage_cms.dao;

import com.xuecheng.framework.domain.cms.CmsPage;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @author
 * @date 2019-10-18
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class CmsPageRepositoryTset {

    @Autowired
    CmsPageRepository cmsPageRepository;
    @Test
    public void testFindAll(){
        List<CmsPage> all = cmsPageRepository.findAll();
        System.out.println(all);


    }
}
