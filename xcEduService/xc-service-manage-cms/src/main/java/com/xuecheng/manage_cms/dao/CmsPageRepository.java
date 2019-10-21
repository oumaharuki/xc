package com.xuecheng.manage_cms.dao;

import com.xuecheng.framework.domain.cms.CmsPage;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CmsPageRepository extends MongoRepository<CmsPage,String>{
    //根据页面名称查询
    CmsPage findByPageName(String pageName);
    List<CmsPage> findByPageAliase(String ss);
}
