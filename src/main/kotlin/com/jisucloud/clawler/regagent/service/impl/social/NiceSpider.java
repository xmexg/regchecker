package com.jisucloud.clawler.regagent.service.impl.social;

import com.google.common.collect.Sets;
import com.jisucloud.clawler.regagent.service.PapaSpider;
import com.jisucloud.clawler.regagent.service.UsePapaSpider;

import lombok.extern.slf4j.Slf4j;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Slf4j
@UsePapaSpider
public class NiceSpider implements PapaSpider {

	private OkHttpClient okHttpClient = new OkHttpClient.Builder().connectTimeout(10, TimeUnit.SECONDS)
			.readTimeout(10, TimeUnit.SECONDS).retryOnConnectionFailure(true).build();

	@Override
	public String message() {
		return "nice好赞作为一款图片社交软件可以让你结识到跟你有共同生活方式或者有共同兴趣爱好的朋友。拍张照片-选择滤镜效果-选择贴纸-为照片打上标签-发布。发布的同时照片可以自动保存在相册里，同时也可以一键同步到微博，微信，QQ空间。";
	}

	@Override
	public String platform() {
		return "nice";
	}

	@Override
	public String home() {
		return "nice.com";
	}

	@Override
	public String platformName() {
		return "nice";
	}

	@Override
	public String[] tags() {
		return new String[] {"交友" , "二手物品", "社区"};
	}
	
	@Override
	public Set<String> getTestTelephones() {
		return Sets.newHashSet("18515290717", "18210538513");
	}

	@Override
	public boolean checkTelephone(String account) {
		try {
			String url = "http://www.oneniceapp.com/account/loginbymobile";
			FormBody formBody = new FormBody
	                .Builder()
	                .add("mobile", account)
	                .add("country", "1")
	                .add("password", "dXKNb4J0LmyFBsEQFbgjD76ur++PlmP2wIdGo6+bUxsZ1G1IPcPR+RL1uybbktSDe5jq4m8mGn8uJXJrIvKF0btZdJZ8aKytAec9lmKkYKefYA5jaXkT80JMRnqK0VeWvpqoTkuZrdilK1uUJvBHVDCl3XTuvLT85hcRXOACaHE=")
	                .build();
			Request request = new Request.Builder().url(url)
					.addHeader("User-Agent", "Mozilla/5.0 (Linux; Android 7.0; PLUS Build/NRD90M) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/61.0.3163.98 Mobile Safari/537.36")
					.addHeader("Host", "www.oneniceapp.com")
					.addHeader("Referer", "http://www.oneniceapp.com/")
					.post(formBody)
					.build();
			Response response = okHttpClient.newCall(request).execute();
			String res = response.body().string();
			if (res.contains("200101")) {
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
