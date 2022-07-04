/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package cn.mall4j.biz.product.application.category.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.mall4j.biz.product.application.category.resp.ProductCategoryRespDTO;
import cn.mall4j.biz.product.application.category.service.ProductCategoryService;
import cn.mall4j.biz.product.domain.entity.ProductCategory;
import cn.mall4j.biz.product.domain.repository.ProductCategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 商品分类
 *
 * @author chen.ma
 * @github https://github.com/mabaiwan
 */
@Service
@AllArgsConstructor
public class ProductCategoryServiceImpl implements ProductCategoryService {
    
    private final ProductCategoryRepository productCategoryRepository;
    
    @Override
    public List<ProductCategoryRespDTO> listAllProductCategory() {
        ProductCategory productCategory = productCategoryRepository.listAllProductCategory();
        return BeanUtil.copyToList(productCategory.getProductCategoryList(), ProductCategoryRespDTO.class);
    }
}
