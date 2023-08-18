package gachon.rookie.server.utils;

import gachon.rookie.server.common.BaseErrorCode;
import gachon.rookie.server.common.BaseException;
import io.jsonwebtoken.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Date;

@Component
public class JwtUtil {

    @Value("${jwt.secret-key}")
    private String jwtKey;

    /**
     * Jwt Token 생성 메서드
     * */
    public String createToken(String userIdx) {

        Date now = new Date();
        Date expireDate = new Date(System.currentTimeMillis()+600000*6);

        return Jwts.builder()
                .setHeaderParam("type", "jwt")
                .claim("userIdx", userIdx)
                .setIssuedAt(now)
                .setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS256, jwtKey)
                .compact();

    }
    /**
     * Refresh Token 생성 매서드
     * */
    public String createRefreshToken() {
        Date now = new Date();
        Date refreshExpireDate = new Date(System.currentTimeMillis()*600000*6*24);
        return Jwts.builder()
                .setIssuedAt(now)
                .setExpiration(refreshExpireDate)
                .signWith(SignatureAlgorithm.HS256, jwtKey)
                .compact();
    }

    public String getJwt(){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();

        return request.getHeader(HttpHeaders.AUTHORIZATION).split(" ")[1];
    }


    /**
     * AccessToken 만료 확인 메서드
     * @return 만료: true 아직 유효: false
     * */
    public boolean isJwtExpired(String accessToken){
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(jwtKey).parseClaimsJws(accessToken);
            return claims.getBody().getExpiration().before(new Date());
        } catch(ExpiredJwtException e) {
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * userIdx 추출
     * @return int */
    public String getUserIdx(String accessToken) throws BaseException{
        // JWT 가져오기
        if(accessToken == null || accessToken.length() == 0){
            throw new BaseException(BaseErrorCode.JWT_NOT_EXIST);
        }

        //JWT 파싱
        return parseJwt(accessToken);
    }

    /**
     * Jwt 파싱해서 userIdx를 리턴하는 메서드
     * */
    public String parseJwt(String accessToken) throws BaseException{
        Jws<Claims> claimsJws;
        try{
            claimsJws = Jwts.parser()
                    .setSigningKey(jwtKey)
                    .parseClaimsJws(accessToken);
        } catch (SignatureException exception){
            throw new BaseException(BaseErrorCode.JWT_ERROR);
        }

        //Return userIdx
        return claimsJws.getBody().get("userIdx", String.class);
    }
}
