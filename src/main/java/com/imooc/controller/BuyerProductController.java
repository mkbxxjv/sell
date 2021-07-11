package com.imooc.controller;

import com.imooc.VO.ProductInfoVO;
import com.imooc.VO.ProductVo;
import com.imooc.VO.ResultVO;
import com.imooc.dataObject.ProductCategory;
import com.imooc.dataObject.ProductInfo;
import com.imooc.service.CategoryService;
import com.imooc.service.ProductService;
import com.imooc.utils.ResultVOUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 买家商品
 */
@RestController
@RequestMapping("/buyer/product")
public class BuyerProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/list")
    public ResultVO<Object> list(){

        //1.查询所有上架商品
        List<ProductInfo> upList = productService.findUpAll();

        //2.查询类目
        /*List<Integer> categoryTypeList = new ArrayList<>();
        //传统方法
        for (ProductInfo productInfo : upList) {
            categoryTypeList.add(productInfo.getCategoryType());
        }*/
        //精简方法
        List<Integer> categoryTypeList = upList.stream()
                .map(ProductInfo::getCategoryType)
                .collect(Collectors.toList());
        List<ProductCategory> productCategoryList = categoryService.findByCategoryTypeIn(categoryTypeList);

        //3.数据拼装
        List<ProductVo> productVOList = new ArrayList<>();
        for (ProductCategory productCategory : productCategoryList) {
            ProductVo productVO = new ProductVo();
            productVO.setCategoryName(productCategory.getCategoryName());
            productVO.setCategoryType(productCategory.getCategoryType());

            List<ProductInfoVO> productInfoVOList =  new ArrayList<>();
            for (ProductInfo productInfo : upList) {
                if (productCategory.getCategoryType().equals(productInfo.getCategoryType())){
                    ProductInfoVO productInfoVO = new ProductInfoVO();
                    BeanUtils.copyProperties(productInfo,productInfoVO);
                    productInfoVOList.add(productInfoVO);
                }
            }
            productVO.setProductInfoVOList(productInfoVOList);
            productVOList.add(productVO);
        }
        return ResultVOUtil.success(productVOList);
    }
}
