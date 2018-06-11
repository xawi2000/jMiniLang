package com.bajdcc.LALR1.interpret.os.user.routine;

import com.bajdcc.LALR1.interpret.os.IOSCodePage;
import com.bajdcc.util.ResourceLoader;

/**
 * 【用户态】时间
 *
 * @author bajdcc
 */
public class URTime implements IOSCodePage {
	@Override
	public String getName() {
		return "/usr/p/time";
	}

	@Override
	public String getCode() {
		return ResourceLoader.load(getClass());
	}
}