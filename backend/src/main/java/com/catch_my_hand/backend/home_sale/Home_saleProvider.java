package com.catch_my_hand.backend.home_sale;

import com.catch_my_hand.backend.config.BaseException;
import com.catch_my_hand.backend.home_sale.model.GetPetPreviewRes;
import com.catch_my_hand.backend.home_sale.model.GetPetRes;
import com.catch_my_hand.backend.image.ImageProvider;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.catch_my_hand.backend.config.BaseResponseStatus.*;

@Service
@Log4j2
public class Home_saleProvider {

    private final Home_saleDao home_saleDao;
    private final ImageProvider imageProvider;

    @Autowired
    public Home_saleProvider(Home_saleDao home_saleDao, ImageProvider imageProvider) {
        this.home_saleDao = home_saleDao;
        this.imageProvider = imageProvider;
    }

    // 전체 게시물 미리보기 조회
    public List<GetPetPreviewRes> getAllPets(int page, int size) throws BaseException {
        try {
            List<GetPetPreviewRes> getPetPreviewResList = home_saleDao.getPetPreviewRes(page, size);
            for (GetPetPreviewRes getPetPreviewRes : getPetPreviewResList) {
                getPetPreviewRes.setProductImgUrl(imageProvider.getOnePetImage(getPetPreviewRes.getProductidx()));
            }

            return getPetPreviewResList;
        } catch (Exception e) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    // 특정 게시물 상세조회
    public GetPetRes getPetRes(int productidx) throws BaseException {
        try {
            GetPetRes petRes = home_saleDao.getPetRes(productidx);
            petRes.setPetImgList(imageProvider.getProductImages(productidx));
            return petRes;
        } catch (Exception e) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    // 현재 분양 진행중인 게시물만 조회
    public List<GetPetPreviewRes> getActivePets (int page, int size) throws BaseException {
        try {
            List<GetPetPreviewRes> getPetPreviewResList = home_saleDao.getActivePetList(page, size);
            for (GetPetPreviewRes getPetPreviewRes : getPetPreviewResList) {
                getPetPreviewRes.setProductImgUrl(imageProvider.getOnePetImage(getPetPreviewRes.getProductidx()));
            }
            return getPetPreviewResList;
        } catch (Exception e) {
            throw new BaseException(DATABASE_ERROR);
        }
    }



}
