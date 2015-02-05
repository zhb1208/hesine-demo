package com.hesine.manager;

import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.anyOf;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.sameInstance;
import static org.hamcrest.CoreMatchers.startsWith;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
import static org.junit.matchers.JUnitMatchers.hasItems;

import java.util.Arrays;

import com.google.gson.Gson;
import com.hesine.common.utils.HttpClientUtil;
import com.hesine.manager.webservice.utils.ResultDto;
import com.hesine.manager.webservice.utils.StatusCode;
import com.hesine.passport.client.utils.MD5;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.util.EntityUtils;
import org.hamcrest.core.CombinableMatcher;
import org.junit.Test;

/**
 * TODO:add description of class here, then delete the line.
 *
 * @author zhanghongbing
 * @version 14-11-21
 */
public class AssertTests {
    @Test
    public void testAssertArrayEquals() {
        byte[] expected = "trial".getBytes();
        byte[] actual = "trial".getBytes();
        org.junit.Assert.assertArrayEquals("failure - byte arrays not same", expected, actual);
    }

    @Test
    public void testAssertEquals() {
        org.junit.Assert.assertEquals("failure - strings are not equal", "text", "text");
    }

    @Test
    public void testAssertFalse() {
        org.junit.Assert.assertFalse("failure - should be false", false);
    }

    @Test
    public void testAssertNotNull() {
        org.junit.Assert.assertNotNull("should not be null", new Object());
    }

    @Test
    public void testAssertNotSame() {
        org.junit.Assert.assertNotSame("should not be same Object", new Object(), new Object());
    }

    @Test
    public void testAssertNull() {
        org.junit.Assert.assertNull("should be null", null);
    }

    @Test
    public void testAssertSame() {
        Integer aNumber = Integer.valueOf(768);
        org.junit.Assert.assertSame("should be same", aNumber, aNumber);
    }

    // JUnit Matchers assertThat
//    @Test
//    public void testAssertThatBothContainsString() {
//        assertThat("albumen", both(containsString("a")).and(containsString("b")));
//    }

    @Test
    public void testAssertThathasItemsContainsString() {
//        assertThat(Arrays.asList("one", "two", "three"), hasItems("one", "three"));
    }

    @Test
    public void testAssertThatEveryItemContainsString() {
//        assertThat(Arrays.asList(new String[]{"fun", "ban", "net"}), everyItem(containsString("n")));
    }

    // Core Hamcrest Matchers with assertThat
    @Test
    public void testAssertThatHamcrestCoreMatchers() {
        assertThat("good", allOf(equalTo("good"), startsWith("good")));
        assertThat("good", not(allOf(equalTo("bad"), equalTo("good"))));
        assertThat("good", anyOf(equalTo("bad"), equalTo("good")));
        assertThat(7, not(CombinableMatcher.<Integer> either(equalTo(3)).or(equalTo(4))));
        assertThat(new Object(), not(sameInstance(new Object())));
    }

    @Test
    public void testAssertTrue() {
        org.junit.Assert.assertTrue("failure - should be true", true);
        fail("fail");
    }

    @Test
    public void testResultDto() {
        ResultDto dto = new ResultDto();
        dto.setStatus(StatusCode.STATUS_FAIL);
        dto.setErrorCode(StatusCode.ERROR_EMPTY_PARAM);
        Gson gson = new Gson();
        System.out.println(gson.toJson(dto));

        //e10adc3949ba59abbe56e057f20f883e
        System.out.println(MD5.encodeString("123456"));
        //e10adc3949ba59abbe56e057f20f883e
        System.out.println(DigestUtils.md5Hex("123456"));

//        HttpClientUtil httpClientUtil = HttpClientUtil.getInstance();
//        String url = "http://me.hesine.com:8080/demo/user/list.xml";
//        byte[] data = "id=1".getBytes();
//        try {
//            HttpResponse response = httpClientUtil.postMethod(url, data, null);
//            HttpEntity entity = response.getEntity();
//            String responseString = EntityUtils.toString(entity, "UTF-8");
//            System.out.println(responseString);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

    }
}
