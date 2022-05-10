package com.catch_my_hand.backend.Home_sale;

import com.catch_my_hand.backend.Home_sale.model.GetProductPreviewRes;
import com.catch_my_hand.backend.Home_sale.model.PatchProductReq;
import com.catch_my_hand.backend.Home_sale.model.PostProductReq;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
@Log4j2
public class ProductDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    //등록
    public int createProduct(PostProductReq postProductReq) {
        String createProductQuery = "insert into Product(useridx, categoryidx, title, price, content,status,buyer) VALUES(?,?,?,?,?,?,?)";
        Object[] createProductParams = new Object[]{postProductReq.getUseridx(), postProductReq.getCategoryidx(), postProductReq.getTitle(), postProductReq.getPrice(),
                postProductReq.getContent(),postProductReq.getStatus(), postProductReq.getBuyer()};
        this.jdbcTemplate.update(createProductQuery, createProductParams);
        String lastInsertIdQuery = "select last_insert_id()";
        int productidx = this.jdbcTemplate.queryForObject(lastInsertIdQuery, int.class);

        return productidx;
    }

    // 수정
    public int modifyProduct(int productidx, PostProductReq postProductReq) {
        String modifyProductQuery = "update Product set categoryidx=?, title=?, price=?, content=?, status=? where productidx=?";
        Object[] modifyProductParams = new Object[]{postProductReq.getCategoryidx(), postProductReq.getTitle(), postProductReq.getPrice(),
                postProductReq.getContent(), postProductReq.getStatus(),productidx};

        return this.jdbcTemplate.update(modifyProductQuery, modifyProductParams);
    }

    // 상품상태 수정
    public int modifyProductStatus(PatchProductReq patchProductReq) {
        String modifyProductQuery = "update Product set status=? where productidx=?";
        Object[] modifyProductParams = new Object[]{patchProductReq.getStatus(), patchProductReq.getProductidx()};
        return this.jdbcTemplate.update(modifyProductQuery, modifyProductParams);
    }

    //탈퇴한 회원 상품 상태 수정
    public int withdrawProduct(int useridx) {
        String modifyProductQuery = "update Product set status='withdraw' where useridx=?";
        return this.jdbcTemplate.update(modifyProductQuery, useridx);
    }

    // 전체 조회
    public List<GetProductPreviewRes> getProductPreviewResList(int page, int size) {
        String getProductsQuery = "select P.productidx, P.title, P.status, P.price " +
                "from Product P join User U " +
                "on P.userIdx = U.userIdx " +
                "limit ?,?";
        Object[] getProductParams = new Object[]{(page - 1) * size, size};

        return this.jdbcTemplate.query(getProductsQuery,
                (rs, rowNum) -> new GetProductPreviewRes(
                        rs.getInt("productidx"),
                        null,
                        rs.getString("title"),
                        rs.getString("status"),
                        rs.getInt("price")), getProductParams);
    }

    //상품 title 로 검색 (포함)
    public List<GetProductPreviewRes> getProductListByTitle(String title, int page, int size){
        String getProductsQuery =  "select P.productidx, P.title, P.status, P.price "+
                "from Product P join User U "+
                "on P.useridx = U.useridx "+
                "where title Like ? "+
                "limit ?, ?";

        Object[] getProductTitleParams = new Object[]{"%"+title+"%", (page-1)*size, size};
        return this.jdbcTemplate.query(getProductsQuery,
                (rs, rowNum) -> new GetProductPreviewRes(
                        rs.getInt("productidx"),
                        null,
                        rs.getString("title"),
                        rs.getString("status"),
                        rs.getInt("price")),getProductTitleParams);
    }

    public List<GetProductPreviewRes> getActiveProductList(int page, int size){
        String getProductsQuery = "select P.productidx, P.title,P.status,P.price " +
                "from Product P join User U " +
                "on P.useridx = U.useridx " +
                "where P.status = 'active' " +
                "limit ?, ?";
        Object[] getProductParams = new Object[]{(page-1)*size, size};

        return this.jdbcTemplate.query(getProductsQuery,
                (rs, rowNum) -> new GetProductPreviewRes(
                        rs.getInt("productIdx"),
                        null,
                        rs.getString("title"),
                        rs.getString("status"),
                        rs.getInt("price")),getProductParams);
    }


    // 상품 구매자 수정
    public int modifyProductBuyer(PatchProductReq patchProductReq) {
        String modifyProductQuery = "update Product set buyer=? where productidx=?"; // 실행될 동적 쿼리문
        Object[] modifyProductParams = new Object[]{patchProductReq.getBuyer(), patchProductReq.getProductidx()}; // 동적 쿼리의 ?부분에 주입될 값
        return this.jdbcTemplate.update(modifyProductQuery, modifyProductParams);
    }

    //상품 삭제
    public int deleteProduct(int productIdx){
        String deleteUserQuery = "delete from Product where productidx = ?";
        int getProductParams = productIdx;
        return this.jdbcTemplate.update(deleteUserQuery, getProductParams);
    }
}
