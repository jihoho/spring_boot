package com.jihoho.myspring.springboot.test;

import static org.assertj.core.api.Assertions.assertThat;

import com.jihoho.myspring.springboot.web.dto.HelloResponseDto;
import org.junit.Test;

/**
 * Created by IntelliJ IDEA
 * User: hojun
 * Date: 2021-05-07 Time: 오후 9:50
 */
public class FailTest {
    @Test
    public void 실패하는_테스트(){
        assertThat(0).isEqualTo(0);
    }
}
