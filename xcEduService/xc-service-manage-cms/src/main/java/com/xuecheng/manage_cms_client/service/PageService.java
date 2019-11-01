package com.xuecheng.manage_cms_client.service;

import com.xuecheng.framework.domain.cms.response.CmsCode;
import com.xuecheng.framework.domain.cms.response.CmsPageResult;
import com.xuecheng.framework.exception.ExceptionCast;
import org.apache.commons.lang3.StringUtils;
import com.xuecheng.framework.domain.cms.CmsPage;
import com.xuecheng.framework.domain.cms.request.QueryPageRequest;
import com.xuecheng.framework.model.response.CommonCode;
import com.xuecheng.framework.model.response.QueryResponseResult;
import com.xuecheng.framework.model.response.QueryResult;
import com.xuecheng.manage_cms_client.dao.CmsPageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

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

    /* *
     *功能描述  新增页面
     * @author
     * @date 2019-10-22
     * @param [cmsPage]
     * @return com.xuecheng.framework.domain.cms.response.CmsPageResult
     */
    public CmsPageResult add(CmsPage cmsPage) {
        if(cmsPage==null){
            ExceptionCast.cast(CommonCode.FAIL);
        }

        CmsPage byPageNameAndSiteidAndPageWebPath = cmsPageRepository.findByPageNameAndSiteIdAndPageWebPath(
                cmsPage.getPageName(),
                cmsPage.getSiteId(),
                cmsPage.getPageWebPath());

        if(byPageNameAndSiteidAndPageWebPath!=null){
            ExceptionCast.cast(CmsCode.CMS_ADDPAGE_EXISTSNAME);
        }
        cmsPage.setPageId(null);
        cmsPageRepository.save(cmsPage);
        return new CmsPageResult(CommonCode.SUCCESS,cmsPage);

    }
}
