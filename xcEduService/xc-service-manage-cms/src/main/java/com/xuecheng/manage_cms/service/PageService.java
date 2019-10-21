package com.xuecheng.manage_cms.service;

import org.apache.commons.lang3.StringUtils;
import com.xuecheng.framework.domain.cms.CmsPage;
import com.xuecheng.framework.domain.cms.request.QueryPageRequest;
import com.xuecheng.framework.model.response.CommonCode;
import com.xuecheng.framework.model.response.QueryResponseResult;
import com.xuecheng.framework.model.response.QueryResult;
import com.xuecheng.manage_cms.dao.CmsPageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author
 * @date 2019-10-21
 */
@Service
public class PageService {

    @Autowired
    CmsPageRepository cmsPageRepository;
    /* *
     *功能描述
     * @author
     * @date 2019-10-21
     * @param [page, size, queryPageRequset]
     * @return com.xuecheng.framework.model.response.QueryResponseResult
     */
    public QueryResponseResult findList(int page,int size, QueryPageRequest queryPageRequset){
        if(queryPageRequset==null){
            queryPageRequset=new QueryPageRequest();
        }
        ExampleMatcher matching = ExampleMatcher.matching();
        CmsPage cmsPage = new CmsPage();

        if(StringUtils.isNoneEmpty(queryPageRequset.getSiteId())){
            cmsPage.setSiteId(queryPageRequset.getSiteId());
        }
        if(StringUtils.isNoneEmpty(queryPageRequset.getTemplateId())){
            cmsPage.setTemplateId(queryPageRequset.getTemplateId());
        }
        if(StringUtils.isNoneEmpty(queryPageRequset.getPageAliase())){
            cmsPage.setPageAliase(queryPageRequset.getPageAliase());
        }
        Example<CmsPage> example=Example.of(cmsPage,matching);


        if(page<=0){
            page=1;
        }
        page=page-1;
        if(size<=0){
            size=10;
        }
        PageRequest pageable = PageRequest.of(page, size);
        Page<CmsPage> all = cmsPageRepository.findAll(example,pageable);
        QueryResult queryResult = new QueryResult();
        queryResult.setList(all.getContent());
        queryResult.setTotal(all.getTotalElements());
        QueryResponseResult queryResponseResult = new QueryResponseResult(CommonCode.SUCCESS,queryResult);
        return queryResponseResult;
    }
}
