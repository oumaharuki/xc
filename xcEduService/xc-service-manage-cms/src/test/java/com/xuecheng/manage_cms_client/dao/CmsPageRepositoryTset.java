package com.xuecheng.manage_cms_client.dao;

import com.xuecheng.framework.domain.cms.CmsPage;
import com.xuecheng.framework.domain.cms.response.CmsCode;
import com.xuecheng.framework.domain.cms.response.CmsPageResult;
import com.xuecheng.framework.exception.ExceptionCast;
import com.xuecheng.framework.model.response.CommonCode;
import com.xuecheng.manage_cms_client.service.PageService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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

    @Autowired
    PageService pageService;
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

    @Test
    public void testAdd(){
        CmsPage cmsPage = new CmsPage();
        cmsPage.setSiteId("5a751fab6abb5044e0d19ea1");
        cmsPage.setPageName("index.html");
        cmsPage.setPageWebPath("F:\\develop\\xc_portal_static\\");

        CmsPageResult res = pageService.add(cmsPage);
        System.out.println(res);
    }

    @Test
    public void testException(){
        CmsPage cmsPage = new CmsPage();
        cmsPage.setSiteId("5a751fab6abb5044e0d19ea1");
        cmsPage.setPageName("index.html");
        cmsPage.setPageWebPath("F:\\develop\\xc_portal_static\\");
        if(cmsPage==null){
            ExceptionCast.cast(CommonCode.FAIL);
        }

        CmsPage byPageNameAndSiteidAndPageWebPath = cmsPageRepository.findByPageNameAndSiteIdAndPageWebPath(cmsPage.getSiteId(),
                cmsPage.getPageName(), cmsPage.getPageWebPath());

        if(byPageNameAndSiteidAndPageWebPath!=null){
            ExceptionCast.cast(CmsCode.CMS_ADDPAGE_EXISTSNAME);
        }
    }
}
