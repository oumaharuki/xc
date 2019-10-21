package com.xuecheng.manage_cms.dao;

import com.xuecheng.framework.domain.cms.CmsPage;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

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
        CmsPage cmsPage = cmsPageRepository.findByPageName("测试页面");
        System.out.println(cmsPage);
        Optional<CmsPage> byId = cmsPageRepository.findById("5a754adf6abb500ad05688d9");
        System.out.println(byId);
        List<CmsPage> all = cmsPageRepository.findAll();
        System.out.println(all);
    }

    @Test
    public void testFindAllByExample(){
        int page=1;
        int size=10;

        CmsPage cmsPage = new CmsPage();
        cmsPage.setPageAliase("轮播");

        PageRequest pageable = PageRequest.of(page, size);

        ExampleMatcher matching = ExampleMatcher.matching();
        ExampleMatcher pageAliase = matching.withMatcher("pageAliase", ExampleMatcher.GenericPropertyMatchers.contains());
        Example<CmsPage> example=Example.of(cmsPage,pageAliase);
        Page<CmsPage> all = cmsPageRepository.findAll(example,pageable);

        List<CmsPage> content = all.getContent();
        System.out.println(content);
    }
}
