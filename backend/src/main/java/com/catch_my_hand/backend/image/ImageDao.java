package com.catch_my_hand.backend.image;

import com.catch_my_hand.backend.image.model.GetImageRes;
import com.catch_my_hand.backend.image.model.PostImageReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class ImageDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    // 게시물 이미지 등록
    public int createProductImage(PostImageReq postImageReq){
        String createProductImageQuery = "Insert into Image (imgUrl, imgCategory, productidx) VALUES (?,?,?)";
        Object[] createImageParams = new Object[]{postImageReq.getImgUrl(), "product", postImageReq.getImgidx()}; // 동적 쿼리의 ?부분에 주입될 값
        this.jdbcTemplate.update(createProductImageQuery, createImageParams);
        String lastInserIdQuery = "select last_insert_id()"; // 가장 마지막에 삽입된(생성된) id값은 가져온다.
//        System.out.println(lastInserIdQuery);
        return this.jdbcTemplate.queryForObject(lastInserIdQuery, int.class); // 해당 쿼리문의 결과 마지막으로 삽인된 유저의 userIdx번호를 반환한다.
    }

    // 게시물 이미지들 조회
    public List<GetImageRes> getProductImages(int productidx){
        String getProductImagesQuery = "select imgidx, imgUrl from Image where productidx=?";
        return this.jdbcTemplate.query(getProductImagesQuery,
                (rs, rowNum) -> new GetImageRes(
                        rs.getInt("imgidx"),
                        rs.getString("imgUrl")
                ), productidx);
    }

    // 대표 이미지 1개 조회
    public List<GetImageRes> getOneProductImage(int productidx){
        String getProductImageQuery = "select imgidx, imgUrl from Image where productidx=? limit 1";
        return this.jdbcTemplate.query(getProductImageQuery,
                (rs, rowNum) -> new GetImageRes(
                        rs.getInt("imgIdx"),
                        rs.getString("imgUrl")
                ), productidx);
    }

    // 이미지 삭제
    public int deleteImage(String imgCategory, int idx) {
        String deleteImageQuery = "";
        switch (imgCategory) {
            case "post":
                deleteImageQuery = "DELETE FROM Image WHERE postidx = ?";
                break;
            case "product":
                deleteImageQuery = "DELETE FROM Image WHERE productidx = ?";
                break;
            case "user":
                deleteImageQuery = "DELETE FROM Image WHERE useridx = ?";
                break;
            default:
                return 0;
        }
        int getIdxParams = idx;
        return this.jdbcTemplate.update(deleteImageQuery, getIdxParams);
    }





}
