package com.bajdcc.LALR1.interpret.module;

import com.bajdcc.LALR1.grammar.runtime.IRuntimeDebugExec;
import com.bajdcc.LALR1.grammar.runtime.IRuntimeStatus;
import com.bajdcc.LALR1.grammar.runtime.RuntimeObject;
import com.bajdcc.LALR1.grammar.runtime.RuntimeObjectType;

import java.util.List;

/**
 * 【扩展】数学一元运算
 *
 * @author bajdcc
 */
public class ModuleMathUnaryFunc implements IRuntimeDebugExec {

	public enum ModuleMathUnaryFuncType {
		kSqrt,
		kCos,
		kSin,
	}

	private String doc;
	private ModuleMathUnaryFuncType type;

	public ModuleMathUnaryFunc(String doc, ModuleMathUnaryFuncType type) {
		this.doc = doc;
		this.type = type;
	}

	@Override
	public RuntimeObject ExternalProcCall(List<RuntimeObject> args,
	                                      IRuntimeStatus status) {
		switch (type) {
			case kSqrt:
				return new RuntimeObject(Math.sqrt(args.get(0).getDouble()));
			case kCos:
				return new RuntimeObject(Math.cos(args.get(0).getDouble()));
			case kSin:
				return new RuntimeObject(Math.sin(args.get(0).getDouble()));
			default:
				break;
		}
		return null;
	}

	@Override
	public RuntimeObjectType[] getArgsType() {
		return new RuntimeObjectType[]{RuntimeObjectType.kReal};
	}

	@Override
	public String getDoc() {
		return doc;
	}
}
