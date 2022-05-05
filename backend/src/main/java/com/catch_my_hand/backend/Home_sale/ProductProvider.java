package com.catch_my_hand.backend.Home_sale;

import com.catch_my_hand.backend.Home_sale.model.GetProductPreviewRes;
import com.catch_my_hand.backend.config.BaseException;
import com.catch_my_hand.backend.image.ImageProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.catch_my_hand.backend.config.BaseResponseStatus.*;

@Service
@RequiredArgsConstructor
@Log4j2
public class ProductProvider {

    private final ProductDao productDao;
    private final ImageProvider imageProvider;


    // 모든 게시물 보기
    public List<GetProductPreviewRes> getAllProducts(int page, int size) throws BaseException {
        try {
            List<GetProductPreviewRes> getProductPreviewResList = productDao.getProductPreviewResList(page, size);
            for (GetProductPreviewRes getProductPreviewRes : getProductPreviewResList) {
                getProductPreviewRes.setProductimgUrl(imageProvider.getOneProductImage(getProductPreviewRes.getProductidx()));
            }
            return getProductPreviewResList;
        } catch (Exception e) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    // 해당 제목을 가지는 게시물 조회
    public List<GetProductPreviewRes> getProductsByTitle(String title,int page, int size) throws BaseException {
        try {
            List<GetProductPreviewRes> getProductPreviewResList = productDao.getProductListByTitle(title,page,size);
            for (GetProductPreviewRes getProductPreviewRes : getProductPreviewResList) {
                getProductPreviewRes.setProductimgUrl(imageProvider.getOneProductImage(getProductPreviewRes.getProductidx()));
            }
            return getProductPreviewResList;
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    // 분양 진행중인 게시물만 보기
    public List<GetProductPreviewRes> getActiveProducts(int page, int size) throws BaseException {
        try {
            List<GetProductPreviewRes> getProductPreviewResList = productDao.getActiveProductList(page, size);
            for (GetProductPreviewRes getProductPreviewRes : getProductPreviewResList) {
                getProductPreviewRes.setProductimgUrl(imageProvider.getOneProductImage(getProductPreviewRes.getProductidx()));
            }
            return getProductPreviewResList;
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }




}
