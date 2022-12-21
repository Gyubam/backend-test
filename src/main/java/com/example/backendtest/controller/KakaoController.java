package com.example.backendtest.controller;

import com.example.backendtest.service.KakaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequiredArgsConstructor
public class KakaoController {

    private final KakaoService kakaoService;

    @GetMapping("/login/kakao")
    public String kakaoLogin(@RequestParam(value = "code", required = false) String code) throws Exception {
        System.out.println("#########" + code);

        /*
         * 리턴값의 testPage는 아무 페이지로 대체해도 괜찮습니다.
         * 없는 페이지를 넣어도 무방합니다.
         * 404가 떠도 제일 중요한건 #########인증코드 가 잘 출력이 되는지가 중요하므로 너무 신경 안쓰셔도 됩니다.
         */

        String access_Token = kakaoService.getAccessToken(code);
        HashMap<String, Object> userInfo = kakaoService.getUserInfo(access_Token);


        System.out.println("###access_Token#### : " + access_Token);
        System.out.println("###nickname#### : " + userInfo.get("nickname"));
        System.out.println("###email#### : " + userInfo.get("email"));

        return "ok";
    }

}
