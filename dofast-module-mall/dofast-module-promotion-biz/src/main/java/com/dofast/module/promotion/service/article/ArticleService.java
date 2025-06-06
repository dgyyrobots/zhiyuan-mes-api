package com.dofast.module.promotion.service.article;

import com.dofast.framework.common.pojo.PageResult;
import com.dofast.module.promotion.controller.admin.article.vo.article.ArticleCreateReqVO;
import com.dofast.module.promotion.controller.admin.article.vo.article.ArticlePageReqVO;
import com.dofast.module.promotion.controller.admin.article.vo.article.ArticleUpdateReqVO;
import com.dofast.module.promotion.controller.app.article.vo.article.AppArticlePageReqVO;
import com.dofast.module.promotion.dal.dataobject.article.ArticleDO;

import javax.validation.Valid;
import java.util.List;

/**
 * 文章详情 Service 接口
 *
 * @author HUIHUI
 */
public interface ArticleService {

    /**
     * 创建文章详情
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createArticle(@Valid ArticleCreateReqVO createReqVO);

    /**
     * 更新文章详情
     *
     * @param updateReqVO 更新信息
     */
    void updateArticle(@Valid ArticleUpdateReqVO updateReqVO);

    /**
     * 删除文章详情
     *
     * @param id 编号
     */
    void deleteArticle(Long id);

    /**
     * 获得文章详情
     *
     * @param id 编号
     * @return 文章详情
     */
    ArticleDO getArticle(Long id);

    /**
     * 获得文章详情分页
     *
     * @param pageReqVO 分页查询
     * @return 文章详情分页
     */
    PageResult<ArticleDO> getArticlePage(ArticlePageReqVO pageReqVO);

    /**
     * 获得文章详情列表
     *
     * @param recommendHot    是否热门
     * @param recommendBanner 是否轮播图
     * @return 文章详情列表
     */
    List<ArticleDO> getArticleCategoryListByRecommend(Boolean recommendHot, Boolean recommendBanner);

    /**
     * 获得文章详情分页
     *
     * @param pageReqVO 分页查询
     * @return 文章详情分页
     */
    PageResult<ArticleDO> getArticlePage(AppArticlePageReqVO pageReqVO);

    /**
     * 获得指定分类的文章列表
     *
     * @param categoryId 文章分类编号
     * @return 文章列表
     */
    List<ArticleDO> getArticleByCategoryId(Long categoryId);

    /**
     * 获得指定分类的文章数量
     *
     * @param categoryId 文章分类编号
     * @return 文章数量
     */
    Long getArticleCountByCategoryId(Long categoryId);

    /**
     * 增加文章浏览量
     *
     * @param id 文章编号
     */
    void addArticleBrowseCount(Long id);

}
