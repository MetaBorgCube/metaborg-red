package Smalltalk.interpreter.natives;

import org.metaborg.Smalltalk.interpreter.generated.TypesGen;
import org.metaborg.Smalltalk.interpreter.generated.terms.BoolV_1_Term;
import org.metaborg.Smalltalk.interpreter.generated.terms.IVTerm;
import org.metaborg.Smalltalk.interpreter.generated.terms.NumV_1_Term;
import org.metaborg.meta.lang.dynsem.interpreter.nodes.building.TermBuild;

import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.source.SourceSection;

public class num_call_native_3 extends TermBuild {

	@Child
	protected TermBuild opNode;
	@Child
	protected TermBuild leftNode;
	@Child
	protected TermBuild rightNode;

	public num_call_native_3(SourceSection source, TermBuild op, TermBuild l, TermBuild r) {
		super(source);
		this.opNode = op;
		this.leftNode = l;
		this.rightNode = r;
	}

	@Override
	public IVTerm executeGeneric(VirtualFrame frame) {
		final String op = TypesGen.asString(opNode.executeGeneric(frame));
		final NumV_1_Term left = TypesGen.asNumV_1_Term(leftNode.executeGeneric(frame));
		final NumV_1_Term right = TypesGen.asNumV_1_Term(rightNode.executeGeneric(frame));
		switch (op) {
		case "+":
			return doPlus(left, right);
		case "-":
			return doMinus(left, right);
		case "*":
			return doTimes(left, right);
		case "/":
			return doDivide(left, right);
		case "==":
		case "=":
			return doEq(left, right);
		case "~~":
		case "~=":
			return doNeq(left, right);
		case ">":
			return doGreater(left, right);
		case "<":
			return doLess(left, right);
		case ">=":
			return doGreaterEq(left, right);
		case "<=":
			return doLessEq(left, right);
		default:
			throw new IllegalArgumentException("operator: '" + op + "' not recognised as operator on number.");
		}
	}

	private IVTerm doPlus(NumV_1_Term left, NumV_1_Term right) {
		return new NumV_1_Term(left.get_1() + right.get_1());
	}

	private IVTerm doMinus(NumV_1_Term left, NumV_1_Term right) {
		return new NumV_1_Term(left.get_1() - right.get_1());
	}

	private IVTerm doTimes(NumV_1_Term left, NumV_1_Term right) {
		return new NumV_1_Term(left.get_1() * right.get_1());
	}
	
	private IVTerm doDivide(NumV_1_Term left, NumV_1_Term right) {
		return new NumV_1_Term(left.get_1() / right.get_1());
	}

	private IVTerm doPow(NumV_1_Term left, NumV_1_Term right) {
		return new NumV_1_Term((int) Math.pow(left.get_1(), right.get_1()));
	}

	private IVTerm doEq(NumV_1_Term left, IVTerm right) {
		return new BoolV_1_Term(left.equals(right));
	}

	private IVTerm doNeq(NumV_1_Term left, NumV_1_Term right) {
		return new BoolV_1_Term(left.get_1() != right.get_1());
	}

	private IVTerm doGreater(NumV_1_Term left, NumV_1_Term right) {
		return new BoolV_1_Term(left.get_1() > right.get_1());
	}

	private IVTerm doLess(NumV_1_Term left, NumV_1_Term right) {
		return new BoolV_1_Term(left.get_1() < right.get_1());
	}

	private IVTerm doGreaterEq(NumV_1_Term left, NumV_1_Term right) {
		return new BoolV_1_Term(left.get_1() >= right.get_1());
	}

	private IVTerm doLessEq(NumV_1_Term left, NumV_1_Term right) {
		return new BoolV_1_Term(left.get_1() <= right.get_1());
	}
	
	public static TermBuild create(SourceSection source, TermBuild op, TermBuild left, TermBuild right) {
		return new num_call_native_3(source, op, left, right);
	}

}