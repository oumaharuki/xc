package com.xuecheng.manage_cms_client.service;

import com.xuecheng.framework.domain.cms.CmsPage;
import com.xuecheng.framework.domain.cms.CmsSite;
import com.xuecheng.manage_cms_client.dao.CmsPageRepository;
import com.xuecheng.manage_cms_client.dao.CmsSiteRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

/**
 * @author
 * @date 2019-10-23
 */
public class PageService {

    @Autowired
    CmsPageRepository cmsPageRepository;
    //保存页面到服务器
    public void savePageToServerPath(String pageId){
        //得到html的页面id
        CmsPage cmsPageById = this.getCmsPageById(pageId);



    }

    public CmsPage getCmsPageById(String pageId){
        Optional<CmsPage> byId = cmsPageRepository.findById(pageId);
        if(byId.isPresent()){
            CmsPage cmsPage = byId.get();
            return cmsPage;
        }
        return null;
    }
}
