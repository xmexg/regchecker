package com.jisucloud.clawler.regagent.service.impl.borrow;

import com.jisucloud.clawler.regagent.service.PapaSpider;
import com.jisucloud.clawler.regagent.service.UsePapaSpider;

import lombok.extern.slf4j.Slf4j;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import com.google.common.collect.Sets;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Slf4j
@UsePapaSpider
public class PuHuiJiaSpider implements PapaSpider {

	private OkHttpClient okHttpClient = new OkHttpClient.Builder().connectTimeout(10, TimeUnit.SECONDS)
			.readTimeout(10, TimeUnit.SECONDS).retryOnConnectionFailure(true).build();
	
	@Override
	public String message() {
		return "普惠家(www.puhuijia.com)专业的P2P互联网金融信息服务平台。旨在为有财富增值需求的出借人和有融资需求的借款人提供专业、高效、诚信、互惠互助的网络投融资信息。";
	}

	@Override
	public String platform() {
		return "puhuijia";
	}

	@Override
	public String home() {
		return "puhuijia.com";
	}

	@Override
	public String platformName() {
		return "普惠家";
	}

	@Override
	public String[] tags() {
		return new String[] {"P2P", "借贷"};
	}
	
	@Override
	public Set<String> getTestTelephones() {
		return Sets.newHashSet("15985268904", "18210538513");
	}

	@Override
	public boolean checkTelephone(String account) {
		try {
			String url = "https://www.puhuijia.com/member/memberRegistryAction!validatememberPhone.action";
			FormBody formBody = new FormBody
	                .Builder()
	                .add("memberPhone", account)
	                .build();
			Request request = new Request.Builder().url(url)
					.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64; rv:56.0) Gecko/20100101 Firefox/56.0")
					.addHeader("Host", "www.puhuijia.com")
					.addHeader("Referer", "https://www.puhuijia.com/member/memberRegistryAction!initRegistry.action")
					.post(formBody)
					.build();
			Response response = okHttpClient.newCall(request).execute();
			if (response.body().string().contains("true")) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean checkEmail(String account) {
		return false;
	}

	@Override
	public Map<String, String> getFields() {
		return null;
	}

}
