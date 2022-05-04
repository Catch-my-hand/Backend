package com.catch_my_hand.backend.home_sale;

import com.catch_my_hand.backend.config.BaseException;
import com.catch_my_hand.backend.config.BaseResponseStatus;
import com.catch_my_hand.backend.home_sale.model.PatchPetReq;
import com.catch_my_hand.backend.home_sale.model.PetStatus;
import com.catch_my_hand.backend.home_sale.model.PostPetReq;
import com.catch_my_hand.backend.home_sale.model.PostPetRes;
import com.catch_my_hand.backend.image.ImageService;
import com.catch_my_hand.backend.image.model.PostImageReq;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.catch_my_hand.backend.config.BaseResponseStatus.*;

@Service
@Log4j2
public class Home_saleService {

    private final Home_saleDao home_saleDao;
    private final ImageService imageService;

    @Autowired
    public Home_saleService(Home_saleDao home_saleDao, ImageService imageService) {
        this.home_saleDao = home_saleDao;
        this.imageService = imageService;
    }

    // 분양 등록 ( POST )
    public PostPetRes createPets(PostPetReq postPetReq) throws BaseException {
        try {
            int petidx = home_saleDao.createPet(postPetReq);
            for (String imgUrl : postPetReq.getImgUrlList()) {
                imageService.createPetImage(new PostImageReq(
                        imgUrl,
                        petidx
                ));
            }
            return new PostPetRes(petidx);
        } catch (Exception e) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    // 분양 게시물 수정
    public void modifyPet(int petidx, PostPetReq postPetReq) throws BaseException {
        try {
            home_saleDao.modifyPet(petidx, postPetReq);
            imageService.modifyPetImage(petidx, postPetReq.getImgUrlList());
        } catch (Exception e) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    // 분양 상태 수정
    public void modifyPetStatus(PatchPetReq patchPetReq) throws BaseException {
        String status = patchPetReq.getStatus();

        // 상태는 active, reserved, completed  세개중 하나
        if (status.equals(PetStatus.active.toString()) || status.equals(PetStatus.reserved.toString()) || status.equals(PetStatus.completed.toString())) {
            int result = home_saleDao.modifyPetStatus(patchPetReq); // True(1) or False(0)
            if (result == 0) {
                throw new BaseException(MODIFY_FAIL_PRODUCT_STATUS);
            }

            try {
                home_saleDao.modifyPetBuyer(patchPetReq);
            } catch (Exception e) {
                throw new BaseException(DATABASE_ERROR);
            }
        } else {
            throw new BaseException(PATCH_PRODUCTS_INVALID_STATUS);
        }
    }

    // 분양 희망자 수정
    public void modifyPetBuyer(PatchPetReq patchPetReq) throws BaseException {
        String status = "";
        try {
            status = home_saleDao.getPetRes(patchPetReq.getProductidx()).getStatus();
        } catch (Exception e) {
            throw new BaseException(DATABASE_ERROR);
        }
        if (status.equals(PetStatus.active.toString())) {
            throw new BaseException(PATCH_PRODUCTS_ACTIVE_STATUS);
        }
        try {
            int result = home_saleDao.modifyPetBuyer(patchPetReq);
            if (result == 0) {
                throw new BaseException(MODIFY_FAIL_BUYER);
            }
        } catch (Exception e) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    // 해당 petidx를 가지는 pet 삭제
    public void deletePet(int petidx) throws BaseException {
        try {
            home_saleDao.deletePet(petidx);
            imageService.deletePetImage(petidx);
        } catch (Exception e) {
            throw new BaseException(DATABASE_ERROR);
        }
    }
}
